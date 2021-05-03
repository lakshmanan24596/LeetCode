/*
There is a new alien language that uses the English alphabet. 
However, the order among the letters is unknown to you.
You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. 
If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. 
If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.


Example 1:
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:
Input: words = ["z","x"]
Output: "zx"

Example 3:
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
*/



/*
    logic:
        basic topological sorting in graph
    time: 
        word * wordLength --> to create a graph
        V+E --> for topological sorting
        where V = 26, E = 26 * 26
    space:
        V+E --> for graph adjList
        V --> for validLetters, inDegree
*/

class Solution {
    final int lettersSize = 26;
    Graph graph;
    boolean[] validLetters;
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        this.graph = new Graph(lettersSize);
        this.validLetters = new boolean[lettersSize];
        boolean edgeAdded;
        
        for (int i = 0; i < words.length; i++) {
            edgeAdded = false;
            for (int j = 0; j < words[i].length(); j++) {
                int v = words[i].charAt(j) - 'a';           // v = letter in curr word
                validLetters[v] = true;
                
                if (i != 0 && !edgeAdded && words[i - 1].length() > j) {    
                    int u = words[i - 1].charAt(j) - 'a';   // u = letter in prev word
                    if (u != v) {                           // find the first non match letter
                        graph.addEdge(u, v);
                        edgeAdded = true;
                    }
                }
            }
            if (i != 0 && edgeAdded == false && words[i - 1].length() > words[i].length()) {
                return "";  // s.length() > t.length(), so return invalid input
            } 
        }
        return topologicalSorting();
    }
    
    public String topologicalSorting() {
        StringBuilder alienOrder = new StringBuilder();
        Queue<Integer> queue = new LinkedList<Integer>();
        int totalNodesCount = 0;
        
        for (int i = 0; i < lettersSize; i++) {
            if (validLetters[i]) {
                totalNodesCount++;
                if (graph.inDegree[i] == 0) {
                    queue.add(i);                           // present in alienDict and indegree = 0
                }
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            alienOrder.append((char) (curr + 'a'));
            
            Set<Integer> neighbours = graph.adjList[curr];
            for (int neigh : neighbours) {
                graph.inDegree[neigh]--;
                if (graph.inDegree[neigh] == 0) {
                    queue.add(neigh);
                }
            }
        }
        if (totalNodesCount != alienOrder.length()) {       // cycle in the graph
            return "";
        } else {
            return alienOrder.toString();
        }
    }
}

class Graph {
    Set<Integer>[] adjList;     // we can also create a boolean "adjacency matrix" of size 26 * 26
    int[] inDegree;
    
    Graph(int n) {
        adjList = new HashSet[n];
        inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<Integer>();
        }
    }
    
    public void addEdge(int u, int v) {
        if (adjList[u].add(v)) {
            inDegree[v]++;
        }
    }
}