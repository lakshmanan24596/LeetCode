/*
You are given a 0-indexed array of strings words. Each string consists of lowercase English letters only. 
No letter occurs more than once in any string of words.

Two strings s1 and s2 are said to be connected if the set of letters of s2 can be obtained from the set of letters of s1 by any one of the following operations:
Adding exactly one letter to the set of the letters of s1.
Deleting exactly one letter from the set of the letters of s1.
Replacing exactly one letter from the set of the letters of s1 with any letter, including itself.

The array words can be divided into one or more non-intersecting groups. 

A string belongs to a group if any one of the following is true:
It is connected to at least one other string of the group.
It is the only string present in the group.

Note that the strings in words should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. 
It can be proved that such an arrangement is always unique.

Return an array ans of size 2 where:
ans[0] is the total number of groups words can be divided into, and
ans[1] is the size of the largest group.
 

Example 1:
Input: words = ["a","b","ab","cde"]
Output: [2,3]
Explanation:
- words[0] can be used to obtain words[1] (by replacing 'a' with 'b'), and words[2] (by adding 'b'). So words[0] is connected to words[1] and words[2].
- words[1] can be used to obtain words[0] (by replacing 'b' with 'a'), and words[2] (by adding 'a'). So words[1] is connected to words[0] and words[2].
- words[2] can be used to obtain words[0] (by deleting 'b'), and words[1] (by deleting 'a'). So words[2] is connected to words[0] and words[1].
- words[3] is not connected to any string in words.
Thus, words can be divided into 2 groups ["a","b","ab"] and ["cde"]. The size of the largest group is 3.  


Example 2:
Input: words = ["a","ab","abc"]
Output: [1,3]
Explanation:
- words[0] is connected to words[1].
- words[1] is connected to words[0] and words[2].
- words[2] is connected to words[1].
Since all strings are connected to each other, they should be grouped together.
Thus, the size of the largest group is 3.


Constraints:
1 <= words.length <= 2 * 104
1 <= words[i].length <= 26
words[i] consists of lowercase English letters only.
No letter occurs more than once in words[i].
*/



/*
    Observations:
    1)
        Words = ["a","ab","abc"]
        a --> ab --> abc then it means all 3 are in same component
        From this, we can say it is "Graph + DSU". This is the main logic
    2)
        How to find out 2 strings are connected or not?
        Use 2 loops to pick 2 strings to find it out --> time = O(W * W * WL), which is not optimal
    3)
        optimization: making use of "word ladder" problem "star technique + hashmap" to reduce time from n^2 to n
        example: cde
        we need to process cde, *de, c*e, cd* --> this is done for remove, replace
        which means we need to unset each bit in the initial word "cde"
        formula for unset --> unsetMask = mask & (~(1 << index));
    4) 
        current problem: order doesn't matter and only lowercase --> so bitmask can be used since it is just a count of each alphabet
        word ladder problem: order matter --> so bitmask CANT be used
        
    Logic: DSU + Hashmap + Bitmask
    Time: O(W * WL)
    Space: O(W * WL)
*/

class Solution {
    public int[] groupStrings(String[] words) {
        Map<Integer, Integer> maskVsWordIndex = new HashMap<Integer, Integer>();
        int n = words.length;
        DSU dsu = new DSU(n);
        String word;
        int index, mask, unsetMask;
        
        for (int u = 0; u < n; u++) {                               // example: u = "cde"
            word = words[u];
            mask = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                mask |= 1 << index;
            }
            process(mask, u, maskVsWordIndex, dsu);                 // insert mask of "cde" 
            
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                unsetMask = mask & (~(1 << index));
                process(unsetMask, u, maskVsWordIndex, dsu);        // insert mask of "cd", "ce", "de"
            }
        }
        
        int totalGroups = 0, sizeOfLargestGroup = 0;
        for (int i = 0; i < n; i++) {                               // find the output from dsu
            if (dsu.parent[i] == i) {
                totalGroups++;
                sizeOfLargestGroup = Math.max(sizeOfLargestGroup, dsu.rank[i]);
            }
        }
        return new int[] {totalGroups, sizeOfLargestGroup};
    }
    
    public void process(int mask, int u, Map<Integer, Integer> maskVsWordIndex, DSU dsu) {
        if (!maskVsWordIndex.containsKey(mask)) {
            maskVsWordIndex.put(mask, u);
        } else {
            int v = maskVsWordIndex.get(mask);
            int vParent = dsu.find(v);
            int uParent = dsu.find(u);
            if (uParent != vParent) {
                dsu.union(uParent, vParent);
            }
        }
    }
}

class DSU {
    int[] parent;
    int[] rank;
    
    DSU(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
    
    void union(int u, int v) {
        if (rank[u] >= rank[v]) {
            rank[u] += rank[v];
            parent[v] = u;
        } else {
            rank[v] += rank[u];
            parent[u] = v;
        }
    }
}