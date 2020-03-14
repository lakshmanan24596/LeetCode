/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

class Solution 
{
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    int n;
    
    public List<List<Integer>> combine(int n, int k) 
    {
        if(k > n)
            return output;
        
        this.n = n;
        combineUtil(k, 1);
        return output;
    }
    
    public void combineUtil(int k, int startIndex)
    {
        if(k == 0)
        {
            output.add(new ArrayList<Integer>(currOutput));
            return;
        }    
    
        for(int i = startIndex; i <= n; i++)
        {
            currOutput.add(i);
            combineUtil(k-1, i+1);                          // k is level and increment startIndex for next iteration
            currOutput.remove(currOutput.size() - 1);
        }
    }
}