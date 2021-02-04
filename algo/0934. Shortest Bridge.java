/*
In a given 2D binary array A, there are two islands.  
(An island is a 4-directionally connected group of 1s not connected to any other 1s.)
Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
Return the smallest number of 0s that must be flipped.  
(It is guaranteed that the answer is at least 1.)

Example 1:
Input: A = [[0,1],[1,0]]
Output: 1

Example 2:
Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2

Example 3:
Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 
Constraints:
2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
*/


/*
    Time: r*c, Space: r*c
    Logic:
        https://leetcode.com/problems/shortest-bridge/discuss/189293/C%2B%2B-BFS-Island-Expansion-%2B-UF-Bonus
        Find and expand
            a) do DFS and find any one of the island
            b) do BFS from one island and try to reach other island by expanding
*/
class Solution {
    int[][] A;
    int n;
    int[] dir = new int[]{0, -1, 0, 1, 0};
    Queue<int[]> queue;
    
    public int shortestBridge(int[][] A) {
        this.A = A;
        this.n = A.length;
        queue = new LinkedList<int[]>();
        
        outerloop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(i, j);
                    break outerloop;
                }
            }
        }
        return bfs();
    }
    
    public void dfs(int currX, int currY) {     // do DFS and find any one of the island
        A[currX][currY] = 2;
        queue.add(new int[] {currX, currY});
        int nextX, nextY;
        
        for (int i = 0; i < 4; i++) {
            nextX = currX + dir[i];
            nextY = currY + dir[i + 1];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && A[nextX][nextY] == 1) {
                dfs(nextX, nextY);
            }
        }
    }
    
    public int bfs() {                          // do BFS from one island and try to reach other island by expanding
        int currX, currY, nextX, nextY;
        int level = 1;
        
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            level++;
            while (queueSize-- > 0) {
                int[] curr = queue.remove();
                currX = curr[0];
                currY = curr[1];
                for (int i = 0; i < 4; i++) {
                    nextX = currX + dir[i];
                    nextY = currY + dir[i + 1];
                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                        if (A[nextX][nextY] == 1) {         // found other island, so return
                            return level - 2;
                        }
                        if (A[nextX][nextY] == 0) {         // not visited
                            A[nextX][nextY] = level;
                            queue.add(new int[] {nextX, nextY});
                        }
                    }
                }
            }
        }
        return -1;
    }
}