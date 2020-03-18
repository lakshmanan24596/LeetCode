/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example:
Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

class Solution 
{
    // 1) recursion  :   n! * n   ==> n!
    // 2) DP         :   n^2 * n  ==> n^3
    // 3) optimal DP :   n^2      ==> n^2
    
    
    // n^2 solution
    public int minCut(String s)
    {
        if(s == null || s.length() == 1)
            return 0;

        boolean[][] DP1 = new boolean[s.length()][s.length()];
        int[] DP2 = new int[s.length()]; 

        // fill only top right diagnol.. exactly similar to longest palindromic substring problem
        for(int i = 0; i < s.length(); i++)     
        {
            for(int start = 0, end = i; end < s.length(); start++, end++)
            {
                if(start == end)                            // string length 1
                    DP1[start][end] = true;

                else if(end-start == 1)                     // string length 2
                    DP1[start][end] = (s.charAt(start) == s.charAt(end)) ? true : false;

                else
                    DP1[start][end] = ((s.charAt(start) == s.charAt(end)) && DP1[start+1][end-1]) ? true : false;
            }
        }

        for(int i = 0; i < s.length(); i++)
        {
            if(DP1[0][i])                                   // full string is a palindrome
            {
                DP2[i] = 0;
            }
            else
            {
                int currOutput = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++)
                {
                    if(DP1[j+1][i])                         // DP1[j+1][i] contains suffix 
                        currOutput = Math.min(currOutput, 1 + DP2[j]);
                }
                DP2[i] = currOutput;
            }    
        } 
        return DP2[s.length()-1];
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------------

// BACKTRACKING (time = n! * n ).. we can also do memorization in this which will be (n^2 * n ==> n^3)

// class Solution 
// {
//     int output = Integer.MAX_VALUE;
//     int currOutput = 0;
//     int count = 0;
//     String input;
    
//     public int minCut(String s)
//     {
//         this.input = s;
//         recurPartition(0);
//         return output;
//     }
    
//     public void recurPartition(int start)                   // Time = O(n! * n)
//     {
//         if(count == input.length())
//         {
//             if(output > currOutput - 1)
//                 output = currOutput - 1;
//             return;
//         }   
        
//         for(int i = start; i < input.length(); i++)
//         {
//             String str = input.substring(start, i+1);
//             if(isPalindrome(str))                           // if prefix is not a plaindrome, then we need not recur for suffix
//             {
//                 count += str.length();   
//                 currOutput++;
                
//                 recurPartition(count);                      // recur suffix (instead of entire string, send remaining count alone)
                
//                 currOutput--;                               // backtrack
//                 count -= str.length();                      // backtrack
//             }
//         }
//     }
    
//     public boolean isPalindrome(String s)
//     {
//         for(int i = 0, j = s.length() - 1; i < j; i++, j--)
//             if(s.charAt(i) != s.charAt(j))
//                 return false;
        
//         return true;
//     }
// }

// ----------------------------------------------------------------------------------------------------------------------------------------

// DP tabulation (n^3 solution)

// class Solution 
// {   
//     public int minCut(String s)
//     {
//         if(s == null || s.length() <= 1)
//             return 0;
        
//         int[][] DP = new int[s.length()][s.length()];
        
//         // fill only top right diagnol.. similar to longest palindromic substring problem
//         for(int i = 0; i < s.length(); i++)     
//         {
//             for(int start = 0, end = i; end < s.length(); start++, end++)
//             {
//                 if(start == end)                                                                    // string length 1
//                     DP[start][end] = 0;
                
//                 else if(end - start == 1)                                                           // string length 2
//                     DP[start][end] = (s.charAt(start) == s.charAt(end)) ? 0 : 1;
                
//                 else                                                                                // remaining length
//                 {
//                     if(isPalindrome(s.substring(start, end+1)))
//                     {
//                         DP[start][end] = 0;
//                     }    
//                     else
//                     {
//                         int currOutput = Integer.MAX_VALUE;
//                         for(int k = start; k < end; k++)                                            // split is all posiitons
//                         {
//                             currOutput = Math.min(currOutput, 1 + DP[start][k] + DP[k+1][end]);
//                             // consider, "abcd" (length 4).. we need to cut in all possible position
//                             // cut position: (a, bcd), (ab, cd), (abc, d).. so this loop will run 3 times for "abcd"
//                             // for (a,bcd) --> 1 + leftSide + rightSide
//                             //      DP[start][k] is left side of cut.. "a" is again a subproblem which is already solved and stored in DP (of length 1)
//                             //      DP[k+1][end] is right side of cut.. "bcd" is again a subproblem which is already solved and stored in DP (of length 3)
//                         }
//                         DP[start][end] = currOutput;
//                     }
//                 }
//             }
//         }
            
//         return DP[0][s.length()-1];                                                                 // top right corner
//     }
    
//     public boolean isPalindrome(String s)
//     {
//         for(int i = 0, j = s.length() - 1; i < j; i++, j--)
//             if(s.charAt(i) != s.charAt(j))
//                 return false;
        
//         return true;
//     }
// }

// ----------------------------------------------------------------------------------------------------------------------------------------


// 1ms leetcode solution
// 2 pointer technique
// time = n^2 and space = n

// class Solution 
// {
//     public int minCut(String s) 
//     {
//         if(s.length() <= 1)
//             return 0;
        
//         int[] dp = new int[s.length() + 1];
//         for(int i = 0; i < dp.length; i++)
//             dp[i] = i - 1;
        
//         for(int i = 0; i < dp.length - 1; i++) 
//         {
//             spread(s, dp, i, i);
//             spread(s, dp, i, i + 1);
//         }
//         return dp[dp.length - 1];
//     }
//
//     private void spread(String s, int[] dp, int start, int end) 
//     {
//         while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) 
//         {
//             dp[end + 1] = Math.min(dp[end + 1], dp[start] + 1);
//             start--;
//             end++;
//         }
//     }
// }
