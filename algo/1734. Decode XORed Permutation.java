/*
There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. 
For example, if perm = [1,3,2], then encoded = [2,1].
Given the encoded array, return the original array perm. 
It is guaranteed that the answer exists and is unique.

Example 1:
Input: encoded = [3,1]
Output: [1,2,3]
Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]

Example 2:
Input: encoded = [6,5,4,6]
Output: [2,4,1,5,3]

Constraints:
3 <= n < 105
n is odd.
encoded.length == n - 1
*/


/*
    If we know the first element we can find the remaining all elements
    https://leetcode.com/problems/decode-xored-array/
    Logic:
        Encod[i] = output[i] ^ output[i+1]
        Encod[i] ^ Encod[i] ^ output[i+1] = output[i] ^ output[i+1] ^ Encod[i] ^ output[i+1]
        output[i+1] = output[i] ^ Encod[i]
     
    https://leetcode.com/problems/decode-xored-permutation/discuss/1031840/Explanations-XOR-and-1st-element-Java-Kotlin-Python 
    https://leetcode.com/problems/decode-xored-permutation/discuss/1031107/JavaC%2B%2BPython-Straight-Forward-Solution
    To find the first element, it can will n^2 time in worst case
    How to find first element in O (n) time ?
        (a1^a2) ^ (a1^a3) ^ (a1^a4) ^ (a1^a5) ^ (a1^a2^a3^a4^a5) = a1
*/

class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int first = 0;
        int[] output = new int[n];
        
        for (int i = 0; i <= n; i++) {               // (a1^a2) ^ (a1^a3) ^ (a1^a4) ^ (a1^a5) ^ (a1^a2^a3^a4^a5) = a1
            first ^= i;
            if (i < n && i % 2 == 1) {
                first ^= encoded[i];
            }
        }
        output[0] = first;
        
        for (int i = 0; i < n - 1; i++) {
            output[i + 1] = output[i] ^ encoded[i];  // main logic
        }
        return output;
    }
}