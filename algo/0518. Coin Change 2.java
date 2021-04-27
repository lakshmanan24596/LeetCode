/*
You are given coins of different denominations and a total amount of money. 
Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10] 
Output: 1

Note:
You can assume that
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
*/



/*
    Approach-1: 
        DP states: amount, coinIndex
        time: O((amount * coins) * coins)
        space: O(amount * coins)
*/
/*
class Solution 
{
    int[] coins;
    Integer[][] DP;
    public int change(int amount, int[] coins) 
    {
        this.coins = coins;   
        this.DP = new Integer[amount+1][coins.length];
        return dfs(amount, 0);
    }
    
    public int dfs(int amount, int startIndex)
    {
        if (amount == 0) {
            return 1;
        }
        if(DP[amount][startIndex] != null) {
            return DP[amount][startIndex];
        }
        int output = 0;
        
        for(int i = startIndex; i < coins.length; i++) {
            if(coins[i] <= amount) {
                output += dfs(amount - coins[i], i);
            }
        }
        return DP[amount][startIndex] = output;
    }
}
*/


/*
    Approach-2:
        Logic: same as above solution with optimized time complexity, using 0/1 knapsack approach
        DP states: amount, coinIndex
        time: O(amount * coins)
        space: O(amount * coins)
*/
class Solution {
    int[] coins;
    Integer[][] DP;
    
    public int change(int amount, int[] coins) {
        this.coins = coins;   
        this.DP = new Integer[amount+1][coins.length];
        return dfs(amount, 0);
    }
    
    public int dfs(int amount, int startIndex) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || startIndex >= coins.length) {
            return 0;
        }
        if(DP[amount][startIndex] != null) {
            return DP[amount][startIndex];
        }
        
        int pick = dfs(amount - coins[startIndex], startIndex);
        int dontPick = dfs(amount, startIndex + 1);
            
        int output = pick + dontPick;
        return DP[amount][startIndex] = output;
    }
}


/* 
    Approach-3:
        Logic: convert approach-2 to a tabulation space optimized solution
        time: O(amount * coins)
        space: O(amount)
        because currState depends only on prevState of coinIndex
        
        Note: dont intercharge both FOR loops
        Unbound knapsack: for each coin, we can put as many times as we want.
*/
/*
class Solution
{
    public int change(int amount, int[] coins) 
    {
        int[] DP = new int[amount+1];
        DP[0] = 1;
        
        for(int coin : coins) {
            for(int amt = coin; amt <= amount; amt++) {
                DP[amt] += DP[amt - coin];
            }
        }
        return DP[amount];
    }
}
*/