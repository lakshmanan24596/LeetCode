/*
There is an integer array nums that consists of n unique elements, but you have forgotten it. 
However, you do remember every pair of adjacent elements in nums.
You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. 
The pairs can appear in any order.
Return the original array nums. If there are multiple solutions, return any of them.

Example 1:
Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.

Example 2:
Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.

Example 3:
Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]

Constraints:
nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
*/



/*
    time: 3n
    space: 3n
    logic: find head and do DFS
*/

class Solution {
    Map<Integer, List<Integer>> adjMap;                     // adjList in graph
    Set<Integer> isAlreadyRestored;                         // visited array in dfs
    int[] restoredArray;                                    // output array
    int noOfRestoredElements = 0;
    
    public int[] restoreArray(int[][] adjacentPairs) {
        int size = adjacentPairs.length + 1;
        this.restoredArray = new int[size];
        this.adjMap = new HashMap<Integer, List<Integer>>();
        this.isAlreadyRestored = new HashSet<Integer>();
        int[] adjacentPair;
        List<Integer> neighbours;
        
        for (int i = 0; i < size - 1; i++) {
            adjacentPair = adjacentPairs[i];
            addNeighbours(adjacentPair[0], adjacentPair[1]);
            addNeighbours(adjacentPair[1], adjacentPair[0]);
        }
        int head = findHead();                               // find head
        fillRestoredArray(head);                             // do DFS starting from head
        return restoredArray;
    }
    
    public void addNeighbours(int a, int b) {
        List<Integer> neighbours = adjMap.get(a);
        if (neighbours == null) {
            neighbours = new ArrayList<Integer>();
            adjMap.put(a, neighbours);
        }
        neighbours.add(b);
    }
    
    public int findHead() {
        for (Map.Entry<Integer, List<Integer>> node : adjMap.entrySet()) {
            if (node.getValue().size() == 1) {              // head (and tail) has only 1 adjacent element
                return node.getKey();
            }
        }
        throw new AssertionError();
    }
    
    public void fillRestoredArray(int curr) {               // DFS
        restoredArray[noOfRestoredElements++] = curr;
        isAlreadyRestored.add(curr);
        List<Integer> neighbours = adjMap.get(curr);
        
        for (int neigh : neighbours) {
            if (!isAlreadyRestored.contains(neigh)) {
                fillRestoredArray(neigh);
                break;
            }
        }
    }
}