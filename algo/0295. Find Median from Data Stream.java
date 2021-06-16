/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.

Example:
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 
Follow up:
If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/



/*
    1) insertion sort + linkedlist + binary search --> Time: log(n), 1 and Space: n
    2) min heap + max heap + sliding windown --> Time: 3log(n/2), 1 and Space: n
    
    Similar to https://leetcode.com/problems/sliding-window-median/
    Solution to follow up: https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups/651523
*/

class MedianFinder {
    PriorityQueue<Integer> smallMaxHeap;
    PriorityQueue<Integer> largeMinHeap;

    public MedianFinder() {
        smallMaxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));   // max heap
        largeMinHeap = new PriorityQueue<Integer>();                    // min heap
    }
    
    public void addNum(int num) {
        if(smallMaxHeap.isEmpty()) {
            smallMaxHeap.add(num);
        } else if(smallMaxHeap.size() == largeMinHeap.size()) {         // insert in smallMaxHeap
            if(num <= largeMinHeap.peek()) {
                smallMaxHeap.add(num);
            } else {
                largeMinHeap.add(num);
                smallMaxHeap.add(largeMinHeap.remove());
            }
        } else {                                                        // insert in largeMinHeap
            if(num >= smallMaxHeap.peek()) {
                largeMinHeap.add(num);
            } else {
                smallMaxHeap.add(num);
                largeMinHeap.add(smallMaxHeap.remove());
            }
        }
    }
    
    public double findMedian() {
        if(smallMaxHeap.size() == largeMinHeap.size()) {
            return (smallMaxHeap.peek() + largeMinHeap.peek()) / (double)2;
        } else {
            return smallMaxHeap.peek(); // because either both size will be equal or smallMaxHeap > largeMinHeap
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */