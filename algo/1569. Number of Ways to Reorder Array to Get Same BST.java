/*
Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. 
Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.
For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.

Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [2,1,3]
Output: 1
Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.

Example 2:
Input: nums = [3,4,5,1,2]
Output: 5
Explanation: The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]

Example 3:
Input: nums = [1,2,3]
Output: 0
Explanation: There are no other orderings of nums that will yield the same BST.

Example 4:
Input: nums = [3,1,2,5,4,6]
Output: 19

Example 5:
Input: nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
Output: 216212978
Explanation: The number of ways to reorder nums to get the same BST is 3216212999. Taking this number modulo 10^9 + 7 gives 216212978.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= nums.length
All integers in nums are distinct.
*/



/*
    logic: DC (use bst property)
    preOrder or postOrder traversal
    time: n^2
    
    Hint:
    The first number will always be the root. Consider the numbers smaller and larger than the root separately. When merging the results together, how many ways can you order x elements in x+y positions?
    
    Why combination is used?
    https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/discuss/819369/C%2B%2B-Just-using-recursion-very-Clean-and-Easy-to-understand-O(n2)
    
    output = nCr of currNode * recur(lesserNums) * recur(greaterNums)
*/

/*
class Solution {
    int mod = 1_000_000_007;
    
    public int numOfWays(int[] nums) {
        List<Integer> numsList = new ArrayList<Integer>();
        for (int num : nums) {
            numsList.add(num);
        }
        return (int) (numOfWays(numsList) - 1);     // ignore given input, so minus 1
    }
    
    public long numOfWays(List<Integer> nums) {
        if (nums.size() <= 1) {
            return 1;
        }
        List<Integer> lesserNums = new ArrayList<Integer>();
        List<Integer> greaterNums = new ArrayList<Integer>();
        int rootVal = nums.get(0);
        
        for (int num : nums) {
            if (num < rootVal) {
                lesserNums.add(num);
            } else if (num > rootVal) {
                greaterNums.add(num);
            }
        }
        
        int n = nums.size() - 1;      // ignore root, so minus 1
        int r = lesserNums.size();    // or greaterNums.size() because nCr == nCn-r
        return (combination(n, r) * numOfWays(lesserNums) * numOfWays(greaterNums)) % mod;  // main logic
    }
    
    public long combination(int n, int r) {                                                 // calculate nCr
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
                
    public long factorial(int n) {
        long fact = 1;
        while (n > 1) {
            fact *= n;
            n--;
        }
        return fact;
    }
}
*/


// above solution is correct, but the output exceeds long value. so used BigInteger
import java.math.BigInteger;

class Solution {
    int MOD = 1000000007;
    public int numOfWays(int[] nums) {
        if(nums.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        for(int num : nums) list.add(num);
        return (int)(helper(list).longValue() - 1);
    }

    private BigInteger helper(List<Integer> nums) {
        if(nums.size() <= 2) return BigInteger.ONE;
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i=1; i<nums.size(); i++) {
            if(nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        BigInteger count = combination(nums.size() - 1, left.size());
        BigInteger leftCount = helper(left);
        BigInteger rightCount = helper(right);
        return count.multiply(leftCount).multiply(rightCount).mod(BigInteger.valueOf(MOD));
    }

    private BigInteger combination(int total, int num) {
        num = Math.min(num, total - num);
        if(num == 0) return BigInteger.ONE;

        BigInteger res = BigInteger.ONE;
        int limit = num;
        long multi = total;
        long did = 1;
        for(int i=0; i<limit; i++) {
            res = res.multiply(BigInteger.valueOf(multi--));
            res = res.divide(BigInteger.valueOf(did++));
        }
        return res;
    }
}