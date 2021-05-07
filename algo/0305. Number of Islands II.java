/*
You are given an empty 2D binary grid grid of size m x n. 
The grid represents a map where 0's represent water and 1's represent land. 
Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. 
You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.

Example 2:
Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]

Constraints:
1 <= m, n, positions.length <= 104
1 <= m * n <= 104
positions[i].length == 2
0 <= ri < m
0 <= ci < n

Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
*/



/*
    1) brute force:
        for each position, find out the number of islands
        time: O(pos * (rows * cols))
        space: O(rows * cols) for visited array in dfs
        
    2) DSU:
        question understanding:
            in ex-1, if additional position (0,2) is given in the last, then output = 2 and not 4
            this is because, (0,2) merges --> (0,1) and (1,2)
        logic:
            each island is each set in DSU
            for each position, check its adjacent 4 sides, and merge (union) them into a single set
        time: 
            O(rows * cols) for initializing DSU
            O(pos * 4 * (log(rows * cols))) for processing
            If we consider DSU as amortized O(1), then time = O(pos * 4)
            so overall time complexity = O((rows * cols) + pos)
        space: 
            O(rows * cols) for DSU and visited set
            O(pos) for outputList
            
    Furthur optimization:
        Instead of initializing the entire DSU (m * n) upfront, we can do it "on-demand" while processing.
        Now, time = O(pos), space = O(pos)
        It works better in case of a huge grid, with a small amount of lands.
        https://leetcode.com/problems/number-of-islands-ii/discuss/995970/O(L)-Time-and-O(L)-Space-Java-solution
*/

class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);                   // time and space complexity: both are O(m * n)
        int islands = 0;
        List<Integer> numIslandList = new ArrayList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int[] dir = new int[] {0, -1, 0, 1, 0};
        int currX, currY, currPos;
        int nextX, nextY, nextPos;
        
        for (int[] position : positions) {
            currX = position[0];
            currY = position[1];
            currPos = getIndex(currX, currY, n);
            if (!visited.contains(currPos)) {       // avoid duplicate input positions
                islands++;
                
                for (int d = 0; d < dir.length - 1; d++) {
                    nextX = currX + dir[d];
                    nextY = currY + dir[d + 1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                        nextPos = getIndex(nextX, nextY, n);

                        if (visited.contains(nextPos)) {
                            int u = dsu.findParent(currPos);
                            int v = dsu.findParent(nextPos);
                            if (u != v) {
                                dsu.union(u, v);    // main logic
                                islands--;          // main logic
                            }
                        }
                    }

                }
                visited.add(currPos);
            }
            numIslandList.add(islands);
        }
        return numIslandList;
    }
    
    public int getIndex(int x, int y, int cols) {   // convert 2D array index to 1D array indes
        return (x * cols) + y;
    }
}

class DSU {
    int[] parent;
    int[] rank;
    
    DSU(int nodes) {
        this.parent = new int[nodes];
        this.rank = new int[nodes];
        
        for (int i = 0; i < nodes; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int findParent(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = findParent(parent[i]);
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