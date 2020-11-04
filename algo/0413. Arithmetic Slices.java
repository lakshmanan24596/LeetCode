/*
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, these are arithmetic sequences:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.
1, 1, 2, 5, 7
 
A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
A slice (P, Q) of the array A is called arithmetic if the sequence:
A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
*/

/*
class Solution 
{
    public int numberOfArithmeticSlices(int[] A) 
    {
        int n = A.length;
        if(n < 3) {
            return 0;
        }
        
        int[] DP = new int[n];
        int prevDiff = A[1] - A[0];
        int currDiff;
        int count = 0;
        
        for(int i = 2; i < n; i++)
        {
            currDiff = A[i] - A[i-1];
            if(currDiff == prevDiff)    // main logic
            {
                count++;
                DP[i] = DP[i-1] + count;
            }
            else {
                prevDiff = currDiff;    // reset prevDiff and count
                count = 0;
                DP[i] = DP[i-1];
            }
        }        
        return DP[n-1];
    }
}
*/


/*
    brute force: O(n^2), O(1) 
    DP: O(n), O(n) 
    DP: O(n), O(1)
*/
class Solution 
{
    public int numberOfArithmeticSlices(int[] A) 
    {
        /*  In above solution we fetch only DP[i-1]. No other old values are required.
            so instead of DP, we can store DP[i-1] alone in a varaible */
        
        int n = A.length;
        if(n < 3) {
            return 0;
        }
        
        int output = 0;
        int prevDiff = A[1] - A[0];
        int currDiff;
        int count = 0;
        
        for(int i = 2; i < n; i++)
        {
            currDiff = A[i] - A[i-1];
            if(currDiff == prevDiff)    // main logic
            {
                count++;
                output += count;
            }
            else {
                prevDiff = currDiff;    // reset prevDiff and count
                count = 0;
            }
        }        
        return output;
    }
}
