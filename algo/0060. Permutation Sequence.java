/*
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:
Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.

Example 1:
Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"
*/

class Solution 
{
    StringBuilder output = new StringBuilder();
    StringBuilder str;
    int n, k;
    int perBatch, batchNum, batchStart, val;
        
    public String getPermutation(int n, int k) 
    {
        this.n = n;
        this.k = k;      
        
        batchStart = 0;
        perBatch = factorial(n);
        
        str = new StringBuilder();
        for(int i=0; i<n; i++)
            str.append(i+1);
        
        permute(n);
        return output.toString();
    }
    
    // brute            --> time = O(n!) --> generate all leaf.. stop it when k-th leaf is found
    // find exact leaf  --> time = O(n)
    
    public void permute(int level) // time = O(n)
    {
        if(level == 0)
            return;
             
        perBatch = perBatch / level;     
        batchNum = (k == batchStart) ?  1 : (int)Math.ceil((double)(k-batchStart)/perBatch); // find exact leaf to recur      
        batchStart += perBatch * (batchNum-1);

        val = str.charAt(batchNum-1) - '0';
        str.deleteCharAt(batchNum-1);           // delete from input
        output.append(val);                     // add in output
        permute(level-1);
    }
    
    public int factorial(int n)
    {
        if(n <= 2) 
            return n;
        return (n * factorial(n-1));
    }
}