/*
The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. 
In one operation, you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.

Example 1:
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.

Example 2:
Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.

Example 3:
Input: nums = [3,9,6], k = 2
Output: 1

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 105
*/


/*
    1) brute force
        time: n^2
        space: 1
        logic: sort the array and then, consider each element as most frequent element and check for all remaining elements in left side
    
    2) logic: sort + sliding window + math
        time: n*logn
        space: 1
    
        explanation for currK += (nums[end] - nums[end - 1]) * (end - start);
            example: 1,   4,   10,   12
                    (0)+(3*1)+(6*2)+(2*3)
            All the elements in the sliding window needs to moved to 12. 
            Assuming 1, 4, 10, 12 has been changed to 10, 10, 10, 12 and now the currDiff = 12-10 = 2. 
            All the remaining elements needs to be changed to 12. For that, output = 2+2+2 = 2*3
*/

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);                                          // main logic
        int start = 0, currK = 0;
        int output = 1;
        
        for (int end = 1; end < nums.length; end++) {
            currK += (nums[end] - nums[end - 1]) * (end - start);   // main logic
            
            while (currK > k) {
                currK -= nums[end] - nums[start];
                start++;
            }
            output = Math.max(output, (end - start) + 1);
        }
        return output;
    }
}
