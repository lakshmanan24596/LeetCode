/*
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
You can swap the characters at any pair of indices in the given pairs any number of times.
Return the lexicographically smallest string that s can be changed to after using the swaps.

Example 1:
Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"

Example 2:
Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"

Example 3:
Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"

Constraints:
1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
*/


/*
    Union-Find:
        Logic: ex --> "dcab" and [0,1],[1,3]
        we can see that [0,3] is also a valid pair.
        0, 1, 3 becomes a single connected componenets and 2 is a another component
        the above line tells us that union-find can be applied for this problem
        so the main logic is, we can sort each components individually. To do this, we use a pQueue.
        pqArr[] = <d,c,b>, <>, <a>, <>
*/
class Solution {
    Node[] disjointSet;
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        disjointSet = new Node[n];
        int rootA, rootB, root;
        PriorityQueue<Character>[] pqArr = new PriorityQueue[n];
        PriorityQueue<Character> pq;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            disjointSet[i] = new Node(i);  // initialize disjoint set
        }
        for(List<Integer> pair : pairs) {
            rootA = find(pair.get(0));
            rootB = find(pair.get(1));
            if(rootA != rootB) {
                union(rootA, rootB);  // form connected componenets
            }
        }
        
        for(int i = 0; i < n; i++) {
            pqArr[i] = new PriorityQueue<Character>();
        }
        for(int i = 0; i < n; i++) {
            root = find(i);
            pq = pqArr[root];
            pq.add(s.charAt(i));    // add in pQueue for sorting purpose
        }
        for(int i = 0; i < n; i++) {
            root = find(i);
            pq = pqArr[root];
            sb.append(pq.remove()); // remove in pQueue to form output
        }
        return sb.toString();
    }
    
    public int find(int curr) {
        Node currNode = disjointSet[curr];
        if (currNode.parent != curr) {
            currNode.parent = find(currNode.parent);    // path compression
        }
        return currNode.parent;
    }
    
    public void union(int a, int b) {
        Node nodeA = disjointSet[a];
        Node nodeB = disjointSet[b];
        
        if(nodeA.rank < nodeB.rank) {   // union by rank
            nodeA.parent = nodeB.parent;
            nodeB.rank += nodeA.rank;
        } else {
            nodeB.parent = nodeA.parent;
            nodeA.rank += nodeB.rank;
        }
    }
    
    class Node {
        int parent;
        int rank = 1;
        Node(int parent) {
            this.parent = parent;
        }
    }
}