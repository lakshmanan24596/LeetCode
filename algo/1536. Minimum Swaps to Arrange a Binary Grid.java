/*
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
A grid is said to be valid if all the cells above the main diagonal are zeros.
Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

Example 1:
Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3

Example 2:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 200
grid[i][j] is 0 or 1
*/



/*
    step 1: 
        find trailing zeros in each row
        for ex1: trailing zeros = {0, 1, 2}
        required training zeros = {2, 1, 0}
    
    step 2:
        sort training zeros array and find noOfSwaps
        
    time: n^2
    space: n
*/

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];
        int zeroCount;
        
        for (int i = 0; i < n; i++) {
            zeroCount = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    break;
                }
                zeroCount++;
            }
            trailingZeros[i] = zeroCount;                   // find trailing zeros in each row
        }
        return sortAndCountSwaps(trailingZeros);
    }
    
    public int sortAndCountSwaps(int[] arr) {
        int n = arr.length;
        int reqCount;
        int countOfSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            reqCount = (n - 1) - i;
            if (arr[i] < reqCount) {
                int j = i + 1;
                
                while (j < n && arr[j] < reqCount) {        // find j index such that arr[j] >= reqCount
                    j++;
                }
                if (j == n) {
                    return -1;
                }
                while (j > i) {
                    swap(arr, j, j - 1);
                    countOfSwaps++;
                    j--;
                }
            }
        }
        return countOfSwaps;
    }
    
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}