/*
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. 
The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 

Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.
*/



/*
    DP memo
    states: n, totalA, contL
    
    time: (n * 2 * 3) * 3 ==> n
    space: n * 2 * 3
*/
class Solution {
    int mod = 1_000_000_007;
    Integer[][][] DP;
    
    public int checkRecord(int n) {
        DP = new Integer[n+1][2][3];
        return dfs(n, 0, 0);
    }
    
    public int dfs(int n, int totalA, int contL) {
        if (n == 0) {
            return 1;
        }
        if (DP[n][totalA][contL] != null) {
            return DP[n][totalA][contL];
        }
        int output = 0;
        
        if (totalA < 1) {
            output += dfs(n - 1, totalA + 1, 0);            // pick A
            output %= mod;
        }
        if (contL < 2) {
            output += dfs(n - 1, totalA, contL + 1);        // pick L
            output %= mod;
        }
        output += dfs(n - 1, totalA, 0);                    // pick P
        output %= mod;
        return DP[n][totalA][contL] = output;
    }
}