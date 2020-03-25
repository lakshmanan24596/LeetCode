/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].
*/

class Solution 
{   
//     int[] cost;
//     public int minCostClimbingStairs(int[] cost) 
//     {
//         this.cost = cost;
//         return recur(-1);      
//     }
    
//     public int recur(int index)
//     {
//         if(index >= cost.length)
//             return 0;
        
//         int val1 = recur(index + 1);
//         int val2 = recur(index + 2);
        
//         int currVal = (index == -1) ? 0 : cost[index];
        
//         return Math.min(val1, val2) + currVal;               // post order
//     }
    
    public int minCostClimbingStairs(int[] cost) 
    {
        int[] DP = new int[cost.length + 1];                    // we can also change it in original array itself
        
        DP[0] = cost[0];
        DP[1] = cost[1];
        
        for(int i = 2; i < cost.length; i++)
        {
            DP[i] = Math.min(DP[i-1], DP[i-2]) + cost[i];       // main logic
        }
        
        return Math.min(DP[cost.length-1], DP[cost.length-2]);     
    }
    
    
    // change in original array.. O(1) space
    
    // public int minCostClimbingStairs(int[] cost) 
    // {
    //     for(int i = 2; i < cost.length; i++) 
    //     {
    //         cost[i] += Math.min(cost[i - 1], cost[i - 2]);
    //     }
    //     return Math.min(cost[cost.length - 1], cost[cost.length - 2]); 
    // }
}