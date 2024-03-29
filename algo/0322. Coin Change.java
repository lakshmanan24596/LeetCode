/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/

class Solution {
  int[] coins;
  Integer[] DP;

  public int coinChange(int[] coins, int amount) {
    this.coins = coins;
    this.DP = new Integer[amount+1];
    DP[0] = 0;      // base case for recursion: if (amount == 0) then return 0;
    int output = coinChangeUtil(amount);
    return ((output != Integer.MAX_VALUE - 1) ? output : -1);
  }

  public int coinChangeUtil(int amount) {
    if(DP[amount] != null) {
      return DP[amount];
    }
        
    int minVal = Integer.MAX_VALUE - 1;
    for(int i = 0; i < coins.length; i++) {
        if(coins[i] <= amount) {
            minVal = Math.min(minVal, 1 + coinChangeUtil(amount - coins[i]));
        }
    }
    return DP[amount] = minVal;
  }
}  

 
// tabulation
// time = nm and space = m

//     public int coinChange(int[] coins, int amount) 
//     {
//         //Arrays.sort(coins);               // no need sorting
        
//         int[] DP = new int[amount+1];              
//         for(int i = 1; i <= amount; i++)    // DP[0] = 0; so start from 1
//             DP[i] = Integer.MAX_VALUE - 1;
        
//         for(int i = 0; i < coins.length; i++)
//             for(int j = coins[i]; j <= amount; j++)
//                 DP[j] = Math.min(DP[j], 1 + DP[j - coins[i]]);
    
//         return ((DP[amount] != Integer.MAX_VALUE - 1) ? DP[amount] : -1);
//     }    
// }
