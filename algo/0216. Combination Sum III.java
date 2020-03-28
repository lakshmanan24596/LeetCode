/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

class Solution 
{
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    
    public List<List<Integer>> combinationSum3(int k, int n) // Time: 9 ^ k
    {
        recur(k, n, 1);
        return output;
    }
    
    public void recur(int level, int sum, int startIndex)   
    {
        if(level == 0 && sum == 0)
        {
            output.add(new ArrayList<Integer>(currOutput));
            return;
        }
        
        for(int i = startIndex; i <= 9; i++)                // possible numbers are 1 to 9
        {
            if(sum >= i)
            {
                currOutput.add(i);
                recur(level-1, sum-i, i+1);                 // reduce level and sum.. i+1 will be next startIndex to avoid duplicate combinations
                currOutput.remove(currOutput.size() - 1);
            }
        }
    }
}