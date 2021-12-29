/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/


/*
    Recursion    --> O(n ^ target)
    Memorization --> O(n * target) 
    (target involved, because one candidate can be used many times)
*/
    
/*
class Solution {
    int[] candidates;
    List<Integer> currOutput = new ArrayList<Integer>();
    List<List<Integer>> output = new ArrayList<List<Integer>>();
  
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        this.candidates = candidates;
        //Array.sort(candidates);                               // no need sorting

        recur(target, 0);
        return output;
    }
   
    public void recur(int target, int startIndex)
    {
        if(target == 0)
        {       
            output.add(new ArrayList<Integer>(currOutput));
            return;
        }
      
        for(int i=startIndex; i<candidates.length; i++)         // i = startIndex --> because output should be unique
        {
            if(candidates[i] <= target)
            {
                currOutput.add(candidates[i]);                  // pre-order
                recur(target-candidates[i], i);                 // i --> next startIndex
                currOutput.remove(currOutput.size() - 1);
            }
        }
    }
}
*/


// DP memorization
class Solution 
{   
    int[] candidates;
    HashMap<Integer, List<List<Integer>>> DP = new HashMap<Integer, List<List<Integer>>>(); // key = target, value = List<List<>> output
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        this.candidates = candidates;
        Arrays.sort(candidates);                                    // sorting is needed in DP (both tabuldation and memorization)
            
        List<List<Integer>> output = new ArrayList<List<Integer>>();      
        output.add(new ArrayList<Integer>());
        DP.put(0, output);                                          // base case is handled in DP map, to avoid creating unwanted empty list (memory)                                                       
        return recur(target);
    }
    
    public List<List<Integer>> recur(int target)
    {
        List<List<Integer>> output = new ArrayList<List<Integer>>();                // declare locally, because we are storing the reference in DP
        
        if(DP.containsKey(target))
            return DP.get(target);
         
        for(int i=0; i<candidates.length; i++)
        {
            if(candidates[i] <= target)
            {
                List<List<Integer>> currOutput = recur(target-candidates[i]);   
                
                for(List<Integer> list : currOutput)
                {
                    if(list.isEmpty() || candidates[i] <= list.get(list.size()-1))  // to maintain list in decending order 
                    {
                        list = new ArrayList<Integer>(list);                        // clone it, because we are storing the reference in DP
                        list.add(candidates[i]);                
                        output.add(list);                                           // post order
                    }
                }
            }
        }
        
        DP.put(target, output);
        return output;
    }
}
