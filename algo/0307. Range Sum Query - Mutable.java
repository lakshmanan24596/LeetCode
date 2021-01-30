/*
Given an array nums and two types of queries where you should update the value of an index in the array, and retrieve the sum of a range in the array.
Implement the NumArray class:
NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the subarray nums[left, right] (i.e., nums[left] + nums[left + 1], ..., nums[right]).

Example 1:
Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 9 = sum([1,3,5])
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // return 8 = sum([1,2,5])

Constraints:
1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
*/


/*
    Time for constuct, getRangeSum, update:
        1) Brute force   ==> Time: 1, n, 1           and Space: 1
        2) DP prefix sum ==> Time: n, 1, n           and Space: n
        3) Segment tree  ==> Time: n, logn, logn     and Space: n
        4) Fenwick tree  ==> Time: nlogn, logn, logn and Space: n 
    
    Segment tree:
        Implemented using "divide and conquer" technique
        Each node will have left, right, index
        root node: (left, right, index) = (0, n-1, 0)
    
    Implementation: 
    1) array --> https://www.youtube.com/watch?v=0l3xN3BpxHg&feature=emb_title
    2) segment tree node --> https://leetcode.com/problems/range-sum-query-mutable/discuss/75724/17-ms-Java-solution-with-segment-tree
        class SegmentTreeNode {
            int start, end;
            SegmentTreeNode left, right;
            int sum;
            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = null;
                this.right = null;
                this.sum = 0;
            }
        }
*/
class NumArray {                // Segment tree implementation
    int[] tree, nums;
    int n;
    int queryLeft, queryRight;  // used for getRangeSum
    int numsIndex, diffValue;   // used for update
    
    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        int height = 1 + (int)(Math.ceil(Math.log(n) / Math.log(2))); 
        int size = (int) Math.pow(2, height) - 1;           // main logic to find size of segment tree array
        this.tree = new int[size];
        constructTree(0, n - 1, 0);
    }
    
    public int constructTree(int left, int right, int index) {
        if (left == right) {
            return tree[index] = nums[left];
        }
        int mid = getMidIndex(left, right);
        int leftSum = constructTree(left, mid, getLeftIndex(index));
        int rightSum = constructTree(mid + 1, right, getRightIndex(index));
        return tree[index] = leftSum + rightSum;            // post order traversal
    }
    
    public int sumRange(int left, int right) {
        this.queryLeft = left; 
        this.queryRight = right;
        return sumRangeUtil(0, n - 1, 0);
    }
    
    public int sumRangeUtil(int left, int right, int index) {
        if(left > queryRight || right < queryLeft) {        // completely outside
            return 0;
        }
        if(left >= queryLeft && right <= queryRight) {      // completely within
            return tree[index];
        }
        int mid = getMidIndex(left, right);                 // partialy present
        int leftSum = sumRangeUtil(left, mid, getLeftIndex(index));
        int rightSum = sumRangeUtil(mid + 1, right, getRightIndex(index));
        return leftSum + rightSum;
    }
    
    public void update(int index, int val) {
        this.diffValue = val - nums[index];
        this.numsIndex = index;
        nums[index] = val;
        updateUtil(0, n - 1, 0);
    }
    
    public void updateUtil(int left, int right, int index) {
        if(left > numsIndex || right < numsIndex) {         // completely outside
            return;
        }
        tree[index] += diffValue;
        if(left == right) {
            return;
        }
        int mid = getMidIndex(left, right);                 // preOrder traversal
        updateUtil(left, mid, getLeftIndex(index));
        updateUtil(mid + 1, right, getRightIndex(index));
    }
    
    // utils:
    public int getLeftIndex(int index) {
        return (2 * index) + 1;
    }
    
    public int getRightIndex(int index) {
        return (2 * index) + 2;
    }
    
    public int getMidIndex(int left, int right) {
        return (left + right) / 2;
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */