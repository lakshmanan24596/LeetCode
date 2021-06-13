/*
You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space. 

A brick is stable if:
It is directly connected to the top of the grid, or
At least one other brick in its four adjacent cells is stable.

You are also given an array hits, which is a sequence of erasures we want to apply. 
Each time we want to erase the brick at the location hits[i] = (rowi, coli). 
The brick on that location (if it exists) will disappear. 
Some other bricks may no longer be stable because of that erasure and will fall. 
Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).

Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.
Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.


Example 1:
Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
Output: [2]
Explanation: Starting with the grid:
[[1,0,0,0],
 [1,1,1,0]]
We erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,1,1,0]]
The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
[[1,0,0,0],
 [0,0,0,0]]
Hence the result is [2].

Example 2:
Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: Starting with the grid:
[[1,0,0,0],
 [1,1,0,0]]
We erase the underlined brick at (1,1), resulting in the grid:
[[1,0,0,0],
 [1,0,0,0]]
All remaining bricks are still stable, so no bricks fall. The grid remains the same:
[[1,0,0,0],
 [1,0,0,0]]
Next, we erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,0,0,0]]
Once again, all remaining bricks are still stable, so no bricks fall.
Hence the result is [0,0].


Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is 0 or 1.
1 <= hits.length <= 4 * 104
hits[i].length == 2
0 <= xi <= m - 1
0 <= yi <= n - 1
All (xi, yi) are unique.
*/



/*
1) brute:
    for each hit, do dfs in all 4 sides
    time: O(hits * (row * col))
    space: O(hits)
        
2) DSU:
    time: O((rows * cols) + (hits * 4))
    space: O(hits) + O(rows * cols)
    logic: reverse time + dsu
    
    links:
        1) similar to 305. Number of Islands II
        2) https://leetcode.com/problems/bricks-falling-when-hit/discuss/195781/Union-find-Logical-Thinking
    
    4 cases:
        a) hit = 0, then output = 0
        b) hit = 1, but it cannot connect to top, then output = 0
        c) hit = 1, it can connect to the top and 4 adjacents "can" also connect to top
           so adjacents wont be dropped and output = 0
        d) hit = 1, it can connect to the top, but 4 adjacents "cannot" connect to top
            so adjacents will be dropped and we need to calculate the output here

    4 steps in implementation:
        a) create DSU with n+1 nodes, 
           where plus 1 is done for a "top most VIRTUAL root node" with index = 0
           assume the grid index from 1 to n. So total 1 + n nodes.
        b) intially erase all bricks before processing                  // main logic
        c) unionAllAround 4 sides, to form edges in the graph
        d) now iterate hits[] from the last and restore the hits. 
           formula for count = dsu.rank[dsu.find(0)]                    // main logic
           output = currCount - prevCount - 1
           where -1 is done to neglect current brick which is being removed
           
    example-1:
    output = (currCount - prevCount) - 1
           = (4 - 1) - 1
           = 2
*/

class Solution {
    int[][] grid, hits;
    int rows, cols, n;
    int[] dir;
    DSU dsu;
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.grid = grid;
        this.hits = hits;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.n = (rows * cols) + 1;
        this.dir = new int[] {-1, 0, 1, 0, -1};
        this.dsu = new DSU(n + 1);                                      // step-1: initialize DSU
        int x, y;
        int prevCount, currCount;
        int[] output = new int[hits.length];
        
        for (int[] hit : hits) {                                        // step-2: erase bricks initially
            x = hit[0];
            y = hit[1];
            if (grid[x][y] == 1) {
                grid[x][y] = 2;
            }
        }
        for (x = 0; x < rows; x++) {                                    // step-3: unionAllAround
            for (y = 0; y < cols; y++) {
                if (grid[x][y] == 1) {
                    unionAllAround(x, y);
                }
            }
        }
        prevCount = dsu.rank[dsu.find(0)];
        
        for (int i = hits.length - 1; i >= 0; i--) {                    // step-4: restore bricks and find output
            x = hits[i][0];
            y = hits[i][1];
            if (grid[x][y] == 0) {
                output[i] = 0;
            } else {
                grid[x][y] = 1;                                         // restore last erased brick
                unionAllAround(x, y);
                currCount = dsu.rank[dsu.find(0)];
                output[i] = Math.max((currCount - prevCount - 1), 0);   // main logic
                prevCount = currCount;
            }
        }
        return output;
    }
    
    public void unionAllAround(int x, int y) {                          // create edges with neighbours alone (NO DFS)
        int nx, ny;
        int currParent, nextParent;
        
        for (int d = 0; d < 4; d++) {
            nx = x + dir[d];
            ny = y + dir[d + 1];
            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] == 1) {
                currParent = dsu.find(getIndex(x, y));
                nextParent = dsu.find(getIndex(nx, ny));
                if (currParent != nextParent) {
                    dsu.union(currParent, nextParent);
                }
            }
        }
        if (x == 0) {                                                   // special handling for 1st row alone
            currParent = dsu.find(getIndex(x, y));
            nextParent = dsu.find(0);                                   // where 0 is top most virtual root node
            if (currParent != nextParent) {
                dsu.union(currParent, nextParent);
            }
        }
    }
    
    public int getIndex(int x, int y) {
        return ((x * cols) + y) + 1;
    }
}

class DSU {
    int[] parent;
    int[] rank;
    
    DSU(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    
    public void union(int u, int v) {
        if (rank[u] > rank[v]) {
            rank[u] += rank[v];
            parent[v] = u;
        } else {
            rank[v] += rank[u];
            parent[u] = v;
        }
    }
}