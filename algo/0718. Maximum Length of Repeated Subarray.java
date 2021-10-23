/*
    1) Brute force: 
        Time: m * n * min(m, n)
        Space: 1

    2) DP:
        https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/413054/ALL-4-ways-Recursion-greater-Top-down-greaterBottom-Up-including-VIDEO-TUTORIAL
        Time: n * m
        Space: min(n, m)
        Note: This question is same as Longest Common Substring
        
        longest common sub-sequence:
            equal     --> diagnol + 1
            not equal --> max(left, top)
            
        longest common sub-array:   
            equal     --> diagnol + 1
            not equal --> 0
     
    3) HashMap + Binary search + Rolling hash using sliding window 
        Solution 1 :
            Brute
            generate all substring of A and put in hashset.
            now iterate all substring of B and check if it exist in the hashSet.
            if exist, then length is the output. We need max length as output.
        Solution 2:
            Linear search
            Instead of generating all string of A, we first generate substring of A of length 5, then 4,... till 1
            Because we need maxLength.
        Solution 3:
            Binary search
            Instead of linear search from length 5 to 1, we can do binary search
            ex: mid = 3. So generate all substring of A of length 3 alone.
            Check repeated subarray is formed using length 3.
                If length 3 is true, then length 1 and 2 will also be surely true
                if length 3 is false, then 4 and 5 will also be surely false
        Solution 4:
            Binary search + Rabin karp algo
            we are checking for each length. 
            so the problem reduces to KMP or Rabin karp algo 
            It can find the pattern (substring) in the text in "m+n" time instead of m*n time
            For this problem, we can use rabin karp algo which uses rolling hash with sliding window technique
            So the time complexity is --> log(min(m,n)) * (m+n) --> which is n*logn is simple terms
*/


// DP tabulation
class Solution 
{
    public int findLength(int[] A, int[] B)                 // Time: m * n, Space: min(m, n)
    {
        int m = A.length;
        int n = B.length;
        if(m < n) {                                         // always n should be smaller than m, because we create DP of size n
            return findLength(B, A);
        }
        if(m == 0 || n == 0) {
            return 0;
        }
        
        int output = 0;
        int[] DP = new int[n];                              // instead 2D, we can use 1D DP array
        int prev, temp;
        
        for(int i = 0; i < m; i++) 
        {
            prev = 0;
            for(int j = 0; j < n; j++) 
            {
                temp = DP[j];
                if(A[i] == B[j])  {
                    DP[j] = prev + 1;                       // main logic (fetch from diagnol case)
                    output = Math.max(output, DP[j]);
                } 
                else {
                    DP[j] = 0;                              // main logic
                }
                prev = temp;
            }
        }
        return output;
    }
}
