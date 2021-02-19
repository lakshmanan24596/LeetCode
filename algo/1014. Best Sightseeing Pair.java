/*
Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.
The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.
Return the maximum score of a pair of sightseeing spots.

Example 1:
Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 
Note:
2 <= A.length <= 50000
1 <= A[i] <= 1000
*/


/*
    1) brute force
        search every possible pairs
        time: n^2, space: 1
    
    2) using i + arr[i] technique
        When we move forward, all sightseeing spot will be 1 distance away.
        if we add index it will incremented by 1, so only i + arr[i] works here
        time: n, space: 1
*/

class Solution {
    public int maxScoreSightseeingPair(int[] arr) {
        int maxIndex = 0;
        int currScore, maxScore = 0;
        
        for  (int i = 1; i < arr.length; i++) {
            currScore = (arr[i] + arr[maxIndex]) -  (i - maxIndex);     // main logic
            maxScore = Math.max(maxScore, currScore);
            if (i + arr[i] > maxIndex + arr[maxIndex]) {                // main logic
                maxIndex = i;
            }
        } 
        return maxScore;
    }
}