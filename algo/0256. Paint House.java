/*
There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.

Example 1:
Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.

Example 2:
Input: costs = [[7,6,2]]
Output: 2

Constraints:
costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20
*/



/*
    DP memo
    states:
        1) house index = n
        2) prev color  = 3
        
    time: (n*3)*3
    space: n*3
    
    tabulation space optimization can be done to reduce space from n*3 to 3
    because curr state depends only on prev state
*/

/*
class Solution {
    int[][] costs;
    Integer[][] costMemo;
    int n;
    
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        this.n = costs.length;
        this.costs = costs;
        this.costMemo = new Integer[n][4];
        return paintWithMinCost(0, 0);
    }
    
    public int paintWithMinCost(int house, int prevColor) {
        if (house == n) {
            return 0;
        }
        if (costMemo[house][prevColor] != null) {
            return costMemo[house][prevColor];
        }
        int currCost;
        int minCost = Integer.MAX_VALUE;
        
        for (int color = 1; color <= 3; color++) {
            if (color != prevColor) {
                currCost = costs[house][color-1] + paintWithMinCost(house + 1, color);
                minCost = Math.min(minCost, currCost);
            }
        }
        costMemo[house][prevColor] = minCost;
        return minCost;
    }
}
*/


// tabulation space optmization of above solution (time: n*3*3, space: 2*3)
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int minCost = Integer.MAX_VALUE;
        int currCost;
        int[] prevDP = new int[3];
        int[] currDP;
        prevDP = costs[0];
        
        for (int house = 1; house < n; house++) {
            currDP = new int[3];
            Arrays.fill(currDP, Integer.MAX_VALUE);
            
            for (int color = 0; color < 3; color++) {
                for (int prevColor = 0; prevColor < 3; prevColor++) {
                    if (color != prevColor) {
                        currCost = costs[house][color] + prevDP[prevColor];
                        currDP[color] = Math.min(currDP[color], currCost);
                    }
                }
            }
            prevDP = currDP;
        }
        
        for (int color = 0; color < 3; color++) {
            minCost = Math.min(minCost, prevDP[color]);
        }
        return minCost;
    }
}
