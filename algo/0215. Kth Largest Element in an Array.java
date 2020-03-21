/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

class Solution 
{
    int[] heap;
    int k;
    
    public int findKthLargest(int[] nums, int k) 
    {
        // brute      : O(NK)  
        // sort       : O((N*logN)+K)
        // heap       : O(n*logK)
        // quick sort : O(2n)
         
        int n = nums.length;
        this.k = k;
        this.heap = new int[k];                // heap of size k
        
        for(int i = 0; i < k; i++)             // first K elements
            heap[i] = nums[i];
        
        for(int i = (k/2)-1; i >= 0; i--)
            minHeapify(i);
        
        for(int i = k; i < n; i++)              // remaining elements
        {
            if(nums[i] > heap[0])
            {
                heap[0] = nums[i];
                minHeapify(0);
            }
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


// Time = O(n^2) is worst case and O(2n) in avg case

// class Solution
// {
//     int[] nums;
//     int n, k;
    
//     public int findKthLargest(int[] nums, int k) 
//     {
//         this.n = nums.length;
//         this.nums = nums;
//         this.k = n - k;                             // because kth largest == (n-k) smallest        
//         return quickSort(0, n-1);
//     }
    
//     public int quickSort(int left, int right)
//     {
//         if(left > right)
//             return nums[k];
        
//         int j = partition(left, right);
//         if(j == k)                                  // found ans
//             return nums[k];
//         else if(k < j)
//             return quickSort(left, j -1);           // recur left or
//         else
//             return quickSort(j+1, right);           // recur right
//     }
    
//     public int partition(int left, int right)
//     {
//         int i = left, j = right;
//         int pivot = left;
        
//      while(i < j)
//      {
//          while(i < right && nums[i] <= nums[pivot])  // find nums[i] greater than pivot
//              i++;
//          while(j > left && nums[j] >= nums[pivot])   // find nums[j] lesser than pivot
//              j--;
//          if(i < j)
//              swap(i, j);
//      }
        
//      swap(pivot, j);
//      return j;
//     }
    
//     public void swap(int i, int j)
//     {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }



// O(n) solution using quick sort
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         return helper(0, nums.length - 1, nums,  nums.length - k);
//     }
//     private int helper(int s, int e, int[] nums, int k) {
//         if (s >= e) {
//             return nums[k];
//         }
//         int l = s;
//         int r = e;
//         int pivot = nums[l + (r - l) / 2];
//         while (l <= r) {
//             if (nums[l] < pivot) {
//                 l++;
//                 continue;
//             }
//             if (nums[r] > pivot) {
//                 r--;
//                 continue;
//             }
//             swap(r, l, nums);
//             l++;
//             r--;
//         }
//         // s...rl...e
//         if (l <= k) {
//             return helper(l, e, nums, k);
//         } else if (r >= k){
//             return helper(s, r, nums, k);
//         }
//         return nums[k];
//     }
//     private void swap(int s, int e, int[] nums) {
//         int tmp = nums[s];
//         nums[s] = nums[e];
//         nums[e] = tmp;
//     }
// }