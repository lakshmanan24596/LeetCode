/*
Given two sparse vectors, compute their dot product.

Implement class SparseVector:
SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6

Constraints:
n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
*/


/*
    space: store only non-zero elements
        1) store in arraylist (with index, value pair)  --> time: (n) + (L1 + L2)
        2) store in hashmap                             --> time: (n) + (min(L1,L2))
*/

class SparseVector {
    List<int[]> indexValue;
    
    SparseVector(int[] nums) {                                                  // time: n
        indexValue = new ArrayList<int[]>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                indexValue.add(new int[] {i, nums[i]});
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {                                   // time: L1 + L2
        int p1 = 0, p2 = 0;
        int dotProduct = 0;
        
        while (p1 < this.indexValue.size() && p2 < vec.indexValue.size()) {     // 2-pointer technique
            if (this.indexValue.get(p1)[0] == vec.indexValue.get(p2)[0]) {
                dotProduct += this.indexValue.get(p1)[1] * vec.indexValue.get(p2)[1];
                p1++;
                p2++;
            } else if (this.indexValue.get(p1)[0] < vec.indexValue.get(p2)[0]) {
                p1++;
            } else {
                p2++;
            }
        }
        return dotProduct;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);