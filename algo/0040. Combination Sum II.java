/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

class Solution 
{
    int[] candidates;
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
        
    // 1) recursion : Time ==> O(candiates.length ^ candidates.length)  = n^n  (target not involved because 1 element can be used only once in output)
    // 2) DP        : Time --> O(candidates.length * target)            = n*m
    // Similar to sumOfSubset, coinChange problem
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) 
    {
        this.candidates = candidates;
        Arrays.sort(candidates);       
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
            if(candidates[i] <= target && (i==startIndex || candidates[i] != candidates[i-1]))  // to avoid duplicates
            {
                currOutput.add(candidates[i]);
                recur(target-candidates[i], i+1);               // i+1 --> because candidates must be used only once 
                currOutput.remove(currOutput.size() - 1);
            }
        }
    }
}

// addition changes comparing problem 40 and 39
    // 1. Arrays.sort(candidates);
    // 2. i==startIndex || candidates[i] != candidates[i-1]
    // 3. i+1 --> (startIndex variable)


// DP solution

// class Solution
// {
//     public List<List<Integer>> combinationSum2(int[] candidates, int target)
//     {
//         int length = candidates.length;       
//         Arrays.sort(candidates);
//         Set<List<Integer>>[][] DP = new HashSet[length][target];       
//         Set<List<Integer>> currSet;
//         int x, y;
        
//         for(int i=0; i<length; i++)
//         {
//             for(int j=0; j<target; j++)
//             {
//                 x = candidates[i];   // curr candidate
//                 y = j+1;         // curr target              
//                 currSet = null;
                
//                 if(x == y)
//                 {
//                  currSet = new HashSet<List<Integer>>();                 
//                  List<Integer> presentCellList = new ArrayList<Integer>();
//                  presentCellList.add(x);
//                  currSet.add(presentCellList);
//                 }       
//                 if(i != 0 && DP[i-1][j] != null)                     // top  (i-1 --> because move top 1 position)
//                 {
//                  currSet = (currSet != null) ? currSet : new HashSet<List<Integer>>();                   
//                  for(List<Integer> topList : DP[i-1][j])
//                  {
//                      topList = new ArrayList<Integer>(topList);
//                      currSet.add(topList);
//                  }
//                 }
//                 if(i != 0 && y-x-1 >= 0 && DP[i-1][y-x-1] != null)  // top-left (y-x-1 --> because move left (target-candidate))
//                 {
//                  currSet = (currSet != null) ? currSet : new HashSet<List<Integer>>();                                   
//                  for(List<Integer> topLeftList : DP[i-1][y-x-1])
//                  {
//                      topLeftList = new ArrayList<Integer>(topLeftList);
//                      topLeftList.add(x);
//                      currSet.add(topLeftList);
//                  }
//                 }

//                 DP[i][j] = currSet;
//             }
//         }

//         return ((DP[length-1][target-1] == null)) ? new ArrayList<List<Integer>>() : new ArrayList<List<Integer>>(DP[length-1][target-1]);
//     }
// }