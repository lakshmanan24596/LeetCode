/*
In an N by N square grid, each cell is either empty (0) or blocked (1).
A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).

Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:
Input: [[0,1],[1,0]]
Output: 2

Example 2:
Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Note:
1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
*/


/*
    Logic: bfs
    Time: n * 8, Space: n * 8, where n = r * c
*/
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(0, 0, 1));
        grid[0][0] = 1;  // use given array as visited array
        Node curr;
        int nextX, nextY, queueSize;
        
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            while (queueSize-- > 0) {
                curr = queue.remove();
                if (curr.x == n - 1 && curr.y == n - 1) {
                    return curr.dist;
                }
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i != 0 || j != 0) {
                            nextX = curr.x + i;
                            nextY = curr.y + j;
                            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0) {
                                grid[nextX][nextY] = 1;
                                queue.add(new Node(nextX, nextY, curr.dist + 1));   // main logic
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    class Node {
        int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y; 
            this.dist = dist;
        }
    }
}