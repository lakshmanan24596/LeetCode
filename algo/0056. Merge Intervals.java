/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/

class Solution 
{
    public int[][] merge(int[][] intervals) 
    {
        if (intervals == null || intervals.length < 2) 
            return intervals;
    
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        
        int pos = 0;
        for(int i = 1; i < intervals.length; i++)
        {
            if(intervals[pos][1] >= intervals[i][0])  // [1,3],[2,6] --> 3 > 2  --> so merge as [1,6]
            {    
                intervals[pos][1] = Math.max(intervals[i][1], intervals[pos][1]);   // merge
            }    
            else
            {  
                pos++; 
                intervals[pos][0] = intervals[i][0];
                intervals[pos][1] = intervals[i][1];
            }    
        }
        
        return Arrays.copyOfRange(intervals, 0, pos + 1);
    }
    
    // Better solution (without sorting)
    
    // public int[][] merge(int[][] intervals) {
    //     if(intervals.length < 1) return intervals;
    //     int len = intervals.length;
    //     int count = 0;
    //     for(int i = 0; i < len; i++) {
    //         for(int j = i + 1; j < len; j++) {
    //             int digitA0 = intervals[i][0], digitA1 = intervals[i][1];
    //             int digitB0 = intervals[j][0], digitB1 = intervals[j][1];
    //             if(digitA0 > digitB1 || digitA1 < digitB0) continue;
    //             intervals[j][0] = Math.min(digitA0, digitB0);
    //             intervals[j][1] = Math.max(digitA1, digitB1);
    //             intervals[i][0] = Integer.MAX_VALUE;
    //             count++;
    //             break;
    //         }
    //     }
    //     int[][] ans = new int[len - count][2];
    //     int j = 0;
    //     for(int i = 0; i < len; i++) {
    //         if(intervals[i][0] != Integer.MAX_VALUE) {
    //             ans[j] = intervals[i];
    //             j++;
    //         }
    //     }
    //     return ans;
    // }
}
