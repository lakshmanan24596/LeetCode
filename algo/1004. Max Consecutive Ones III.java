/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1s. 

Example 1:
Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:
Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Note:
1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 
*/

/*
// Sliding window, Time: n, Space: n
class Solution {
    public int longestOnes(int[] A, int K) {
        List<Integer> onePos = new ArrayList<Integer>();
        onePos.add(-1);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                onePos.add(i);
            }
        }
        onePos.add(A.length);
        if(onePos.size() - 2 <= K) {
            return A.length;
        }
        
        int curr, max = 0;
        for(int start = 1, end = start + K - 1; end < onePos.size() - 1; start++, end++) {  // sliding window
            curr = onePos.get(end) - onePos.get(start) + 1;
            curr += onePos.get(start) - onePos.get(start - 1) - 1;  // left
            curr += onePos.get(end + 1) - onePos.get(end) - 1;      // right
            max = Math.max(curr, max);
        }
        return max;
    }
}
*/

// Sliding window, Time: n, Space: 1
class Solution {
    public int longestOnes(int[] A, int K) {
        int curr, max = 0;
        int zeroCount = 0;
        
        for (int start = 0, end = 0; end < A.length; end++) {
            if (A[end] == 0) { 
                zeroCount++;
            }
            while (zeroCount > K) {
                if (A[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
            curr = end - start + 1;
            max = Math.max(curr, max);
        }
        return max;
    }
}