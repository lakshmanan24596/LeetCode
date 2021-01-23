/*
On a 2D plane, we place n stones at some integer coordinate points. 
Each coordinate point may have at most one stone.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

Example 3:
Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

Constraints:
1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
*/


/*
    output = number of components - connected components in a graph
    for finding connected components in an undirected graph, we can use union-find or dfs

    Approach 1:
        Time: n * n * logn, (where log(n) is for dsu.find())
        Space: n
        dsu size: n
        add a edge between 2 stones when either x or y is same

    Approach 2:
        Time: n * logn, (where log(n) is for dsu.find())
        Space: n
        logic is exactly same as above solution.
        dsu size:
            1) array of size 10000 * 2 (or)
            2) hashmap
        to separate x and y co-ordinate: (main logic)
            1) x = x and y = y + 10000 (or)
            2) x = x and y = ~y
*/
class Solution {
    public int removeStones(int[][] stones) {     // n^2 time solution (approach 1)
        int n = stones.length;
        DSU dsu = new DSU(n);
        int parentA, parentB;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    parentA = dsu.find(i);
                    parentB = dsu.find(j);
                    if (parentA != parentB) {
                        dsu.union(parentA, parentB);
                    }
                }
            }
        }
        int removedStones = 0;
        for(int i = 0; i < n; i++) {
            if(dsu.parent[i] != i) {
                removedStones++;
            }
        }
        return removedStones;   // number of components - connected components
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if(parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
         
        public void union(int a, int b) {
            if(rank[a] > rank[b]) {
                parent[b] = a;
                rank[a] += rank[b];
            } else {
                parent[a] = b;
                rank[b] += rank[a];
            }
        }
    }
}