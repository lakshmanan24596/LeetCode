/*
You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.

You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:

Choose one integer x from either the start or the end of the array nums.
Add multipliers[i] * x to your score.
Remove x from the array nums.

Return the maximum score after performing m operations.


Example 1:
Input: nums = [1,2,3], multipliers = [3,2,1]
Output: 14
Explanation: An optimal solution is as follows:
- Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
- Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
- Choose from the end, [1], adding 1 * 1 = 1 to the score.
The total score is 9 + 4 + 1 = 14.

Example 2:
Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
Output: 102
Explanation: An optimal solution is as follows:
- Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
- Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
- Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
- Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
- Choose from the end, [-2,7], adding 7 * 6 = 42 to the score. 
The total score is 50 + 15 - 9 + 4 + 42 = 102.

Constraints:
n == nums.length
m == multipliers.length
1 <= m <= 103
m <= n <= 105
-1000 <= nums[i], multipliers[i] <= 1000
*/



/*
    DP
    time: m * m, where m = multipliers.length
    space: n * n, where n = nums.length
    this solution is Memory Limit Exceeded because n >= m
*/
/*
class Solution {
    int[] nums;
    int[] multipliers;
    Integer[][] DP;
    
    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        this.DP = new Integer[nums.length][nums.length];    // n * n
        return maximumScoreDfs(0, nums.length - 1, 0);
    }
    
    public int maximumScoreDfs(int start, int end, int mIndex) {
        if (mIndex == multipliers.length) {
            return 0;
        }
        if (DP[start][end] != null) {
            return DP[start][end];
        }
        
        int pickStart = (nums[start] * multipliers[mIndex]) + maximumScoreDfs(start + 1, end, mIndex + 1);
        int pickEnd = (nums[end] * multipliers[mIndex]) + maximumScoreDfs(start, end - 1, mIndex + 1);
        return DP[start][end] = Math.max(pickStart, pickEnd);
    }
} 
*/


/*
    DP
    time: m * m
    space: m * m
    we can calculated "end index" using pickedElements
    this is space optimal because n >= m according to the question
*/
class Solution {
    int[] nums, multipliers;
    Integer[][] DP;
    
    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        this.DP = new Integer[multipliers.length][multipliers.length];  // m * m
        return maximumScoreDfs(0, 0);
    }
    
    public int maximumScoreDfs(int start, int pickedElements) {
        if (pickedElements == multipliers.length) {
            return 0;
        }
        if (DP[start][pickedElements] != null) {
            return DP[start][pickedElements];
        }
        int end = ((nums.length - 1) - pickedElements) + start;         // main logic when compared to previous solution
        
        int pickStart = (nums[start] * multipliers[pickedElements]) + maximumScoreDfs(start + 1, pickedElements + 1);
        int pickEnd = (nums[end] * multipliers[pickedElements]) + maximumScoreDfs(start, pickedElements + 1);
        return DP[start][pickedElements] = Math.max(pickStart, pickEnd);
    }
}
