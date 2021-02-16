/*
You are given an integer array nums and an integer k.
In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
Return the maximum number of operations you can perform on the array.

Example 1:
Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.

Example 2:
Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109
*/


/*
    HashMap and 2-sum problem technique
    Time: n
    Space: n
*/
class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        Integer freq;
        int output = 0;
        
        for (int i = 0; i < nums.length; i++) {
            freq = freqMap.get(k - nums[i]);            // main logic: similar to 2 sum problem
            if (freq != null) {     
                output++;
                if (freq == 1) {
                    freqMap.remove(k - nums[i]);
                } else {
                    freqMap.put(k - nums[i], freq - 1);
                }
            } else {
                freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
            }  
        }
        return output;
    }
}


/*
    Sort and 2-pointer technique
    Time: n*logn
    Space: 1
*/
/*
class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int output = 0;
        int i = 0, j = nums.length - 1;
        
        while (i < j) {
            if (nums[i] + nums[j] > k) {
                j--;
            } else if (nums[i] + nums[j] < k) {
                i++;
            } else {
                i++;
                j--;
                output++;
            }
        }
        return output;
    }
}
*/