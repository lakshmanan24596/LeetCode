/*
You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. 
The root of the tree is node 0.
Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. 
If there is no such ancestor, return -1.
The k-th ancestor of a tree node is the k-th node in the path from that node to the root.

Example:
Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor

Constraints:
1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.
*/



/*
    1) brute force
        time: k
        space: 1
        
    2) DP
        time: k or 1
        space: n * n
        
    3) DP + binary lifting
        time: log(k)
        space: n * log(n)
        
        Instead of storing all the ancestors, we can store only 2 power ancestor nodes
        For each node, it stores 1st, 2nd, 4th, 8th and so on ancestors.
        
        if k = 12, then 1, 2, 4, 8 and then recur by changing node and k value
        now k = 12 - 8 = 4 and node = ancestor(node 8)
        recur again with new k, node
        base case: node == -1 || k == 0 then return node
*/


/*
// DP
// MEMORY LIMIT EXCEEDED

class TreeAncestor {
    int n;
    int[] parent;
    Integer[][] DP;
    
    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.parent = parent;
        DP = new Integer[n][n+1];                           // memory limit exceeded
    }
    
    public int getKthAncestor(int node, int k) {
        if (node == -1 || k == 0) {
            return node;
        }
        if (DP[node][k] != null) {
            return DP[node][k];
        }
        
        DP[node][k] = getKthAncestor(parent[node], k - 1);  // main logic
        return DP[node][k];
    }
}
*/


// DP + binary lifting
class TreeAncestor {
    int[][] jump;
    int maxPow;
    
    public TreeAncestor(int n, int[] parent) {
        maxPow = (int) (Math.log(n) / Math.log(2)) + 1;
        jump = new int[maxPow][n];
        jump[0] = parent;
        int prevNode;
            
        for (int p = 1; p < maxPow; p++) {
            for (int i = 0; i < n; i++) {
                prevNode = jump[p - 1][i];
                jump[p][i] = prevNode == -1 ? -1 : jump[p - 1][prevNode];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int p = this.maxPow;
        
        while (k > 0 && node > -1) {
            if (k >= (1 << p)) {
                node = jump[p][node];           // main logic: change node
                k -= 1 << p;                    // main logic: change k
            } else {
                p -= 1;
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */