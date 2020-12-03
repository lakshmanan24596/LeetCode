/*
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
You may assume the array's length is at most 10,000.

Example:
Input: [1,2,3]
Output: 2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
*/

/*
    1) brute force --> n^2, 1
    2) sorting --> n*logn, 1
    3) quick select --> n avg case, nlogn worst case
*/

// sorting based solution: Time: n*logn + n ==> n*logn
class Solution 
{
    public int minMoves2(int[] nums) 
    {
        Arrays.sort(nums);
        int n = nums.length;
        int midVal = nums[n / 2];
        int output = 0;
        
        for(int i = 0; i < n; i++) {
            output += Math.abs(midVal - nums[i]);   // main logic
        }
        return output;
    }
}

/*
// quick select --> n avg case, nlogn worst case
class Solution
{
    int[] nums;
    int n, k;
    
    public int minMoves2(int[] nums) 
    {
        this.nums = nums;
        this.n = nums.length;
        this.k = n / 2;                             // because we need mid           
        int midVal = quickSort(0, n-1);
        
        int output = 0;
        for(int i = 0; i < n; i++) {
            output += Math.abs(midVal - nums[i]);   // main logic
        }
        return output;
        
    }
    
    public int quickSort(int left, int right)
    {
        if(left > right)
            return nums[k];
        
        int j = partition(left, right);
        if(j == k)                                  // found ans
            return nums[k];
        else if(k < j)
            return quickSort(left, j -1);           // recur left or
        else
            return quickSort(j+1, right);           // recur right
    }
    
    public int partition(int left, int right)
    {
        int i = left, j = right;
        int pivot = left;
        
        while(i < j)
        {
            while(i < right && nums[i] <= nums[pivot])  // find nums[i] greater than pivot
                i++;
            while(j > left && nums[j] >= nums[pivot])   // find nums[j] lesser than pivot
                j--;
            if(i < j)
                swap(i, j);
        }
        
        swap(pivot, j);
        return j;
    }
    
    public void swap(int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
*/

/*
class Solution {
    public int minMoves2(int[] A) {
        int sum = 0, median = quickselect(A, A.length/2+1, 0, A.length-1);
        for (int i=0;i<A.length;i++) sum += Math.abs(A[i] - median);
        return sum;
    }

    public int quickselect(int[] A, int k, int start, int end) {
        int l = start, r = end, pivot = A[(l+r)/2];
        while (l<=r) {
            while (A[l] < pivot) l++;
            while (A[r] > pivot) r--;
            if (l>=r) break;
            swap(A, l++, r--);
        }
        if (l-start+1 > k) return quickselect(A, k, start, l-1);
        if (l-start+1 == k && l==r) return A[l];
        return quickselect(A, k-r+start-1, r+1, end);
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
*/
