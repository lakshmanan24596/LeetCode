/*
Given a wooden stick of length n units. The stick is labelled from 0 to n. 
For example, a stick of length 6 is labelled as follows:
Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
You should perform the cuts in order, you can change the order of the cuts as you wish.
When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). 
Please refer to the first example for a better explanation.
Return the minimum total cost of the cuts.

Example 1:
Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).

Example 2:
Input: n = 9, cuts = [5,6,1,4,2]
Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.

Constraints:
2 <= n <= 10^6
1 <= cuts.length <= min(n - 1, 100)
1 <= cuts[i] <= n - 1
All the integers in cuts array are distinct.
*/



/*
    Logic: DP + DC
    MEMORY LIMIT EXCEEDED
    time: n^3
    space: n^2
*/
/*
class Solution {
    boolean[] cutPoints;
    int[][] minCostCache;
    
    public int minCost(int n, int[] cuts) {
        cutPoints = new boolean[n + 1];
        minCostCache = new int[n + 1][n + 1];           // memory limit exceeded
        
        for (int i = 0; i < cuts.length; i++) {
            cutPoints[cuts[i]] = true;
        }
        return minCostUtil(0, n);
    }
    
    public int minCostUtil(int start, int end) {
        if (end - start <= 1) {
            return 0;
        }
        if (minCostCache[start][end] > 0) {
            return minCostCache[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        int currCost;
        
        for (int i = start + 1; i <= end - 1; i++) {
            if (cutPoints[i]) {
                currCost = (end - start) + minCostUtil(start, i) + minCostUtil(i, end);     // main logic
                minCost = Math.min(minCost, currCost);
            }
        }
        minCost = (minCost == Integer.MAX_VALUE) ? 0 : minCost;
        return minCostCache[start][end] = minCost;
    }
}
*/



/* 
    Logic: DP + DC
    same as above, but instead of processing with n, we process with cuts.length
    time: cuts^3
    space: cuts^2
    same as 1000. Minimum Cost to Merge Stones.
*/
class Solution {
    List<Integer> cutPoints;
    int[][] minCostCache;
    
    public int minCost(int n, int[] cuts) {
        int c = cuts.length;
        cutPoints = new ArrayList<Integer>();
        minCostCache = new int[c + 2][c + 2];
        
        cutPoints.add(0);
        Arrays.sort(cuts);
        for (int i = 0; i < c; i++) {
            cutPoints.add(cuts[i]);
        }
        cutPoints.add(n);
        return minCostUtil(0, c + 1);
    }
    
    public int minCostUtil(int start, int end) {
        if (end - start <= 1) {
            return 0;
        }
        if (minCostCache[start][end] > 0) {
            return minCostCache[start][end];
        }
        int minCost = Integer.MAX_VALUE;
        int currCost;
        
        for (int i = start + 1; i <= end - 1; i++) {
            currCost = minCostUtil(start, i) + minCostUtil(i, end);     // main logic (left + right)
            minCost = Math.min(minCost, currCost);
        }
        minCost += cutPoints.get(end) - cutPoints.get(start);           // main logic (current cut cost)
        return minCostCache[start][end] = minCost;
    }
}