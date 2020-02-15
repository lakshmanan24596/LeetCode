// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

// The replacement must be in-place and use only constant extra memory.

// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1


class Solution 
{
    public void nextPermutation(int[] nums) 
    {       
        int length = nums.length;
        if(length == 1) 
            return;
        
        int i;
        for(i=length-2; i>=0 && nums[i] >= nums[i+1]; i--);
        
        if(i == -1) // corner case
        {
            Arrays.sort(nums);
            return;
        }
        
        int j = findLargest(nums, nums[i], i+1, length-1);       
        swap(nums, i, j);
        reverse(nums, i+1, length-1);
    }
    
    public int findLargest(int[] nums, int val, int start, int end)
    {
        while(start < end)
        {
            int mid = (start+end)/2;

            if(nums[mid] > val && nums[mid+1] <= val)
                return mid;          
            else if(nums[mid] <= val)
                end = mid-1;
            else
                start = mid+1;
        }
        return end;
    }
    
    public void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int[] nums, int i, int j)
    {
        while(i<j)
        {
            swap(nums, i, j);
            i++;
            j--;
        }
    }   
}