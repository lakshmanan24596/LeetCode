/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
    Insert a character
    Delete a character
    Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

class Solution 
{
    public int minDistance(String word1, String word2) 
    {
        return minDistance(word1, word2, word1.length(), word2.length()); 
    }
    
//     recursion.. Time = Math.pow(3, Math.min(len1, len2));
    
//     public int minDistance(String word1, String word2, int len1, int len2)
//     {
//         if(len1 == 0)
//             return len2;
//         if(len2 == 0)
//             return len1;
            
//         if(word1.charAt(len1-1) == word2.charAt(len2-1))
//             return minDistance(word1, word2, len1-1, len2-1);
            
//         int insert = minDistance(word1, word2, len1, len2-1);
//         int replace = minDistance(word1, word2, len1-1, len2-1);
//         int remove = minDistance(word1, word2, len1-1, len2);
        
//         return 1 + Math.min(Math.min(insert, replace), remove);
//     }
    
    // horse, ros
    // insert r--> rhorse, ros
    // so recur: horse, os
    // ie; 5,3 = recur(5,2)
    
    
    // DP - tabulation    
    public int minDistance(String word1, String word2, int len1, int len2)
    {
        int[][] DP = new int[len1+1][len2+1];
        
        for(int i=0; i<=len1; i++)
            DP[i][0] = i;
        
        for(int i=0; i<=len2; i++)
            DP[0][i] = i;
        
        for(int i=1; i<=len1; i++)
        {
            for(int j=1; j<=len2; j++)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    DP[i][j] = DP[i-1][j-1];
                else
                    DP[i][j] = 1 + Math.min(Math.min(DP[i][j-1], DP[i-1][j-1]), DP[i-1][j]);
            }
        }
        return DP[len1][len2];
    }
}