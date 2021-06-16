/*
Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. 
Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.
*/



/*
    1) brute force
        time: n*k
        space: 1
        
`   2) 2 heaps + lazy removal
        time: n*logn
        space: n
        logic: 
            remove random element in heap is costly operation. 
            so instead of removing it at that point of time, use a hashmap to store out of window elements. 
            once this element comes to root node, we can remove it in O(logn) time
            in worst case, heap will have n elements instead of k elements which increases the complexit
        
    3) 2 treeset or 2 BBST
        time: n*logk
        space: k
        logic:
            since duplicates are allowed, we store array index in treeset (dont store array value)
            left tree set stores min values in descending order -->  (similar to left child of BBST) 
            right tree set stores max values in ascending order-->  (similar to right child of BBST) 
        
    similar to: https://leetcode.com/problems/find-median-from-data-stream/
*/

public class Solution {
    TreeSet<Integer> left, right;
    int[] nums;
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        this.nums = nums;
        this.left = new TreeSet<Integer>((a, b) -> (nums[a] == nums[b]) ? a - b : Integer.compare(nums[b], nums[a]));
        this.right = new TreeSet<Integer>((a, b) -> (nums[a] == nums[b]) ? a - b : Integer.compare(nums[a], nums[b]));
        double[] output = new double[n - k + 1];
        
        for (int i = 0; i < n; i++) {
            insert(i);                                 // step1: insert
            if ((i - k + 1) >= 0) {
                output[i - k + 1] = getMedian();       // step2: get median
                remove(i - k + 1);                     // step3: remove out of window element
            }
        }
        return output;
    }
    
    public void insert(int i) {                        // similar to https://leetcode.com/problems/find-median-from-data-stream/
        if (left.isEmpty()) {
            left.add(i);
        } else if (left.size() <= right.size()) {      // add in left
            right.add(i);
            left.add(right.pollFirst());
        } else {                                       // add in right
            left.add(i);
            right.add(left.pollFirst());
        }
    }
    
    public double getMedian() {
        if (left.size() == right.size()) {
            return ((double) nums[left.first()] + nums[right.first()]) / 2;
        } else {
            return (double) nums[left.first()];
        }
    }
    
    public void remove(int i) {
        if (left.contains(i)) {                        // current index to remove can be in any tree set
            left.remove(i);
        } else {
            right.remove(i);
        }
    }
}