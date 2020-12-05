/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
*/


/*
    1) recursion
    2) tabulation
    3) tabulation space optimized
    4) tabulation space optimized 1-D array
    
    // we can also use LCS. Formula ==> m + n − 2 ∗ lcs.
*/

/*
// recursion
class Solution 
{
    public int minDistance(String word1, String word2)  // Time: 2 ^ max(m,n)
    {
        int m = word1.length();
        int n = word2.length();
        
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        
        // memorization --> need to create a DP with states: word1 and word2
        
        if(word1.charAt(0) == word2.charAt(0)) {
            return minDistance(word1.substring(1, m), word2.substring(1, n));
        }
        else {
            int dist1 = 1 + minDistance(word1.substring(1, m), word2.substring(0, n));  // delete starting char in word1
            int dist2 = 1 + minDistance(word1.substring(0, m), word2.substring(1, n));  // delete starting char in word2
            return Math.min(dist1, dist2);
        }
    }
}
*/

/*
// tabulation
class Solution 
{
    public int minDistance(String word1, String word2)  // Time: m*n, Space: m*n
    {
        int m = word1.length();
        int n = word2.length();
        
        int[][] DP = new int[m+1][n+1];
        
        for(int i = 1; i <= m; i++) {   // word2 empty
            DP[i][0] = i;
        }
        
        for(int i = 1; i <= n; i++) {   // word1 empty
            DP[0][i] = i;
        }
        
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    DP[i][j] = DP[i-1][j-1];
                }
                else {
                    int dist1 = 1 + DP[i-1][j];
                    int dist2 = 1 + DP[i][j-1]; 
                    DP[i][j] = Math.min(dist1, dist2);
                }
            }
        }
        return DP[m][n];
    }
}
*/

/*
// tabulation space optimized
class Solution 
{
    public int minDistance(String word1, String word2)  // Time: m*n, Space: 2*Math.min(m, n)
    {
        int m = word1.length();
        int n = word2.length();
        
        if(n > m) {
            return minDistance(word2, word1);   // always word2 should be smaller to reduce space complexity  
        }
        
        int[][] DP = new int[2][n+1];           // space optimization O(2 * min(m, n))
        
        for(int i = 1; i <= n; i++) {
            DP[0][i] = i;
        }
        
        for(int i = 1; i <= m; i++)
        {
            DP[1][0] = i;
            for(int j = 1; j <= n; j++)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    DP[1][j] = DP[0][j-1];
                }
                else {
                    int dist1 = 1 + DP[0][j];
                    int dist2 = 1 + DP[1][j-1]; 
                    DP[1][j] = Math.min(dist1, dist2);
                }
            }
            DP[0] = DP[1].clone();  // reset
        }
        return DP[1][n];
    }
} 
*/


// tabulation space optimized 1-D array
class Solution 
{
    public int minDistance(String word1, String word2)  // Time: m*n, Space: Math.min(m, n)
    {
        int m = word1.length();
        int n = word2.length();
        
        if(n > m) {
            return minDistance(word2, word1);   // always word2 should be smaller to reduce space complexity  
        }
        
        int[] DP = new int[n+1];
        
        for(int i = 1; i <= n; i++) {
            DP[i] = i;
        }
        
        int prev, temp;
        
        for(int i = 1; i <= m; i++)
        {
            prev = DP[0];
            DP[0] = i;
            
            for(int j = 1; j <= n; j++)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    temp = DP[j];
                    DP[j] = prev;
                    prev = temp;
                }
                else {
                    int dist1 = 1 + DP[j];
                    int dist2 = 1 + DP[j-1];
                    
                    temp = DP[j];
                    DP[j] = Math.min(dist1, dist2);
                    prev = temp;
                }
            }
        }
        return DP[n];
    }
} 
