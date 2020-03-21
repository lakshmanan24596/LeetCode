/*
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.
*/

class KthLargest 
{
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
    
    int[] heap;                                    // heap of size k
    int k;  
    
    public KthLargest(int k, int[] nums)           // refer: https://leetcode.com/problems/kth-largest-element-in-an-array/ 
    {
        int n = nums.length;
        this.k = k;
        this.heap = new int[k];
        
        if(k <= n)                                 // first K elements
        {
            for(int i = 0; i < k; i++)             
                heap[i] = nums[i];
        } 
        else
        {
            int i;
            for(i = 0; i < n; i++)                 
                heap[i] = nums[i];
            for(; i < k; i++)
                heap[i] = Integer.MIN_VALUE;       // put -infinity for remaining elements
        }
        
        for(int i = (k/2)-1; i >= 0; i--)
            minHeapify(i);
        
        for(int i = k; i < n; i++)                 // remaining elements
        {
            if(nums[i] > heap[0])
            {
                heap[0] = nums[i];
                minHeapify(0);
            }
        }
    }
    
    public int add(int val)                        // add in heap and return root element of heap   (Time: log k)
    {
        if(val > heap[0])
        {
            heap[0] = val;
            minHeapify(0);
        }
        return heap[0];
    }
    
    public void minHeapify(int i)                                  // for heap sort alone, send size as a extra param
    {
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        int small = i;
        
        if(left >= 0 && left < k && heap[left] < heap[small])       // k is the size of the heap
            small = left;
        
        if(right >= 0 && right < k && heap[right] < heap[small])
            small = right;
        
        if(small != i)
        {
            swap(small, i);
            minHeapify(small);
        }
    }
    
    public void swap(int i, int j)
    {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
