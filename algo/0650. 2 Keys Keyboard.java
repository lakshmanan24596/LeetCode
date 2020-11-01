/*
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.

Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. 
Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3

Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

Note:
The n will be in the range [1, 1000].
*/

class Solution 
{
    // logic similar to: https://leetcode.com/problems/2-keys-keyboard/discuss/105908/Very-Simple-Java-Solution-With-Detail-Explanation
    public int minSteps(int n) 
    {
        if(n <= 1) {
            return 0;   // because A is present in the clipboard initially
        }
        
        int[] DP = new int[n+1];    // DP of 0 to n
        
        for(int i = 2; i <= n; i++)
        {
            DP[i] = i;
            for(int j = i / 2; j >= 2; j--)
            {
                if(i % j == 0)
                {
                    DP[i] = DP[j] + (i / j);    // main logic: (i/j) is for copyall and then paste continously...
                    break;
                }
            }
        }
        return DP[n];
    }
}
