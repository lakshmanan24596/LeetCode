/*
You are given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. 
Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
Return the maximum points you can get.

Example 1:
Input: boxes = [1,3,2,2,2,3,4,3,1]
Output: 23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)

Example 2:
Input: boxes = [1,1,1]
Output: 9

Example 3:
Input: boxes = [1]
Output: 1

Constraints:
1 <= boxes.length <= 100
1 <= boxes[i] <= 100
*/


/*
    DP memo
    similar to burst balloons
    
    logic:
        instead of processing first box to remove, we process with the last box to remove.
        any box can be a last box, so check with all boxes  --> 1st for loop in code --> extra n time
        for each box, we need to recur all inbetween boxes  --> 2nd for loop in code --> extra n time
    
    time: n^2 * n^2 ==> n^4
    space: n^2 * n  ==> n^3
*/


/*
// WRONG SOLUTION (52/62 cases passed)
// test case which break: [1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,2,2,1]

class Solution {
    int[] boxes;
    int[][] DP;
    
    public int removeBoxes(int[] boxes) {
        this.boxes = boxes;
        int n = boxes.length;
        this.DP = new int[n][n];
        return removeBoxesUtil(0, n - 1);
    }
    
    public int removeBoxesUtil(int left, int right) {
        if (left > right) {
            return 0;
        }
        if (DP[left][right] > 0) {
            return DP[left][right];
        }
        int maxPoints = 0, currPoints;
        int length, nextLeft, nextRight;    
        Set<Integer> isProcessed = new HashSet<Integer>();
        
        for (int i = left; i <= right; i++) {
            if (!isProcessed.contains(boxes[i])) {
                isProcessed.add(boxes[i]);
                length = 0;
                currPoints = 0;
                nextLeft = left;
                
                for (int j = i; j <= right; j++) {
                    if (boxes[j] == boxes[i]) {                                         // main condition
                        length++;
                        nextRight = j - 1;
                        currPoints += removeBoxesUtil(nextLeft, nextRight);             // main logic
                        nextLeft = j + 1;
                    }
                }
                nextRight = right;
                currPoints += removeBoxesUtil(nextLeft, nextRight);
                
                currPoints += length * length;
                maxPoints = Math.max(maxPoints, currPoints);
            }
        }
        return DP[left][right] = maxPoints;
    }
}
*/


/*
    https://leetcode.com/problems/remove-boxes/discuss/975800/Java-DP%2BDFS-code%2Bphoto
    https://leetcode.com/problems/remove-boxes/discuss/101328/Java-Preprocessing-DFS-%2B-Memoization-less-space-needed
    https://leetcode.com/problems/remove-boxes/discuss/645838/Evolve-from-intuition-to-dp
    k --> number of adjacent consecutive boxes with same color as left
    
    time: n^4
    space: n^3
*/
class Solution {
    private int[] boxes;
    private int[][][] dp;

    public int removeBoxes(int[] boxes) {
        this.boxes = boxes;
        this.dp = new int[boxes.length][boxes.length][boxes.length];
        return dfs(0, boxes.length - 1, 0);
    }

    private int dfs(int left, int right, int k) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right][k] != 0) {
            return dp[left][right][k];
        }
        while (left < right && boxes[left + 1] == boxes[left]) {
            left++;
            k++;
        }
        int result = (k + 1) * (k + 1) + dfs(left + 1, right, 0);
        for (int i = left + 1; i <= right; i++) {
            if (boxes[left] == boxes[i]) {
                result = Math.max(result, dfs(left + 1, i - 1, 0) + dfs(i, right, k + 1));
            }
        }
        dp[left][right][k] = result;
        return result;
    }
}