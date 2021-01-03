/*
We have two integer sequences A and B of the same non-zero length.
We are allowed to swap elements A[i] and B[i].  
Note that both elements are in the same index position in their respective sequences.
At the end of some number of swaps, A and B are both strictly increasing.  
(A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
Given A and B, return the minimum number of swaps to make both sequences strictly increasing. 
 It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1

Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.

Note:
A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
*/


/*
    logic:
        this problem is exactly similar to 0/1 knapsack, either we can swap/dontSwap at a particular index
        output value range is from 0 to n
        so time: 2^n, exponential
        
        for converting 0/1 knapsack to DP --> in same level, the sum should be same.
        in this problem, instead of sum we have only 2 values (0 for dont swap and 1 for swap)
        
        Time for 0/1 knapsack in DP = n * sum
        Time for this problem in DP = n * 2
        
    Techniques to solve:
        Recursion, memorization, tabulation --> 2n time, 2n space
        tabulation space optimized          --> 2n time, 2 space (current state depends only on previous state)
*/

class Solution 
{    
    Integer[][] DP;
    int n;
    int[] A, B;
    
    public int minSwap(int[] A, int[] B) 
    {
        this.A = A; 
        this.B = B;
        n = A.length;
        DP = new Integer[A.length][2];            
        return recur(0, 0, -1, -1);
    }
    
    private int recur(int i, int swapped, int prevA, int prevB)     // DP states: i, swapped
    {
        if(i == n) {
            return 0;
        }
    	if(DP[i][swapped] != null) {
            return DP[i][swapped];
        }
        
        int dontSwap = n, swap = n;
        if(A[i] > prevA && B[i] > prevB) {                  // main logic: condition for no-swap
            dontSwap = recur(i + 1, 0, A[i], B[i]);
        }
        if(A[i] > prevB && B[i] > prevA) {                  // main logic: condition for swap
            swap = 1 + recur(i + 1, 1, B[i], A[i]);
        }
        
        int output = Math.min(dontSwap, swap);              // similar to 0/1 knapsack
        return DP[i][swapped] = output;
    }
}
