/*
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

Example 1:
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7

Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 
Note:
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
*/


/*
    1) brute: n^2, 1
    2) hashmap:
        similar to 560. Subarray Sum Equals K
        logic: if k = 5 and we see a number 4, then it can be paired a number 1, 6, 11, etc...
        so modulo arithematic can be used.
        
        Formula for modulo:
            +ve --> num % k
            -ve --> (num % k) + k --> for -ve numbers, we need to convert it to positive modulo value
*/

class Solution 
{
    public int subarraysDivByK(int[] A, int K)  // Time: n, Space: k
    {
        int[] map = new int[K];
        map[0] = 1;
        int sum = 0, output = 0;
        
        for(int num : A)
        {
            sum = (sum + num) % K;      // main logic: modulo arithematic
            if(sum < 0) {
                sum += K;               // main logic: convert negative module value to positive modulo value
            }
            
            output += map[sum];
            map[sum]++;
        }
        return output;
    }
}
