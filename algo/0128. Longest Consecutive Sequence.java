/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Constraints:
0 <= nums.length <= 104
-109 <= nums[i] <= 109
*/



/*
    1) simple sort and linear scan
        time: n*logn + n
        space: 1
        
    2) graph DFS or DSU
        create a edge between val and val + 1
        output = node which has higher rank in dsu
        time: n     (assuming union-find is amortized O(1)) (also we can use simple DSU which is also O(n) time)
        space: n
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> valToIndexMap = new HashMap<Integer, Integer>();
        DSU dsu = new DSU(nums.length);
        int longestConsecutive = 0;
        int value, currParent, consecutiveParent;
        
        for (int i = 0; i < nums.length; i++) {
            valToIndexMap.put(nums[i], i);
        }
        for (Map.Entry<Integer, Integer> entry : valToIndexMap.entrySet()) {    // process only unique elements
            value = entry.getKey();
            if (valToIndexMap.containsKey(value + 1)) {                           // main logic
                currParent = dsu.find(entry.getValue());
                consecutiveParent = dsu.find(valToIndexMap.get(value + 1));
                if (currParent != consecutiveParent) {
                    dsu.union(currParent, consecutiveParent);
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (dsu.parent[i] == i) { 
                longestConsecutive = Math.max(longestConsecutive, dsu.rank[i]);  // main logic
            }
        }
        return longestConsecutive;
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int curr) {
            if (parent[curr] == curr) {
                return curr;
            }
            return parent[curr] = find(parent[curr]);
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