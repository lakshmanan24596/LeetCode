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
        
    // 1) recursion : Time ==> O(candiates.length ^ candidates.length)  = n^2  
    // 2) DP        : Time --> O(candidates.length * target)            = n*m
    // Similar to coin change
    
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
            if(candidates[i] <= target && (i==startIndex || candidates[i] != candidates[i-1]))  // 
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
//         Node[][] DP = new Node[length][target];
        
//         Set<List<Integer>> currSet;       
//         boolean isSum;
        
//         // Time = O(candidates.length * target)
//         for(int i=0; i<length; i++)
//         {
//             for(int j=0; j<target; j++)
//             {
//                 int x = candidates[i];   // curr candidate
//                 int y = j+1;         // curr target              
//                 isSum = false;
//                 currSet = null;
                
//                 if(x == y)
//                 {
//                  isSum = true;
//                  currSet = new HashSet<List<Integer>>();
                    
//                  List<Integer> presentCellList = new ArrayList<Integer>();
//                  presentCellList.add(x);
//                  currSet.add(presentCellList);
//                 }       
//                 if(i != 0 && DP[i-1][j].isSum == true)                   // top  (i-1 --> because move top 1 position)
//                 {
//                  isSum = true;
//                  currSet = (currSet != null) ? currSet : new HashSet<List<Integer>>();
                    
//                  Set<List<Integer>> topList = (DP[i-1][j].values); 
//                  for(List<Integer> list : topList)
//                  {
//                      list = new ArrayList<Integer>(list);
//                      currSet.add(list);
//                  }
//                 }
//                 if(i != 0 && y-x-1 >= 0 && DP[i-1][y-x-1].isSum == true) // top-left (y-x-1 --> because move left (target-candidate))
//                 {
//                  isSum = true;   
//                  currSet = (currSet != null) ? currSet : new HashSet<List<Integer>>();
                    
//                  Set<List<Integer>> topLeftList = (DP[i-1][y-x-1].values);                   
//                  for(List<Integer> list : topLeftList)
//                  {
//                      list = new ArrayList<Integer>(list);
//                      list.add(x);
//                      currSet.add(list);
//                  }
//                 }

//                 DP[i][j] = new Node(isSum, currSet); 
//             }
//         }
        
//         if(DP[length-1][target-1].values == null)
//             return new ArrayList<List<Integer>>();
//         return new ArrayList<List<Integer>>(DP[length-1][target-1].values);
//     }
// }

// class Node
// {
//     boolean isSum;
//     Set<List<Integer>> values;
    
//     Node(boolean isSum, Set<List<Integer>> values)
//     {
//         this.isSum = isSum;
//         this.values = values;
//     }
// }
