/*
Given an array nums of integers, you can perform operations on the array.
In each operation, you pick any nums[i] and delete it to earn nums[i] points. 
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
 
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.

Note:
The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].
*/


/*
    1) recursion ==> Time: 2^n, Space: n  ==> sort nums and then either pick or dont pick a element
    2) memorization ==> Time: n^2, Space: n
    3) tabulation space optimized ==> Time: n + 10000, Space: 10000 (same as house robber problem)
*/

/*
// recursion + memorization
class Solution 
{
    int[] DP, nums;
    int n;
    
    public int deleteAndEarn(int[] nums) 
    {
        this.nums = nums;
        n = nums.length;
        DP = new int[n];
        
        Arrays.sort(nums);
        return recur(0);
    }
    
    public int recur(int curr)
    {
        if(curr == nums.length) {
            return 0;
        }
        if(DP[curr] != 0) {
            return DP[curr];
        }
        
        int sum = nums[curr];
        int nextIndex = curr + 1;
        
        while(nextIndex < n && nums[nextIndex] == nums[curr]) {         // add duplicates
            sum += nums[nextIndex++];
        }
        
        while(nextIndex < n && nums[nextIndex] == nums[curr] + 1) {     // skip nums[i] + 1
            nextIndex++;
        }
        
        int pick = sum + recur(nextIndex);
        int dontPick = recur(curr + 1);
        
        return DP[curr] = Math.max(pick, dontPick);
    }
}
*/


/*
explanation:
    nums: [2, 2, 3, 3, 3, 4] (2 appears 2 times, 3 appears 3 times, 4 appears once)
    points: [0, 0, 4, 9, 4] <- This is the gold in each house!
*/
class Solution
{
    public int deleteAndEarn(int[] nums) 
    {
        int[] points = new int[10001];  
        // instead of 10,000 we can use maxValue in the nums array
        // also we can use treeMap
        
        for(int num : nums) {
            points[num] += num;
        }
        
        return rob(points);
    }
    
    // 198. House Robber problem
    public int rob(int[] nums)  // Time: n, Space: 2
    {
        int n = nums.length;        
        if(n == 0)
            return 0;
        if(n == 1)
            return nums[0];
          
        // logic: DP.. instead of array we can just use 2 variables.. space optimized DP
        int secondPrev = nums[0];
        int firstPrev = Math.max(nums[0], nums[1]);
        
        for(int i = 2; i < n; i++)
        {
            int curr = nums[i] + secondPrev;
            if(curr > firstPrev)
            {
                secondPrev = firstPrev;
                firstPrev = curr;
            }
            else
            {
                secondPrev = firstPrev;
            }
        }
        return firstPrev;
    }
}
