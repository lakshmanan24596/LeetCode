/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

class Solution 
{  
    
// 1) Greedy (wont work):::    O(n), O(1) but it wont work for this problem
// 2) Recursion :::            O(2^n)
// 3) Memorization :::         O(n^2), O(n^2)
// 4) tabulation :::           O(n^2), O(n)     (space optimized)

// Main Logic:::
// considering triangle as a tree, adjacent nodes always share a common 'branch'. ie; there are overlapping subproblem
  
    
//     simple recursion  
//     public int minimumTotalRecur(int row, int col)   // initial values: (0, 0)
//     {
//         if(row >= triangle.size()) return 0;
    
//         return (triangle.get(row).get(col) + 
//                 Math.min(minimumTotalRecur(row+1, col), minimumTotalRecur(row+1, col+1)));   // pre-order
//     }
  
    
//     Memorization    
//     List<List<Integer>> triangle;
//     int[][] DP;
//     boolean[][] isAlreadySolved;
    
//     public int minimumTotal(List<List<Integer>> triangle) 
//     {
//         this.triangle = triangle;
//         this.isAlreadySolved = new boolean[triangle.size()][triangle.size()];
//         this.DP = new int[triangle.size()][triangle.size()];    // memorization 
        
//         return minimumTotalRecur(0, 0);
//     }
    
//     calculate sum bottom up
//     public int minimumTotalRecur(int row, int col)
//     {
//         if(row >= triangle.size())
//             return 0;
        
//         if(isAlreadySolved[row][col])                               // if already solved, then return it
//             return DP[row][col];
        
//         int leftSide = minimumTotalRecur(row+1, col);
//         int rightSide = minimumTotalRecur(row+1, col+1);
//         int currData = triangle.get(row).get(col);
    
//         DP[row][col] = currData + Math.min(leftSide, rightSide);    // store and return
//         isAlreadySolved[row][col] = true;
        
//         return DP[row][col];
//     }
    

//  Tabulation - space optimized (n space algo)
    public int minimumTotal(List<List<Integer>> triangle)
    {
        int length = triangle.size();
        int[] DP = new int[length];
        
        for(int i = 0; i < length; i++)
        {
            DP[i] = triangle.get(length-1).get(i);  // fill DP with last row data
        }   
        
        for(int i = length-2; i >= 0; i--)          // bottom-up
        {
            for(int j = 0; j < i + 1; j++)
            {
                DP[j] = triangle.get(i).get(j) + Math.min(DP[j], DP[j+1]);
            }
        }        
        return DP[0];
    }
}