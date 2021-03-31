/*
You are given two groups of points where the first group has size1 points, the second group has size2 points, and size1 >= size2.
The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j] is the cost of connecting point i of the first group and point j of the second group. 
The groups are connected if each point in both groups is connected to one or more points in the opposite group. 
In other words, each point in the first group must be connected to at least one point in the second group, and each point in the second group must be connected to at least one point in the first group.
Return the minimum cost it takes to connect the two groups.

Example 1:
Input: cost = [[15, 96], [36, 2]]
Output: 17
Explanation: The optimal way of connecting the groups is:
1--A
2--B
This results in a total cost of 17.

Example 2:
Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
Output: 4
Explanation: The optimal way of connecting the groups is:
1--A
2--B
2--C
3--A
This results in a total cost of 4.
Note that there are multiple points connected to point 2 in the first group and point A in the second group. This does not matter as there is no limit to the number of points that can be connected. We only care about the minimum total cost.

Example 3:
Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
Output: 10
 
Constraints:
size1 == cost.length
size2 == cost[i].length
1 <= size1, size2 <= 12
size1 >= size2
0 <= cost[i][j] <= 100
*/


/*
    Problem: In a given 2D matrix, we need to pick atleast 1 element in each row and each col 
    https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/discuss/855041/C%2B%2BPython-DP-using-mask

    Logic: DP + bitmask
    Implementation:
        1) In each row, we can pick any element recursively by trying all possible combinations.
        2) After step 1, atleast 1 element in chosen in all rows. Now we need to check missed out columns and chose minVal in that column.
*/

class Solution {
    List<List<Integer>> cost;
    int rows, cols;
    int[] colMin;
    Integer[][] DP;
    
    public int connectTwoGroups(List<List<Integer>> cost) {
        this.cost = cost;
        rows = cost.size();
        cols = cost.get(0).size();
        DP = new Integer[rows + 1][1 << cols];
        colMin = new int[cols];
        
        for (int i = 0; i < cols; i++) { 
            int minVal = Integer.MAX_VALUE;
            for (int j = 0; j < rows; j++) {
                minVal = Math.min(minVal, cost.get(j).get(i));
            }
            colMin[i] = minVal;                     // store minVal in each column
        }
        return getMinCost(0, 0);
    }
    
    public int getMinCost(int level, int bitVal) {
        if (DP[level][bitVal] != null) {
            return DP[level][bitVal];
        }
        if (level == rows) {
            // All rows (group 1) are filled at this point. Now we need to fill missed out columns (group 2).
            int colCost = 0;
            for (int i = 0; i < cols; i++) {
                if ((bitVal & (1 << i)) == 0) {
                    colCost += colMin[i];
                }
            }
            return DP[level][bitVal] = colCost;
        }
        int minCost = Integer.MAX_VALUE;
        int currCost;
        int nextBitVal;
        
        for (int i = 0; i < cols; i++) {
            nextBitVal = bitVal | (1 << i);
            currCost = getMinCost(level + 1, nextBitVal);
            currCost += cost.get(level).get(i);
            minCost = Math.min(currCost, minCost);
        }
        return DP[level][bitVal] = minCost;
    }
}