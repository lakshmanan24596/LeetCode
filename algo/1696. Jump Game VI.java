/*
You are given a 0-indexed integer array nums and an integer k.
You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. 
That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
You want to reach the last index of the array (index n - 1). 
Your score is the sum of all nums[j] for each index j you visited in the array.
Return the maximum score you can get.

Example 1:
Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.

Example 2:
Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

Example 3:
Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0

Constraints:
 1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
*/



/*
    this solution doesn't work for below test case:
    a) k = 2 and nums = [-5,-3,-4,-6,-2,-7]
    b) k = 2 and nums = [0,-1,-2,-3,1]
*/
/*
// WRONG SOLUTION

class Solution {
    public int maxResult(int[] nums, int k) {
        int sum = nums[0];
        int prevIndex = 0;
        int max = Integer.MIN_VALUE;
        
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] >= 0) {                             // add all +ve numbers
                sum += nums[i];
                prevIndex = i;
                max = Integer.MIN_VALUE;
            } else {
                max = Math.max(max, nums[i]);  
                if (i - prevIndex >= k) {
                    sum += max;                             // for negative, add only max number within k range
                    prevIndex = i;
                    max = Integer.MIN_VALUE;
                }
            }
        }
        sum += nums[nums.length - 1];
        return sum;
    }
}
*/


/*
    DP[i] be the maximum score to reach at index i
    Time: n*k (TLE)
    Space: n
*/
/*
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] DP = new int[n];
        DP[0] = nums[0];
        int max;
        
        for (int i = 1; i < n; i++) {
            max = Integer.MIN_VALUE;
            for (int j = 1; j <= k && i - j >= 0; j++) {
                max = Math.max(max, DP[i-j]);
            }
            DP[i] = nums[i] + max;
        }
        return DP[n-1];
    }
}
*/


/*
    DP + Deque
        Logic: exactly same as above
        But instead of iterating k times and finding max value, we can use Deque to find max
        We can use deque with window size k 
        refer problem: 239. Sliding Window Maximum
    
    Time: 2n
    Space: n + k (for DP and deque)
*/
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] DP = new int[n];
        DP[0] = nums[0];
        Deque<Integer> deque = new LinkedList<Integer>(); 
        deque.addLast(0);
        
        for (int i = 1; i < n; i++) {
            DP[i] = nums[i] + DP[deque.peekFirst()];        // main logic
            
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();                          // delete out of window elements
            }
            while (!deque.isEmpty() && DP[i] > DP[deque.peekLast()]) {
                deque.pollLast();                           // keep deque in descending sorted order
            }
            deque.addLast(i);
        }
        return DP[n-1];
    }
}