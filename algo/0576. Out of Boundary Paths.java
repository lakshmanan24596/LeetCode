/*
There is an m by n grid with a ball. 
Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). 
However, you can at most move N times. 
Find out the number of paths to move the ball out of grid boundary. 
The answer may be very large, return it after mod 109 + 7.

Example 1:
Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:
Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
*/


/*
// Brute force: Time: 4^n
class Solution 
{
  public int findPaths(int m, int n, int N, int i, int j) 
  {
    if (i == m || j == n || i < 0 || j < 0) 
        return 1;
    if (N == 0) 
        return 0;
    return findPaths(m, n, N - 1, i - 1, j)
        + findPaths(m, n, N - 1, i + 1, j)
        + findPaths(m, n, N - 1, i, j - 1)
        + findPaths(m, n, N - 1, i, j + 1);
  }
}
*/

// Recursion with memorization: Time: m*n*N, Space: m*n*N
class Solution 
{
    int[] dir;
    int m, n, N;
    Integer[][][] DP;
    int moduloVal = 1000000007; // https://www.geeksforgeeks.org/modulo-1097-1000000007/
    
    public int findPaths(int m, int n, int N, int i, int j)
    {
        dir = new int[] {0, -1, 0, 1, 0};
        this.m = m; this.n = n; this.N = N;
        DP = new Integer[m][n][N+1];
        return recur(i, j, 0);
    }
    
    public int recur(int currX, int currY, int level)
    {
        if(level > N) {
            return 0;
        } 
        if(currX < 0 || currX >= m || currY < 0 || currY >= n) {
            return 1;
        }
        if(DP[currX][currY][level] != null) {   // memorization DP with states: x, y, level
            return DP[currX][currY][level];
        }
        
        int output = 0;
        int nextX, nextY;
        for(int i = 0; i < 4; i++)
        {
            nextX = currX + dir[i];
            nextY = currY + dir[i + 1];
            output += recur(nextX, nextY, level + 1);
            output %= moduloVal;
        }
        return DP[currX][currY][level] = output;
    }
}

/*
DP tabulation space optimized:
    Time: m*n*N, Space: m*n
    
    DP[i][j][k] stands for how many possible ways to walk into cell j,k in step i.
    DP[i][j][k] only depends on DP[i - 1][j][k], so we can compress 3 dimensional dp array to 2 dimensional.

// https://leetcode.com/problems/out-of-boundary-paths/discuss/102967/Java-Solution-DP-with-space-compression
*/
