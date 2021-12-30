/*
// O(n*output), brute force

class Solution {
    public int minMoves(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int maxVal = Integer.MIN_VALUE;
        int newVal;
        int moves = 0;
        
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            minHeap.add(num);
        }
        
        while (true) {
            int diff = maxVal - minHeap.peek();             // main logic
            if (diff == 0) {
                break;
            } 
            moves += diff;
            for (int i = 0; i < nums.length - 1; i++) {     // increment n-1 elements
                newVal = minHeap.remove() + diff;
                maxVal = Math.max(maxVal, newVal);
                minHeap.add(newVal);
            }
        }
        return moves;
    }
}
*/


/*
// O(nlogn), sorting

class Solution {
    public int minMoves(int[] nums) {
        int moves = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            moves += nums[i] - nums[0];
        }
        return moves;
    }
}
*/


// O(n), Math
class Solution {
    public int minMoves(int[] nums) {
        int moves = 0;
        int minVal = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            minVal = Math.min(minVal, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - minVal;                  // main logic
        }
        return moves;
    }
}
