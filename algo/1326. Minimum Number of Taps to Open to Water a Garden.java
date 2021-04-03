/*
There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
There are n + 1 taps located at points [0, 1, ..., n] in the garden.
Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

Example 1:
Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation: The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]

Example 2:
Input: n = 3, ranges = [0,0,0,0]
Output: -1
Explanation: Even if you activate all the four taps you cannot water the whole garden.

Example 3:
Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
Output: 3

Example 4:
Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
Output: 2

Example 5:
Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
Output: 1

Constraints:
1 <= n <= 10^4
ranges.length == n + 1
0 <= ranges[i] <= 100
*/




/*
    1) DP, sorting
        exactly similar to https://leetcode.com/problems/video-stitching/
        time: n^2
        space: n
        
    2) Greedy, sorting:
        exactly similar to https://leetcode.com/problems/video-stitching/
        https://leetcode.com/problems/video-stitching/discuss/269984/java-O(N*lgN)-greedy
        time: n*logn
        space: n
        
    3) Greedy:
        similar to https://leetcode.com/problems/jump-game-ii/
        time: n 
*/



/*
// greedy + sort, time: n*logn
class Solution {
    public int minTaps(int n, int[] ranges) {
        List<int[]> intervals = new ArrayList<int[]>();
        int minTaps = 0;
        int reach = 0, maxReach;
        
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] != 0) {
                intervals.add(new int[] {i - ranges[i], i + ranges[i]});
            }
        }
        Collections.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        for (int i = 0; i < intervals.size(); ) {
            if (intervals.get(i)[0] > reach) {
                return -1;
            }
            maxReach = reach;
            while (i < intervals.size() && intervals.get(i)[0] <= reach) {
                maxReach = Math.max(maxReach, intervals.get(i)[1]);
                i++;
            }
            reach = maxReach;
            minTaps++;
            if (reach >= n) {
                return minTaps;
            }
        }
        return -1;
    }
}
*/


/*
    greedy, time: n
    https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/discuss/506853/Java-A-general-greedy-solution-to-process-similar-problems
    similar to https://leetcode.com/problems/jump-game-ii/
    
    ex1: ranges = [3, 4, 1, 1, 0, 0], n = 5
         nums   = [5, 3, 4, 0, 4, 5]  
         now apply jump game 2 problem for nums array
*/
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] nums = new int[ranges.length];
        int index, val;
        
        for (int i = 0 ; i < ranges.length; i++) {
            index = Math.max(0, i - ranges[i]);             // i - ranges[i] is index
            val = Math.max(nums[index], i + ranges[i]);     // i + ranges[i] is val
            nums[index] = val;
        }
        return jump(nums);
    }
    
    public int jump(int[] nums) {                           // https://leetcode.com/problems/jump-game-ii/
        if(nums[0] == 0) {
            return -1;
        }
        int ladder = nums[0];
        int stairs = nums[0];
        int jumpCount = 1;
            
        for(int i = 1; i < nums.length - 1; i++) {
            ladder = Math.max(ladder, nums[i]);
            stairs--;
            
            if(stairs == 0) {
                jumpCount++;
                stairs = ladder - i;   
                if(stairs <= 0) {
                    return -1;
                }
            }
        }
        return jumpCount;
    }
}