/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 
Example:
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:
[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

class Solution 
{
    /*
        brute force: At each index of the matrix, we need to loop remaining all nodes in matrix. Time = (r*c) * (r*c)
        bfs or dfs: Ar each index of the matrix, we need to go to 4 directions but visit only the unvisited nodes. Time = (r*c) * 4
    */
    
    int[][] matrix;
    int row, col;
    int[] xDir = new int[] {0, -1, 0, 1};
    int[] yDir = new int[] {-1, 0, 1, 0};
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) 
    {
        if(matrix == null) {
            return null;
        }
        this.matrix = matrix;
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        this.row = matrix.length;
        if(row == 0) {
            return output;
        }
        this.col = matrix[0].length;
        
        Queue<int[]> pacificQueue = new LinkedList<int[]>();
        Queue<int[]> atlanticQueue = new LinkedList<int[]>();
        boolean[][] pacificVisited = new boolean[row][col];
        boolean[][] atlanticVisited = new boolean[row][col];
        
        for(int i = 0; i < row; i++)    // vertical
        {
            pacificVisited[i][0] = true;
            pacificQueue.add(new int[]{i, 0});
            
            atlanticVisited[i][col-1] = true;
            atlanticQueue.add(new int[]{i, col-1});
        }
        
        for(int i = 0; i < col; i++)    // horizontal
        {
            if(i != 0) {
                pacificVisited[0][i] = true;
                pacificQueue.add(new int[]{0, i});
            }
            if(i != col-1) {
                atlanticVisited[row-1][i] = true;
                atlanticQueue.add(new int[]{row-1, i});
            }
        }
        
        bfs(pacificQueue, pacificVisited);    // visited all nodes which pacific ocean water flows
        bfs(atlanticQueue, atlanticVisited);  // visit all nodes which atlantic ocea water flows
        
        List<Integer> currOutput;
        for(int i = 0; i < row; i++) 
        {
            for(int j = 0; j < col; j++) 
            {
                if(pacificVisited[i][j] && atlanticVisited[i][j]) 
                {
                    currOutput = new ArrayList<Integer>();
                    currOutput.add(i);
                    currOutput.add(j);
                    output.add(currOutput);
                }
            }
        }
        return output;
    }
    
    public void bfs(Queue<int[]> queue, boolean[][] visited) 
    {
        int currX, currY, nextX, nextY;
        int[] currElement;
        
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i = 0; i < size; i++)
            {
                currElement = queue.remove();
                currX = currElement[0];
                currY = currElement[1];
                    
                for(int j = 0; j < xDir.length; j++)
                {
                    nextX = currX + xDir[j];
                    nextY = currY + yDir[j];
                    
                    if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col
                        && !visited[nextX][nextY] 
                        && matrix[nextX][nextY] >= matrix[currX][currY])
                    {
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }
}
