/*
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
Return the minimum number of flips to make S monotone increasing.

Example 1:
Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.

Example 2:
Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.
 
Note:
1 <= S.length <= 20000
S only consists of '0' and '1' characters.
*/


/*
    brute recursion : 2^n, n (each char can be flipped or dont flipped)
    memorization: 2n, 2n
    tabulation: 2n, 2n
    tabulation space optimized: 2n, 2
*/

// memorizarion
class Solution 
{
    Integer[][] DP;
    String str;
    int n;
    
    public int minFlipsMonoIncr(String S)                       // similar to 801. Minimum Swaps To Make Sequences Increasing
    {
        str = S;
        n = str.length();
        DP = new Integer[n][2];
        return recur(0, 0);
    }
    
    public int recur(int level, int prevState)                  // Time = Space = size of DP ==> 2n
    {
        if(level == n) {
            return 0;
        }
        if(DP[level][prevState] != null) {
            return DP[level][prevState];
        }
        
        int dontFlip = n, flip = n;                             // similar to 0/1 knapsack
        int currState = str.charAt(level) - '0';
        
        if(prevState == 1)
        {
            if(currState == 0) {
                flip = 1 + recur(level + 1, 1);
            }
            else {
                dontFlip = recur(level + 1, 1);
            }
        }
        else 
        {
            dontFlip = recur(level + 1, currState);
            flip = 1 + recur(level + 1, currState == 0 ? 1 : 0);
        }  
        
        return DP[level][prevState] = Math.min(dontFlip, flip);
    }
}

/*
    tabulation space optimized
        https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/solution/
        refer solutions tab  
*/    


/*
// 1 ms most optimal solution
class Solution {
    public int minFlipsMonoIncr(String S) {
        int oneCnt = 0;
        int flips = 0;
        
        for (char c : S.toCharArray()) {
            if (c == '1') {
                oneCnt++;
            } else {
                if (oneCnt > 0) { // has one before zero
                    flips++; // flip needs to happen
                    oneCnt--;
                }
            }
        }
        return flips;
    }
}
*/
