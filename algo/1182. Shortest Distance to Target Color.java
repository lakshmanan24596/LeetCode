/*
You are given an array colors, in which there are three colors: 1, 2 and 3.
You are also given some queries. Each query consists of two integers i and c, 
return the shortest distance between the given index i and the target color c. 
If there is no solution return -1.

Example 1:
Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation: 
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).

Example 2:
Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.
 
Constraints:
1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3
*/



/*
    1) brute force
        time: q*n
        space: 1
        
    2) binary search
        time: n + q*logn
        space: n
        Implementation for ex-1:
            color 1 --> 0, 1, 3
            color 2 --> 2, 5, 6
            color 3 --> 4, 7, 8
            now, for each query, do binary search
        
    3) O(1) lookup search
        time: n*3 + q
        space: n*3
        lookup table: (shortest distance array of size n*3)
            color 1 --> 0, 0, 1, 0, 1, 2, 3, 4, 5
            color 2 --> 2, 1, 0, 1, 1, 0, 0, 1, 2
            color 3 --> 4, 3, 2, 1, 0, 1, 1, 0, 0
*/

// lookup search
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int noOfColors = 3;
        int n = colors.length;
        int[][] distance = new int[noOfColors][n];                                      // lookup table
        List<Integer> outputList = new ArrayList<Integer>();
        int color, index, shortestDist;
        
        for (color = 0; color < noOfColors; color++) {
            distance[color] = createLookupTable(colors, color + 1);                     // pre-process
        }
        for (int[] query : queries) {
            color = query[1] - 1;
            index = query[0];
            shortestDist = (distance[color][index] >= n) ? -1 : distance[color][index]; // O(1) query
            outputList.add(shortestDist);         
        }
        return outputList;
    }
    
    public int[] createLookupTable(int[] colors, int color) {
        int n = colors.length;
        int[] lookupTable = new int[n];
        
        Arrays.fill(lookupTable, n);
        if (colors[0] == color) {
            lookupTable[0] = 0;
        }
        
        for (int i = 1; i < n; i++) {                     // left to right iteration
            lookupTable[i] = (colors[i] == color) ? 0 : lookupTable[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {                // right to left iteration
            lookupTable[i] = Math.min(lookupTable[i], lookupTable[i + 1] + 1);
        }
        return lookupTable;
    }
}