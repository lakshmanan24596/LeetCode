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
    1) 2 heaps --> n * k (becuase pQueue remove is k)
    2) treeset or BBST --> n * log k
    
    Implementation:
        1) remove out of window element
        2) add curr element
        3) get median
*/

public class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int idx = 0;
        TreeMap<Integer, Integer> small = new TreeMap<>((a, b)->{return (int)((double)b-a);});
        int smallSize = 0; 
        TreeMap<Integer, Integer> big = new TreeMap<>();
        int bigSize = 0;
        
        for(int i = 0; i<nums.length; i++){
            if(smallSize + bigSize == k){                           // step1: remove
                if(nums[i-k] <= small.firstKey()){
                    remove(small, nums[i-k]);
                    smallSize--;
                }else{
                    remove(big, nums[i-k]);
                    bigSize--;
                }
            }

            if(smallSize<=bigSize){                                 // step2: add
                add(small, nums[i]);
                smallSize++;
            }else{
                add(big, nums[i]);
                bigSize++;
            }
            if(bigSize>0){
                while(small.firstKey() > big.firstKey()){
                    add(big, remove(small, small.firstKey()));
                    add(small, remove(big, big.firstKey()));
                }
            }
            
            if(smallSize + bigSize==k){                             // step3: getMedian
                if(k % 2 == 0) res[idx++] = ((double)small.firstKey() + big.firstKey())/2.0;
                else res[idx++] = (double)small.firstKey();
            }
        }
        return res;
    }
    
    private int remove(TreeMap<Integer, Integer> map, int i){
        map.put(i, map.get(i)-1);
        if(map.get(i)==0) map.remove(i);
        return i; 
    }
    
    private void add(TreeMap<Integer, Integer> map, int i){
        if(!map.containsKey(i)) map.put(i, 1);
        else map.put(i, map.get(i)+1);
    }
}


/*
// INCOMPLETE CODE
class Solution {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    Integer midVal;
    int k;
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        this.k = k;
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(a, b -> (b > a) ? 1 : -1);
        midVal = null;
        int halfSize = (k % 2 == 0) ? (k / 2) : ((k - 1) / 2);
        double[] median = new double[nums.length - k + 1];
        
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = 0; i < halfSize; i++) {
            maxHeap.add(minHeap.remove());
        }
        if (k % 2 == 1) {
            midVal = minHeap.remove();
        }
        
        for (int i = k; i < n; i++) {           // main iteration (time: n * k)
            median[i - k] = getMedian();
            remove(nums[i - k]);
            add(nums[i]);
        }
        return median;
    }
    
    public double getMedian() {                 // main logic
        if (k % 2 == 1) {
            return midVal;
        } else {
            return (minHeap.peak() + maxHeap.peak()) / 2;
        }
    }
    
    public void remove(int num) {
        if (num == midVal) {
            midVal = null;
        } else if (num <= maxHeap.peak()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
    }
    
    public void add(int num) {
        if (k % 2 == 0) {
            if (minHeap.size() > maxHeap.size()) {
                minHeap.add(num);
                maxHeap.add(minHeap.remove());
            } else {
                maxHeap.add(num);
                minHeap.add(maxHeap.remove());
            }
        } else {
            if (midVal == null) {
                midVal = num;
            } else {
                
            }
        }
    }
}
*/