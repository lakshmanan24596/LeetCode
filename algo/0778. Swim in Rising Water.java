/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
Now rain starts to fall. At time t, the depth of the water everywhere is t. 
You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. 
You can swim infinite distance in zero time. 
Of course, you must stay within the boundaries of the grid during your swim.
You start at the top left square (0, 0). 
What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:
Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:
Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Note:
2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].
*/



/*
    Techniques: Dijstra, Binary search, Union find
    In each path, currOutput is "maxValue" in that path
    We need to find a "minValue" Path out of all possible paths 
    which is min(max(path1), max(path2), ...)
*/
/*
    Dijkstra algo using min heap
    let n = N * N which is the size of the grid
    Time: E*logV = 4n*logn = n*logn
    Space: n
*/
/*
class Solution 
{
    public int swimInWater(int[][] grid) 
    {
        int n = grid.length;
        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return grid[a[0]][a[1]] - grid[b[0]][b[1]];    // min heap
            }
        });
        
        pQueue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        int[] curr, dir = new int[] {0, -1, 0, 1, 0};
        int currX, currY, nextX, nextY, output = 0;
        
        while(!pQueue.isEmpty())
        {
            curr = pQueue.remove();
            currX = curr[0]; 
            currY = curr[1];
            output = Math.max(output, grid[currX][currY]);
            if(currX == n - 1 && currY == n - 1) {
                return output;
            }
            
            for(int i = 0; i < 4; i++)
            {
                nextX = currX + dir[i];
                nextY = currY + dir[i + 1];
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextX][nextY])
                {
                    pQueue.add(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return output; // unreachable code
    }
}
*/


/*
    Logic: Binary search with the answer. Possible answer: 0 to N-1
    Time: n * logn, Space: n
*/    
class Solution 
{
    int[] dir;
    int[][] grid;
    int n;
    
    public int swimInWater(int[][] grid) 
    {
        this.grid = grid;
        this.n = grid.length;
        this.dir = new int[]{0, -1, 0, 1, 0};
        int low = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int high = (n * n) - 1;
        int mid;
        
        while (low <= high)                             // binary search with the answer
        {
            mid = low + ((high - low) / 2); 
            if(dfs(0, 0, new boolean[n][n], mid)) {    // check whether mid is a possible answer using dfs
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public boolean dfs(int currX, int currY, boolean[][] visited, int mid) 
    {    
        if (currX == n -1 && currY == n - 1) {
            return true;
        }    
        visited[currX][currY] = true;
        int nextX, nextY;
        
        for(int i = 0; i < 4; i++) 
        {
            nextX = currX + dir[i];
            nextY = currY + dir[i + 1];
            if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && 
                !visited[nextX][nextY] && 
                grid[nextX][nextY] <= mid)
            {
                if(dfs(nextX, nextY, visited, mid)) {
                    return true;   
                }
            }
        }
        return false;
    }
}

/*
    Union find: https://leetcode.com/problems/swim-in-rising-water/discuss/576389/JAVA-Union-Find
    Note: formula to convert 2D array index to 1D array index --> (i*n)+j
*/
