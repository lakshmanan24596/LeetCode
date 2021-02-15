/*
You are given an integer array nums where the ith bag contains nums[i] balls. 
You are also given an integer maxOperations.

You can perform the following operation at most maxOperations times:
Take any bag of balls and divide it into two new bags with a positive number of balls.
For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.

Return the minimum possible penalty after performing the operations.

Example 1:
Input: nums = [9], maxOperations = 2
Output: 3
Explanation: 
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.

Example 2:
Input: nums = [2,4,8,2], maxOperations = 4
Output: 2
Explanation:
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.

Example 3:
Input: nums = [7,17], maxOperations = 2
Output: 7

Constraints:
1 <= nums.length <= 105
1 <= maxOperations, nums[i] <= 109
*/


/*
    Logic: binary search on answer range
    https://www.youtube.com/watch?t=0&v=5ET1d6PfbNU&feature=youtu.be
    
    Time: n * log(maxValue)
    Space: 1
*/

class Solution {
    int[] nums;
    int maxOperations;
    
    public int minimumSize(int[] nums, int maxOperations) {
        this.nums = nums;
        this.maxOperations = maxOperations;
        int left = 1, right = Integer.MIN_VALUE;
        int mid, output = Integer.MAX_VALUE;
        
        for (int num : nums) {
            right = Math.max(right, num);
        }
        while (left <= right) {                                                     // binary search on possible answeer rang
            mid = (left + right) / 2;
            if (checkOperationCanBePerformed(mid)) {                                // check mid
                output = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return output;
    }
    
    public boolean checkOperationCanBePerformed(int penalty) {
        int currOper = 0;
        for (int num : nums) {
            if (num > penalty) {
                // currOper += ((int) Math.ceil(num / (double) penalty)) - 1;      // main logic
                currOper += (num - 1) / penalty;                                    
                if (currOper > maxOperations) {
                    return false;
                }
            }
        }
        return true;
    }
}

/*
    int get_cost(int val, int max_allow) {
        int quotient = val / max_allow;
        int rem = val % max_allow;
        return quotient + (rem == 0 ? -1 : 0);
    }
*/