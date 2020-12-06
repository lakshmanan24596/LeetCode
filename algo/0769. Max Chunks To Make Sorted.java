/*
Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  
After concatenating them, the result equals the sorted array.
What is the most number of chunks we could have made?

Example 1:
Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.

Example 2:
Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.

Note:
arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
*/

/*
    https://leetcode.com/problems/max-chunks-to-make-sorted/discuss/113528/Simple-Java-O(n)-Solution-with-detailed-explanation
    
    main logic:
    the first chunk is upto --> where 0th element is present and all elements within that chunk is present
    this can be found using max[i] == i
*/

/*
class Solution 
{
    public int maxChunksToSorted(int[] arr)     // Time: N, Space: N
    {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        int[] max = new int[n];
        max[0] = arr[0];
        
        for(int i = 1; i < n; i++) {
            max[i] = Math.max(max[i-1], arr[i]);
        }
        
        int output = 0;
        for(int i = 0; i < n; i++) 
        {
            if(max[i] == i) {  // main logic --> ex: if index is 3, then elemnts 0 to 3 is surely present here
                output++;
            }
        }
        return output;
    }
}
*/

class Solution
{
    public int maxChunksToSorted(int[] arr)     // Time: N, Space: 1
    {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        int max = -1;
        
        int output = 0;
        for(int i = 0; i < n; i++) 
        {
            max = Math.max(max, arr[i]);
            if(max == i) {  // main logic --> ex: if index is 3, then elemnts 0 to 3 is surely present here
                output++;
            }
        }
        return output;
    }
}
