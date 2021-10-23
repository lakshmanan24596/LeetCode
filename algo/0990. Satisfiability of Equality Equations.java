/*
Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  
Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

Example 1:
Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.

Example 2:
Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.

Example 3:
Input: ["a==b","b==c","a==c"]
Output: true

Example 4:
Input: ["a==b","b!=c","c==a"]
Output: false

Example 5:
Input: ["c==c","b==d","x!=z"]
Output: true

Note:
1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='
*/


/* 
    1) brute force: time: n^2
    2) DSU: time: n * logn, space: 26
*/

class Solution {
    public boolean equationsPossible(String[] equations) {
        DSU dsu = new DSU(26);
        String currEq;
        int a, b, setA, setB;
        boolean isEquals;
        
        for (int i = 0; i < equations.length; i++) {
            currEq = equations[i];
            if (currEq.charAt(1) == '=') {                      // process "==" first
                a = currEq.charAt(0) - 'a';
                b = currEq.charAt(3) - 'a';
                setA = dsu.find(a);
                setB = dsu.find(b);
                dsu.union(setA, setB);                          // group them together
            }
        }
        
        for (int i = 0; i < equations.length; i++) {
            currEq = equations[i];
            if (currEq.charAt(1) == '!') {                      // process "!=" second
                a = currEq.charAt(0) - 'a';
                b = currEq.charAt(3) - 'a';
                setA = dsu.find(a);
                setB = dsu.find(b);
                if (setA == setB) {                             // main logic
                    return false;
                }
            }
        }
        return true;
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU (int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); 
        }
        
        public void union(int a, int b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
                rank[a] += rank[b];
            } else {
                parent[a] = b;
                rank[b] += rank[a];
            }
        }
    }
}