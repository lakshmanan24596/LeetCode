/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
*/

class Solution 
{
    Map.Entry<Integer,Integer>[] heap;
    Map<Integer, Integer> map;
    
    public int[] topKFrequent(int[] nums, int k)    // logic : hashMap + minHeap --> Time: O(n * log k)
    {
        map = new HashMap<Integer, Integer>();
        heap = new Map.Entry[k];     
        int heapCurrSize = 0;
        int n = nums.length;
        
        for(int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(heapCurrSize < k)	// first K elements
            {
            	heap[heapCurrSize] = entry;
            	int i = heapCurrSize;
            	while(i != 0 && heap[i].getValue() < heap[getParent(i)].getValue()) {
            		swap(i, getParent(i));
            		i = getParent(i);
            	}
            	heapCurrSize++;
            }
            else                    // remaining elements
            {
            	if(entry.getValue() > heap[0].getValue()) {
            		heap[0] = entry;
            		minHeapify(0, heapCurrSize);
            	}
            }
        }
        
        int[] output = new int[heapCurrSize];
        for(int i = heapCurrSize - 1; i >= 0; i--) {
            output[i] = heap[i].getKey();
        }
        return output;
    }
    
    public void minHeapify(int index, int size) 
    {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest = index;
        
        if(left < size && heap[left].getValue() < heap[smallest].getValue()) {
            smallest = left;
        }
        if(right < size && heap[right].getValue() < heap[smallest].getValue()) {
            smallest = right;
        }
        
        if(index != smallest) {
            swap(smallest, index);
            minHeapify(smallest, size);a
        }
    }
    
    public void swap(int a, int b)
    {
        Map.Entry<Integer, Integer> temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    
    public int getParent(int i) {
    	return (i - 1) / 2;
    }
    public int getLeft(int i) {
    	return (2 * i) + 1;
    }
    public int getRight(int i) {
    	return (2 * i) + 2;
    }
}
