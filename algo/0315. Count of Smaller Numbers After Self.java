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
    1) Brute force:
        time: n^2
        space: 1
    
    2) Time: n*logn
        Similar to inversion count problem - https://www.geeksforgeeks.org/counting-inversions/
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
    Merge sort
    time: n*logn
    space: n
    https://www.geeksforgeeks.org/counting-inversions/
    
    Implementation:
        if right side is strictly smaller, then increment the count
        else use the count for left side index
*/
class Solution {
    int[] nums;
    int[] output, indexArr;
    
    public List<Integer> countSmaller(int[] nums) {
        this.nums = nums;
        this.output = new int[nums.length];
        this.indexArr = new int[nums.length];
        List<Integer> outputList = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            indexArr[i] = i;
        }
        mergeSort(0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            outputList.add(output[i]);
        }
        return outputList;
    }
    
    public void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + ((right - left) / 2);
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            mergeTwoSortedList(left, mid, right);
        }
    }
    
    public void mergeTwoSortedList(int l, int m, int r) {
        int[] left = Arrays.copyOfRange(indexArr, l, m + 1);
        int[] right = Arrays.copyOfRange(indexArr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        int count = 0;
        
        while (i < left.length && j < right.length) {
            if (nums[right[j]] < nums[left[i]]) {       // right side should be strictly smaller
                count++;                                // main logic
                indexArr[k++] = right[j++];
            } else {
                output[left[i]] += count;               // main logic
                indexArr[k++] = left[i++];
            }
        }
        while (i < left.length) {
            output[left[i]] += count;
            indexArr[k++] = left[i++];
        }
        while (j < right.length) {
            indexArr[k++] = right[j++];
        }
    }
}