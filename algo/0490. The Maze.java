/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], 
return true if the ball can stop at the destination, otherwise return false.
You may assume that the borders of the maze are all walls (see examples).


Example 1:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.

Example 3:
Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false
 

Constraints:
m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
*/



/*
    Logic: dfs
    time: m*n
    space: m*n
*/

class Solution {
    int[][] maze;
    int[] destination;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    boolean[][] visited;
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        this.maze = maze;
        this.destination = destination;
        this.visited = new boolean[maze.length][maze[0].length];
        return hasPathUtil(start[0], start[1]);
    }
    
    public boolean hasPathUtil(int x, int y) {
        if (x == destination[0] && y == destination[1]) {
            return true;
        }
        visited[x][y] = true;
        int nextX, nextY;
        
        for (int i = 0; i < 4; i++) {
            nextX = x;
            nextY = y;
            while (validate(nextX + dir[i], nextY + dir[i + 1])) {          // loop to move the ball to the corner or wall
                nextX += dir[i];
                nextY += dir[i + 1];
            }
            if (!visited[nextX][nextY] && hasPathUtil(nextX, nextY)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validate(int x, int y) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length) && (maze[x][y] == 0);
    }
}