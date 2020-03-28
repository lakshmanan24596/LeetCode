/*
Given an array of integers arr and an integer d. In one step you can jump from index i to index:
i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.

In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.

Example 2:
Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.

Example 3:
Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies. 

Example 4:
Input: arr = [7,1,7,1,7,1], d = 2
Output: 2

Example 5:
Input: arr = [66], d = 1
Output: 1
 
Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
*/

class Solution 
{
    int[] arr, DP;
    int d, n;
    
    public int maxJumps(int[] arr, int d)                   // Time: n * 2d and space: n
    {
        this.arr = arr;
        this.d = d;
        n = arr.length;
        DP = new int[n];
        
        int currOutput = 1; 
        int maxOutput = currOutput;        
        for(int i = 0; i < n; i++)                          // start from all indexes in the array
        {
            currOutput = dfs(i);
            if(currOutput > maxOutput)
                maxOutput = currOutput;    
        }    
        return maxOutput;
    }

    public int dfs(int currIndex)                           // DP state: currIndex
    {
        if(DP[currIndex] != 0)                              // memorization
            return DP[currIndex];
                
        int currOutput = 1; 
        int maxOutput = currOutput;

        for(int nextIndex = currIndex - 1; nextIndex >= 0 && nextIndex >= currIndex - d; nextIndex--)  // leftSide    
        {
            if(arr[currIndex] > arr[nextIndex])
            {
                currOutput = dfs(nextIndex) + 1;            // no base case for recursion
                if(currOutput > maxOutput)
                    maxOutput = currOutput;
            }
            else
                break;                                      // main logic... no need to check arr[i] > arr[k], for all K
        }
    
        for(int nextIndex = currIndex + 1; nextIndex < n && nextIndex <= currIndex + d; nextIndex++)  // rightSide
        {
            if(arr[currIndex] > arr[nextIndex])
            {
                currOutput = dfs(nextIndex) + 1; 
                if(currOutput > maxOutput)
                    maxOutput = currOutput;
            }
            else
                break;
        }        
        return DP[currIndex] = maxOutput;
    }
}


// This solution is base as above, but it checks all K between currIndex and nextIndex

// class Solution 
// {
//     int[] arr, DP;
//     int d, n;
    
//     public int maxJumps(int[] arr, int d) 
//     {
//         this.arr = arr;
//         this.d = d;
//         n = arr.length;
//         DP = new int[n];
        
//         int currOutput = 1; 
//         int maxOutput = currOutput;
        
//         for(int i = 0; i < n; i++)                              // start from all indexes in the array
//         {
//             currOutput = dfs(i);
//             if(currOutput > maxOutput)
//                 maxOutput = currOutput;    
//         }    
//         return maxOutput;
//     }

//     public int dfs(int currIndex)                               // DP state: currIndex
//     {
//         if(DP[currIndex] != 0)
//             return DP[currIndex];
        
//         int currOutput = 1; 
//         int maxOutput = currOutput;
        
//         for(int nextIndex = currIndex - d; nextIndex <= currIndex + d; nextIndex++)     // from i - x to i + x
//         {
//             if(nextIndex >= 0 && nextIndex < n 
//                && nextIndex != currIndex
//                && checkAllK(currIndex, nextIndex))
//             {
//                 currOutput = dfs(nextIndex) + 1;                // no base case for recursion
//                 if(currOutput > maxOutput)
//                     maxOutput = currOutput;
//             }
//         }       
//         return DP[currIndex] = maxOutput;
//     }
    
//     public boolean checkAllK(int currIndex, int nextIndex)      // check arr[i] > arr[k] and arr[i] > arr[j]
//     {
//         if(currIndex > nextIndex)
//         {
//             for(int k = currIndex - 1; k >= nextIndex; k--) 
//                 if(arr[currIndex] <= arr[k])
//                     return false;
//         }
//         else
//         {
//             for(int k = currIndex + 1; k <= nextIndex; k++)
//                 if(arr[currIndex] <= arr[k])
//                     return false;
//         }
//         return true;
//     }
// }