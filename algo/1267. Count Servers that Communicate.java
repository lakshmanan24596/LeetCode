/*
You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. 
Two servers are said to communicate if they are on the same row or on the same column.
Return the number of servers that communicate with any other server.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.

Example 2:
Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.

Example 3:
Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other.
The two servers in the third column can communicate with each other. 
The server at right bottom corner can't communicate with any other server.

Constraints:
m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1
*/


/*
    Logic:
        pre-process grid and store number of servers for each row and column
        then in main processing, check 2 or more servers present in either in that row or column and then increment count
    
    Time: 2*m*n
    Space: m+n
*/
/*
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        int countOfServersCommunicate = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;                                  // pre-process and store 
                    colCount[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (rowCount[i] >= 2 || colCount[j] >= 2) {     // main logic
                        countOfServersCommunicate++;
                    }
                }
            }
        }
        return countOfServersCommunicate;
    }
}
*/


/*
    Logic: same as above, but instead of extra array, we alter the original array itself
        a) iterate row by row to take rowCount
        b) iterate col by col to take colCount
        
    Time: 3*m*n
    Space: 1
*/
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count, prevI = -1, prevJ = -1;
        int countOfServersCommunicate = 0;
        
        for (int i = 0; i < m; i++) {               // iterate row by row
            count = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    if (count == 1) {
                        prevI = i;
                        prevJ = j;
                    } else {
                        grid[prevI][prevJ] = 2;
                        grid[i][j] = 2;
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {               // iterate col by col
            count = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] >= 1) {
                    count++;
                    if (count == 1) {
                        prevI = i;
                        prevJ = j;
                    } else {
                        grid[prevI][prevJ] = 2;
                        grid[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    countOfServersCommunicate++;    // main logic
                }
            }
        }
        return countOfServersCommunicate;
    }
}
