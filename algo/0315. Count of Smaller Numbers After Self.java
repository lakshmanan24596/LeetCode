/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example 1:
Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Example 2:
Input: nums = [-1]
Output: [0]

Example 3:
Input: nums = [-1,-1]
Output: [0,0]

Constraints:
1 <= nums.length <= 105
-104 <= nums[i] <= 104
*/


/*
    Time: n*logn
    Similar to inversion count problem
    Solution:
        1) BBST (avl or rbt)
        2) Merge sort
        3) Segment tree or Fenwick tree
*/


/*
    BST
    time: n^2
    space: n
*/
/*
class Solution {
    int count;
    
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] output = new Integer[n];
        BBST root = insert(null, nums[n - 1]);
        output[n-1] = 0;
        
        for (int i = n - 2; i >= 0; i--) {              // iterate from last
            count = 0;
            insert(root, nums[i]);
            output[i] = count;
        }
        return Arrays.asList(output);
    }
    
    public BBST insert(BBST root, int val) {
        if (root == null) {
            return new BBST(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
            root.leftCount++;                           // main logic
        } else {
            root.right = insert(root.right, val);
            if (val == root.val) {
                count += root.leftCount;                // main logic
            } else {
                count += 1 + root.leftCount;            // main logic
            }
        }
        // implement balancing here, and also we need to alter leftCount while altering
        return root;
    }
    
    class BBST {
        int val;
        int leftCount = 0;
        BBST left = null;
        BBST right = null;
        
        BBST(int val) {
            this.val = val;
        }
    }
}
*/


/*
    merge sort (count inversion) (note 4)
    https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
*/
class Solution {
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();     

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
            res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
        if(end <= start){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
        int mid = (start + end) / 2;
        int left_index = start;
        int right_index = mid+1;
        int rightcount = 0;    	
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;
        while(left_index <= mid && right_index <= end){
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index];
                rightcount++;
                right_index++;
            }else{
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightcount;
            left_index++;
            sort_index++;
        }
        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for(int i = start; i <= end; i++){
            indexes[i] = new_indexes[i - start];
        }
    }
}