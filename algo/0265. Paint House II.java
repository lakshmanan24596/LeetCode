/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
Return the minimum cost to paint all houses.

Example 1:
Input: costs = [[1,5,3],[2,9,4]]
Output: 5
Explanation:
Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.

Example 2:
Input: costs = [[1,3],[2,4]]
Output: 5

Constraints:
costs.length == n
costs[i].length == k
1 <= n <= 100
1 <= k <= 20
1 <= costs[i][j] <= 20

Follow up: Could you solve it in O(nk) runtime?
*/




/*
    1) brute   --> time: k^n,   space: n
    2) DP memo --> time: n*k*k, space: n*k
    
    time optimization:
        we need minCost
        in tabulation method, we iterate over all colors and pick minimum
        instead of this iteration, we can make a note of firstMin, secondMin in prevRow --> main logic
        refer image in https://leetcode.com/problems/paint-house-ii/solution/
        time = n * k
        
    space optimization:
        1) curr houseIndex depends only on prev houseIndex
           space = k
        2) use input array as DP array
           space = 1
        3) we just need firstMin, secondMin and preserve the input array
           space = 1
        
    After all optimizations, 
        time = n * k
        space = 1
*/


/*
// DP memo without any optimization
class Solution {
    int[][] costs;
    int n, k;
    Integer[][] cache;
    
    public int minCostII(int[][] costs) {
        this.costs = costs;
        n = costs.length;
        k = costs[0].length;
        cache = new Integer[n][k + 1];
        return minCostIIUtil(0, 0);
    }
    
    public int minCostIIUtil(int houseIndex, int prevColor) {
        if (houseIndex == n) {
            return 0;
        }
        if (cache[houseIndex][prevColor] != null) {
            return cache[houseIndex][prevColor];
        }
        int minCost = Integer.MAX_VALUE;
        int currCost;
        
        for (int color = 1; color <= k; color++) {
            if (color != prevColor) {
                currCost = costs[houseIndex][color-1] + minCostIIUtil(houseIndex + 1, color);       // main logic
                minCost = Math.min(minCost, currCost);
            }
        }
        return cache[houseIndex][prevColor] = minCost;
    }
}
*/


// DP tabulation --> time and space optimized, by preserving input array and storing min values alone
class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int prevFirstMin, prevSecondMin, prevFirstMinColor;
        int currFirstMin = Integer.MAX_VALUE, currSecondMin = Integer.MAX_VALUE, currFirstMinColor = -1;
        int currCost;
        
        for (int house = 0, color = 0; color < k; color++) {
            currCost = costs[house][color];
            
            if (currCost < currFirstMin) { 
                currSecondMin = currFirstMin;
                currFirstMin = currCost;
                currFirstMinColor = color;
            } 
            else if (currCost < currSecondMin) {
                currSecondMin = currCost;
            }
        }
        
        for (int house = 1; house < n; house++) {
            prevFirstMin = currFirstMin;
            prevSecondMin = currSecondMin;
            prevFirstMinColor = currFirstMinColor;
            currFirstMin = Integer.MAX_VALUE;
            currSecondMin = Integer.MAX_VALUE;
            
            for (int color = 0; color < k; color++) {
                currCost = costs[house][color];
                
                if (color != prevFirstMinColor) {       // use: prevFirstMin, prevSecondMin, prevFirstMinColor
                    currCost += prevFirstMin;
                } else {
                    currCost += prevSecondMin;
                }
                
                if (currCost < currFirstMin) {          // track: currFirstMin, currSecondMin, currFirstMinColor
                    currSecondMin = currFirstMin;
                    currFirstMin = currCost;
                    currFirstMinColor = color;
                } 
                else if (currCost < currSecondMin) {
                    currSecondMin = currCost;
                }
            }
        }
        return currFirstMin;
    }
}
