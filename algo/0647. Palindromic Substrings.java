/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 
Note:
The input string length won't exceed 1000.
*/

class Solution 
{
    public int countSubstrings(String s) 
    {
        int output = 0;
        int n = s.length();
        boolean[][] DP = new boolean[n][n];
        
        // length 1
        for(int i = 0; i < n; i++) 
        {
            DP[i][i] = true;
            output++;
        }
        
        // length 2
        for(int i = 0; i < n-1; i++) 
        {
            if(s.charAt(i) == s.charAt(i+1)) 
            {
                DP[i][i+1] = true;
                output++;
            }
        }
        
        // length 3 to n
        for(int k = 3; k <= n; k++)
        {
            for(int i = 0; i < n-k+1; i++)
            {
                int j = i+k-1;
                if(DP[i+1][j-1] && s.charAt(i) == s.charAt(j)) 
                {
                    DP[i][j] = true;
                    output++;
                } 
                else 
                {
                    DP[i][j] = false;
                }
            }
        }

        return output; 
    }
}

/*
Time = O(n^2) and Space = O(1). 
Logic: 2 pointer concept where mid is center and expand in both side

class Solution 
{
    public int countSubstrings(String s) 
    {
        int total = 0;
        for(int i = 0; i<s.length(); i++)
        {
            total+= expandCenter(s, i, i);
            total+= expandCenter(s, i, i+1);
        }
        return total;
    }
     
    public int expandCenter(String s, int L, int R)
    {
        int count = 0;
        while(L>=0 && R<s.length() && s.charAt(L) == s.charAt(R))
        {
            count++;
            L--;
            R++;
        }
        return count;
    }
}
*/