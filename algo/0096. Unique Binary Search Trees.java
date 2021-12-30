/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:
Input: 3
Output: 5

Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/


/*
    For n = 3, we can keep 1 or 2 or 3 as root
    So G(3) = F(1,3)    + F(2,3)    + F(3,3)       // where F(1,3) means --> 1 as root and 3 nodes left
            = G(0)*G(2) + G(1)*G(1) + G(2)*G(0)    // G(0)*G(2) means --> (1 as root) --> 0 nodes in left, 2 nodes in right
    
    catalan(3) = (caralan(0) * calatan(2)) + 
                 (caralan(1) * calatan(1)) + 
                 (caralan(2) * calatan(0))

    catalan number:
        1) recursion
        2) DP
        3) binomial coeff
        https://www.geeksforgeeks.org/program-nth-catalan-number/
        
        binomial coeff (nCr) in time = r
        8C6 = 8C2 = ((8*7)/(1*2)) --> ie; r is the time complexity
        
        catalan number = ((2nCn) / (n+1))
        so if we find catalan number using binomial coeff, then the time complexity = O(n)
*/        


// memo O(n * n)
class Solution {
    Integer[] memo;
    
    public int numTrees(int n) {
        if (n < 1) {
            return 0;
        }
        memo = new Integer[n + 1];
        return numTreesUtil(n);
    }
    
    public int numTreesUtil(int n) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            count += numTreesUtil(i - 1) * numTreesUtil(n - i);   // main logic
        }
        return memo[n] = count;
    }
}     


/*
// DP
class Solution {
    public int numTrees(int n) {
        return catalanDP(n);
    }
    
    public int catalanDP(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] DP = new int[n + 1];
        DP[0] = DP[1] = 1;
        int result = 0;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                DP[i] += DP[j] * DP[i - 1 - j];
            }
        }
        return DP[n];
    }
}
*/