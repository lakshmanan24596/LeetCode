/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
*/


/*
class Solution 
{
    public int findMaxLength(int[] nums)    // Time: n, Space: n
    {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[i] = -1;
            }
        }
        
        int sum = 0;
        int output = 0, currOutput;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();   // key,value = sum,index
        map.put(sum, -1);
        
        for(int i = 0; i < nums.length; i++) 
        {
            sum += nums[i];
            if(map.containsKey(sum)) {
                currOutput = i - map.get(sum);
                output = Math.max(output, currOutput);
            } 
            else {
                map.put(sum, i);
            }
        }
        return output;
    }
}
*/

// same as above solution, but instead of hashmap we use array which is much faster
// hashmap keys are going to be only from -n to +n including 0. So create arr with 2n + 1 space
class Solution 
{
    public int findMaxLength(int[] nums)    // Time: n, Space: n
    {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[i] = -1;
            }
        }
        
        int sum = 0, sumIndex = nums.length;
        int output = 0, currOutput;
        Integer[] mapArr = new Integer[(2 * nums.length) + 1];
        mapArr[sumIndex] = -1;
        
        for(int i = 0; i < nums.length; i++) 
        {
            sum += nums[i];
            sumIndex = sum + nums.length;
                
            if(mapArr[sumIndex] != null) {
                currOutput = i - mapArr[sumIndex];
                output = Math.max(output, currOutput);
            } 
            else {
                mapArr[sumIndex] = i;
            }
        }
        return output;
    }
}
