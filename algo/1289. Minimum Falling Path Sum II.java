/*
Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.
Return the minimum sum of a falling path with non-zero shifts.

Example 1:
Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13

Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
 
Constraints:
1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99
*/


/*  
    DP solution: 
    Time: O(row*col*col)
    Space: 
        1) O(row*col) for extra DP matrix. 
        2) O(2*row) if we use DP arr for currRow and prevRow (Space optimized)
        3) O(1) if we change use given matrix as DP

class Solution 
{
    public int minFallingPathSum(int[][] A) 
    {
        int size = A.length;
        int minSum, currSum, nextRow; 
        
        for(int row = 1; row < size; row++)     // start from row = 1 because (DP of firstrow = A of firstRow) and no changes in it
        {
            for(int col = 0; col < size; col++) 
            {
                minSum = Integer.MAX_VALUE;
                currSum = A[row][col];
                nextRow = row - 1;          // fetch from prevRow
                
                for(int nextCol = 0; nextCol < size; nextCol++)  // diff compared to problem 931
                {
                    if(nextCol != col) // diff compared to problem 931
                    {
                        minSum = Math.min(minSum, currSum + A[nextRow][nextCol]);
                    }
                }
                A[row][col] = minSum;
            }
        }
        
        minSum = Integer.MAX_VALUE;
        for(int i = 0; i < size; i++) {
            minSum = Math.min(minSum, A[size-1][i]);    // output is minValue present in last row
        }
        return minSum;
    }
}
*/


/*  DP solution: 
    Time: O(row*col*2)  // time optimized solution
    Space: 
        1) O(row*col) for extra DP matrix. 
        2) O(2*row) if we use DP arr for currRow and prevRow (Space optimized)
        3) O(1) if we change use given matrix as DP */

class Solution 
{
	public int minFallingPathSum(int[][] A) 
	{
		int size = A.length;
        int reqRow, reqCol;
		int[] smallest;
        
        for(int row = 1; row < size; row++)     // start from row = 1 because (DP of firstrow = A of firstRow) and no changes in it
        {
            smallest = findSmallestMinIndex(A, size, row-1);    // main logic: find two smallest value index in prev row of DP array
            for(int col = 0; col < size; col++) 
            {
                reqRow = row - 1;
                reqCol = (smallest[0] != col) ? smallest[0] : smallest[1];    // main logic
                A[row][col] += A[reqRow][reqCol];
            }
        }

		int minSum = Integer.MAX_VALUE;
		for(int i = 0; i < size; i++) {
            minSum = Math.min(minSum, A[size-1][i]);    // output is minValue present in last row
		}
		return minSum;
	}
	
	public int[] findSmallestMinIndex(int[][] A, int size, int row)
	{
		int firstMinIndex, secondMinIndex; 	// Find the 2 minimum value index in the row.
		
		if(A[row][0] < A[row][1]) {
            firstMinIndex = 0;
            secondMinIndex = 1;
		} else {
            firstMinIndex = 1;
            secondMinIndex = 0;
		}
		for(int i = 2; i < size; i++) 
		{
			if(A[row][i] < A[row][firstMinIndex]) {
                secondMinIndex = firstMinIndex;
                firstMinIndex = i;
			} 
			else if(A[row][i] < A[row][secondMinIndex]) {
                secondMinIndex = i;
			}
		}
		return new int[] {firstMinIndex, secondMinIndex};
	}
}
