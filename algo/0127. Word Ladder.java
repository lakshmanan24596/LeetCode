/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
             return its length 5.

Example 2:
Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, 
             therefore no possible transformation.
*/


/*
    Brute force ::: 
         time = (N^2 * M) and space = 1
         without preprocessing, we will directly do BFS
    
    BFS :::
         step 1 ::: preprocess all words = time = N*M*M, space = N*M*M
         step 2 ::: do BFS traversal by incrementing level = time = N*M*M, space = N*M
         Total  ::: Time: N*M*M, Space: N*M*M
*/
class Solution 
{          
    public int ladderLength(String beginWord, String endWord, List<String> wordList) 
    {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        // logic --> key = h*t and value = hit, hot,...
        Map<String, List<String>> map = new HashMap<String, List<String>>();   // N*M*M space
        String star = "*";
        wordList.add(beginWord);
        
        // Time for preprocessing is O(N*M*M)
        for(String currWord : wordList)                             // N
        {
            for(int i = 0; i < currWord.length(); i++)              // M
            {
                String newWord = currWord.substring(0, i) + star + currWord.substring(i+1, currWord.length());  // M
                
                List<String> list = map.get(newWord);
                if(list == null)
                {
                    list = new ArrayList<String>();
                    map.put(newWord, list);
                }    
                list.add(currWord);
            }
        }
        
        Set<String> visited = new HashSet<String>();                // N*M space
        visited.add(beginWord);
        Queue<String> queue = new LinkedList<String>();             // N*M sapce
        queue.add(beginWord);
        int level = 0;

        while(!queue.isEmpty())                                     // N            
        {
            level++;
            int size = queue.size();

            while(size-- > 0)
            {
                String currWord = queue.remove();
                if(currWord.equals(endWord))
                    return level;

                for(int i = 0; i < currWord.length(); i++)          // M    
                {
                    String newWord = currWord.substring(0, i) + star + currWord.substring(i+1, currWord.length());           
                    List<String> list = map.get(newWord);
                    for(String nextWord : list)                     // M
                    {
                        if(!visited.contains(nextWord))
                        {
                            queue.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
            }         
        }
        return 0; 
    }
}

/*
Bidirectional BFS:
     one bfs from beginWord and one bfs from endWord
     if we visit the same node processed in other bfs, then we found the answer

class Solution {

  private int L = 0;
  private HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();
    
  public int ladderLength(String beginWord, String endWord, List<String> wordList) 
  {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    this.L = beginWord.length();

    wordList.forEach(
        word -> {
          for (int i = 0; i < L; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            ArrayList<String> transformations =
                this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
            transformations.add(word);
            this.allComboDict.put(newWord, transformations);
          }
        });

    Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
    Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();
    Q_begin.add(new Pair(beginWord, 1));
    Q_end.add(new Pair(endWord, 1));

    HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
    HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
    visitedBegin.put(beginWord, 1);
    visitedEnd.put(endWord, 1);

    while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
        
      int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
      if (ans > -1) {
        return ans;
      }

      ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
      if (ans > -1) {
        return ans;
      }
    }

    return 0;
  }
    
  private int visitWordNode(
      Queue<Pair<String, Integer>> Q,
      HashMap<String, Integer> visited,
      HashMap<String, Integer> othersVisited) 
  {
    Pair<String, Integer> node = Q.remove();
    String word = node.getKey();
    int level = node.getValue();

    for (int i = 0; i < this.L; i++) {
      String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

      for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
        if (othersVisited.containsKey(adjacentWord)) {                      // MAIN LOGIC
          return level + othersVisited.get(adjacentWord);
        }

        if (!visited.containsKey(adjacentWord)) {
          visited.put(adjacentWord, level + 1);
          Q.add(new Pair(adjacentWord, level + 1));
        }
      }
    }
    return -1;
  }
}
*/