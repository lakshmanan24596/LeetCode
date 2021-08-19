/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Example 4:
Input: prices = [1]
Output: 0

Constraints:
1 <= prices.length <= 105
0 <= prices[i] <= 105
*/


 /* 
    brute force --> O(n*n)
    Max profit with at most two transactions =
    MAX {max profit with one transaction and subarray price[0..i] +
         max profit with one transaction and subarray price[i+1..n-1]  }
    i varies from 0 to n-1. 


    1) O(n*n)
    2) O(n), O(n)
    3) O(n), O(1)
*/

//  O(n), O(n)
class Solution 
{
    public int maxProfit(int[] prices) 
    {
        int n = prices.length;
        if(n < 2) {
            return 0;
        }
        int[] profit = new int[n];
        
        int minPrice = prices[0];
        for(int i = 1; i < n; i++)      // left to right
        {
            minPrice = Math.min(minPrice, prices[i]);
            profit[i] = Math.max(profit[i-1], prices[i]-minPrice);
        }
        
        int maxPrice = prices[n-1];
        for(int i = n - 2; i >= 0; i--) // right to left
        {
            maxPrice = Math.max(maxPrice, prices[i]);
            profit[i] = Math.max(profit[i+1], profit[i]+(maxPrice-prices[i]));
        }
        return profit[0];
    }
}


/*
// O(n), O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;

        for (int price : prices) {
            buy1 = Math.min(buy1, price);
            sell1 = Math.max(sell1, price - buy1);
        
            buy2 = Math.min(buy2, price - sell1);       // consider profit gained in first transaction
            sell2 = Math.max(sell2, price - buy2);
        }
        return sell2;
    }
}
*/