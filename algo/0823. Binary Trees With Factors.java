/*
Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
We make a binary tree using these integers, and each number may be used for any number of times. 
Each non-leaf node's value should be equal to the product of the values of its children.
Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.

Example 1:
Input: arr = [2,4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]

Example 2:
Input: arr = [2,4,5,10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

Constraints:
1 <= arr.length <= 1000
2 <= arr[i] <= 109
All the values of arr are unique.
*/


/*
    DP + hashmap
        time: n * n
        space: n
        
        ex     = 2, 3, 5, 9, 10, 30, 90
        output = 1, 1, 1, 2,  3,  7, 27
        we need to sum up all values in DP table and return 42 as output
         
        output for 90:
                 90 as root   = 1
                 + 90, 30, 3  = 7   (7 * 1)
                 + 90, 3, 30  = 7   (1 * 7)
                 + 90, 9, 10  = 6   (2 * 3)
                 + 90, 10, 9  = 6   (3 * 2)
                              = 27  (1 + 7 + 7 + 6 + 6)

        for 90, the valid pairs are:
            (3, 30) and (9, 10)
            to find out this, we use a hashmap (similar to 2 sum problem)
*/

class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        long output = 0;
        long currOutput;
        Arrays.sort(arr);
        Map<Integer, Long> binaryTreeCount = new HashMap<Integer, Long>();
        
        for (int i = 0; i < arr.length; i++) {
            currOutput = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && binaryTreeCount.containsKey(arr[i] / arr[j])) {             // main logic
                    currOutput += binaryTreeCount.get(arr[j]) * binaryTreeCount.get(arr[i] / arr[j]);   // main logic
                }
            }
            binaryTreeCount.put(arr[i], currOutput);
            output += currOutput;
        }
        return (int) (output % 1_000_000_007);
    }
}
