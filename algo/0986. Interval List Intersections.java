/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b. 
 The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  
 For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 
Note:
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
*/

class Solution 
{
    public int[][] intervalIntersection(int[][] A, int[][] B)   // Time: n + m
    {
        List<int[]> output = new ArrayList<int[]>();
        int aLength = A.length, bLength = B.length;
        int i = 0, j = 0;
        int start, end;
        
        while(i < aLength && j < bLength)           // logic: 2 pointer technique
        {
            start = Math.max(A[i][0], B[j][0]);     // main logic
            end = Math.min(A[i][1], B[j][1]);       // main logic
            
            if(start <= end) {
                output.add(new int[] {start, end});    
            }
            
           if(B[j][1] > A[i][1]) {
               i++;
           } else {
               j++;
           }
        }
        
        return output.toArray(new int[output.size()][]);
    }
}
