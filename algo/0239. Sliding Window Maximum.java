/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 

Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
*/

class Solution 
{
    public int[] maxSlidingWindow(int[] nums, int k)    // Time = 2N. (Each element is added and popped in deque only once)
    {
        int n = nums.length;
        int output[] = new int[n-k+1];  
        Deque<Integer> deque = new LinkedList<Integer>(); 
        
        int i = 0;
        for(; i < k; i++) {           
            while(!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);                           // add index in the deque
        }
        output[i-k] = nums[deque.peekFirst()];
        
        for(; i < n; i++) {
            while(!deque.isEmpty() && deque.peekFirst() <= i-k ) {
                deque.pollFirst();                      // delete out of window elements
            }
            while(!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();                       // keep deque in descending sorted order
            }
            deque.addLast(i);
            output[i-k+1] = nums[deque.peekFirst()];
        }
        return output;
    }
}

/*
    O(3N) solution. It is faster than deque because deque use DLL and below solution uses array.
    For Example: A  = [2,1,3,4,6,3,8,9,10,12,56], w=4

    1)  partition the array in blocks of size w=4. The last block may have less then w.
                 A  = 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56

    2)  Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
        left_max[]  = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56

    3)  Similarly calculate max in future by traversing from end to start.
        right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

    4)  now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
        sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
*/
