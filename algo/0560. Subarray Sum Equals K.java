/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Constraints:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

class Solution
{
    public int subarraySum(int[] nums, int k) 
    {
        // logic: sum(j,i) = sum(0,i) - sum(0,j), 
        // where sum(0,i) can be found using currSum and sum(0,j) can be found using hashmap
        
        int currSum = 0, output = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // stores preSum and checks preSum - k exist
        map.put(currSum, 1);
        
        for(int i = 0; i < nums.length; i++)
        {
            currSum += nums[i];
            if(map.containsKey(currSum - k)) {  // main logic
                output += map.get(currSum - k);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return output;
    }
}


/*
1) hande +ve alone ---> Time=N, Space=1 ---> sliding window --> https://www.geeksforgeeks.org/find-subarray-with-given-sum/
2) hande -ve also ---> Time=N, Space=N ---> hashmap --> https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
3) hande -ve also ---> Time=N, Space=1 ---> sliding window --> https://www.geeksforgeeks.org/find-subarray-with-given-sum-with-negatives-allowed-in-constant-space/

class Solution 
{
    public int subarraySum(int[] nums, int k)   ---> solution 3 ---> hande -ve also with N time and 1 Space
    {
        int minElem = 0; // to handle negative elements
        for(int i = 0; i < nums.length; i++) // pre-processing
        {
            if(nums[i] < 0 && nums[i] < minElem) {
                minElem = nums[i];
            }
        }
        if(minElem < 0)
        {
            minElem *= -1;
            for(int i = 0; i < nums.length; i++) {
                nums[i] += minElem;
            }
        }
        // after processing, now array will contain only +ve elements. So sliding window logic can be used.

        int output = 0;
        int sum = 0;
        int start = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            sum += nums[i];    
            if(sum == (k + (minElem * (i-start+1)))) // replace k with --> (k + (minElem * (i-start+1))), is the main logic
            {
                output++;
            }
            else if(sum > (k + (minElem * (i-start+1))))
            {
                while(start < i)
                {
                    sum -= nums[start++]; // remove first char of window
                    if(sum == (k + (minElem * (i-start+1)))) {
                        output++;
                        break;
                    } 
                    else if(sum < (k + (minElem * (i-start+1)))) {
                        break;
                    }
                }
            }
        }
        return output;
    }
}
*/