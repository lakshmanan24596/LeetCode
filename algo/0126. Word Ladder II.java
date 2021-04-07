/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord

Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. 
Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 
Constraints:
1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/


public class Solution {
    List<List<String>> result;
    HashMap<String, Boolean> dpDfs;

    public List<List<String>> findLadders(String start, String end, List<String> words) {
        this.result = new ArrayList<>();
        HashSet<String> set = new HashSet<>(words);
        if(!set.contains(end)) return result;
        set.add(start);

        // construct graph
        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, Integer> levelTracker = new HashMap<>();

        constructGraph(graph, levelTracker, set, start, end);
        /**
         * for word ladder simply return level.get(end)
         * return level.getOrDefault(end, 0);
         */

        // to print paths do DFS from start till level level.getOrDefault(end, 0)
        if(levelTracker.containsKey(end)) {
            this.dpDfs = new HashMap<>();
            dfs(graph, levelTracker.get(end), end, start, 1, new LinkedList<>());
        }
        return result;
    }

    private boolean dfs(HashMap<String, List<String>> graph, int maxLevel, String end, String start, int currLevel, LinkedList<String> paths) {
        paths.add(start);
        if (currLevel == maxLevel) {
            if (start.equals(end)) {
                result.add(new ArrayList<>(paths));
                paths.removeLast();
                return true;
            }
            paths.removeLast();
            return false;
        }

        boolean ans = false;
        for (String node : graph.get(start)) {
            if(!this.dpDfs.containsKey(node)) {
                boolean b = dfs(graph, maxLevel, end, node, currLevel + 1, paths);
                ans = ans || b;
                this.dpDfs.put(node, b);
            } else if(this.dpDfs.get(node)) {
                ans = true;
                dfs(graph, maxLevel, end, node, currLevel + 1, paths);
            }
        }
        paths.removeLast();
        this.dpDfs.put(start, ans);
        return this.dpDfs.get(start);
    }

    /**
     * @param graph stores graph representation
     * @param visited keeps the level of the each node in BFS
     * This graph only keeps forward nodes that will point to next level.
     */
    private void constructGraph(HashMap<String, List<String>> graph, HashMap<String, Integer> visited, HashSet<String> set, String start, String end) {
        // BFS for making graph
        Queue<String> queue = new LinkedList<>();
        visited.put(start, 1);
        queue.add(start);
        while (!queue.isEmpty()) {
            String s = queue.remove();
            graph.put(s, new ArrayList<>());
            int level = visited.get(s);

            List<String> list = graph.get(s);

            char[] ch = s.toCharArray();
            for(int i = 0; i < ch.length; i++) {
                char t = ch[i];
                for(char j = 'a'; j <= 'z'; j++) {
                    if(ch[i] == j) continue;
                    ch[i] = j;
                    String str = String.valueOf(ch);
                    if(set.contains(str)) {
                        int curLevel = visited.getOrDefault(str, 0);
                        if(curLevel == 0) {
                            visited.put(str, level + 1);
                            queue.add(str);
                            list.add(str);
                        } else if(curLevel > level) list.add(str);
                    }
                }
                ch[i] = t;
            }
        }
    }
}



/*
// TLE
// https://leetcode.com/problems/word-ladder-ii/discuss/40475/My-concise-JAVA-solution-based-on-BFS-and-DFS
// BFS + DFS

class Solution {
    Map<String, List<String>> connectedWordsMap = new HashMap<String, List<String>>(); // adjList in graph
    Map<String, Integer> distanceMap = new HashMap<String, Integer>(); // Distance of every node from the start node
    List<List<String>> ladders = new ArrayList<List<String>>();
    List<String> currLadder = new ArrayList<String>();
    String endWord;
    Set<String> wordSet;
        
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        this.endWord = endWord;
        this.wordSet = new HashSet<String>(wordList);
        if (!wordSet.contains(endWord)) {
            return new ArrayList<List<String>>();
        }
        
        distanceMap.put(beginWord, 0);
        Queue<String> wordsQueue = new LinkedList<String>();
        wordsQueue.add(beginWord);
        String currWord;
        int level = 0;
        int queueSize;
        boolean isCompleted = false;
        List<String> neighbours;
        
        for (String word : wordList) {
            connectedWordsMap.put(word, new ArrayList<String>());
        }
        while (!wordsQueue.isEmpty()) {                                     // bfs
            queueSize = wordsQueue.size();
            level++;
            while (queueSize-- > 0) {
                currWord = wordsQueue.remove();
                neighbours = getNeighbours(currWord);
                for (String neigh : neighbours) {
                    connectedWordsMap.get(currWord).add(neigh);             // main logic
                    if (!distanceMap.containsKey(neigh)) {
                        distanceMap.put(neigh, level);                      // main logic
                        if (neigh.equals(endWord)) {
                            isCompleted = true;
                        } else {
                            wordsQueue.add(neigh);
                        }
                    }
                }
            }
            if (isCompleted) {
                break;
            }
        }
        findLaddersUsingDfs(beginWord);                                     // dfs
        return ladders;
    }
    
    public List<String> getNeighbours(String currWord) {
        char wordArr[] = currWord.toCharArray();
        List<String> neighbours = new ArrayList<String>();
        char oldCh;
        String nextWord;
        
        for (int i = 0; i < currWord.length(); i++) {       // word length
            for (char ch = 'a'; ch <= 'z'; ch++) {          // 26
                oldCh = wordArr[i];
                wordArr[i] = ch;
                nextWord = String.valueOf(wordArr);         // word length
                if (wordSet.contains(nextWord)) {           // 1
                    neighbours.add(nextWord);
                }
                wordArr[i] = oldCh;
            }
        }
        return neighbours;
    }
    
    public void findLaddersUsingDfs(String currWord) {
        currLadder.add(currWord); 
        if (endWord.equals(currWord)) {
            ladders.add(new ArrayList<String>(currLadder));
        } else {
            List<String> neighbours = connectedWordsMap.get(currWord);
            for (String neigh : neighbours) {  
                if (distanceMap.get(neigh) == distanceMap.get(currWord) + 1) {
                    findLaddersUsingDfs(neigh);
                }
            }
        }
        currLadder.remove(currLadder.size() - 1);
    }
}
*/


/*
// TLE

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladderList = new ArrayList<List<String>>();
        Set<String> wordSet = new HashSet<String>(wordList);
        if (!wordSet.contains(endWord)) {
            return ladderList;
        }
        String currWord, nextWord;
        List<String> currList, nextList;
        int wordsQueueSize;
        boolean isCompleted = false;
        Queue<List<String>> wordsQueue = new LinkedList<List<String>>();        // bfs state: words list
        Set<String> currLevelVisited = new HashSet<String>();
        wordsQueue.add(Arrays.asList(beginWord));
        StringBuilder currStrBuilder;
            
        while (!wordsQueue.isEmpty()) {
            wordsQueueSize = wordsQueue.size();
            while (wordsQueueSize-- > 0) {
                currList = wordsQueue.remove();
                currWord = currList.get(currList.size() - 1);
                if (currWord.equals(endWord)) {
                    ladderList.add(currList);
                    isCompleted = true;
                    continue;
                }
                for (int i = 0; i < currWord.length(); i++)  {
                    currStrBuilder = new StringBuilder(currWord);
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        currStrBuilder.setCharAt(i, ch);                        // main logic
                        nextWord = currStrBuilder.toString();
                        if (wordSet.contains(nextWord)) {                       // main logic
                            currLevelVisited.add(nextWord);
                            nextList = new ArrayList<String>(currList);         // time consuming
                            nextList.add(nextWord);
                            wordsQueue.add(nextList);
                        }
                    }
                }
            }
            if (isCompleted) {
                break;
            }
            wordSet.removeAll(currLevelVisited);
            currLevelVisited.clear();
        }
        return ladderList;
    }
}
*/


/*
// TLE

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladderList = new ArrayList<List<String>>();
        if (!wordList.contains(endWord)) {
            return ladderList;
        }
        String star = "*";
        String tempWord, currWord, nextWord;
        List<String> mapList, currList, nextList;
        int wordsQueueSize;
        boolean isCompleted = false;
        
        Map<String, List<String>> wordsMap = new HashMap<String, List<String>>();
        Queue<List<String>> wordsQueue = new LinkedList<List<String>>();        // bfs state: words list
        Set<String> visited = new HashSet<String>();
        Set<String> currLevelVisited = new HashSet<String>();
        wordsQueue.add(Arrays.asList(beginWord));
        visited.add(beginWord);
        wordList.add(beginWord);
        
        for(int i = 0; i < wordList.size(); i++) {
            currWord = wordList.get(i);
            for(int j = 0; j < currWord.length(); j++) {
                tempWord = currWord.substring(0, j) + star + currWord.substring(j + 1, currWord.length());
                mapList = wordsMap.get(tempWord);
                if (mapList == null) {
                    mapList = new ArrayList<String>();
                    wordsMap.put(tempWord, mapList);
                }
                mapList.add(currWord);
            }
        }
        
        while (!wordsQueue.isEmpty()) {
            wordsQueueSize = wordsQueue.size();
            while (wordsQueueSize-- > 0) {
                currList = wordsQueue.remove();
                currWord = currList.get(currList.size() - 1);
                if (currWord.equals(endWord)) {
                    ladderList.add(currList);
                    isCompleted = true;
                    continue;
                }
                for (int i = 0; i < currWord.length(); i++)  {
                    tempWord = currWord.substring(0, i) + star + currWord.substring(i+1, currWord.length());
                    mapList = wordsMap.get(tempWord);
                    for (int j = 0; j < mapList.size(); j++) {
                        nextWord = mapList.get(j);
                        if (!visited.contains(nextWord)) {
                            currLevelVisited.add(nextWord);
                            nextList = new ArrayList<String>(currList);        // time consuming
                            nextList.add(nextWord);
                            wordsQueue.add(nextList);
                        }
                    }
                }
            }
            if (isCompleted) {
                break;
            }
            visited.addAll(currLevelVisited);
            currLevelVisited.clear();
        }
        return ladderList;
    }
}
*/