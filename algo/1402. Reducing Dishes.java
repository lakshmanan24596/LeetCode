/*
A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]
Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.
Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

Example 1:
Input: satisfaction = [-1,-8,0,5,-9]
Output: 14
Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.

Example 2:
Input: satisfaction = [4,3,2]
Output: 20
Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)

Example 3:
Input: satisfaction = [-1,-4,-5]
Output: 0
Explanation: People don't like the dishes. No dish is prepared.

Example 4:
Input: satisfaction = [-2,5,-1,0,3,-3]
Output: 35

Constraints:
n == satisfaction.length
1 <= n <= 500
-10^3 <= satisfaction[i] <= 10^3
*/



/*
    logic: sort the array
    we need to process the max satisfaction dish in the max time    --> greedy
    because, only then --> time[i] * satisfaction[i] will increase
    
    ex1: after sorting: -9, -8, -1, 0, 5
                output: -3, 10, 14, 10, 5   (process from the last)
    
    maxOutput = 14
    we need to process last 3 dishes (-1, 0, 5) and ignore the first two (-9, -8)
    
    total time = sorting + processing
        sorting = n*logn
        processing = n^2 in brute force and n in DP
*/



/*
// time: n*logn, space: n
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int[] prefixSum = new int[n];
        prefixSum[n-1] = satisfaction[n-1];
        int[] DP = new int[n];
        DP[n-1] = satisfaction[n-1];
        int maxSat = Math.max(0, satisfaction[n-1]);
        
        for (int i = n-2; i >= 0; i--) {
            prefixSum[i] = prefixSum[i+1] + satisfaction[i];
        }
        for (int i = n-2; i >= 0; i--) {
            DP[i] = DP[i+1] + prefixSum[i];         // main logic
            if (DP[i] < maxSat) {
                break;
            }
            maxSat = DP[i];
        }
        return maxSat;
    }
}
*/


/*
    logic: same as above
    both prefixSum and DP arr fetch the data only from the prev state, so we can optimize the space
    time: n*logn, space: 1
*/
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int currSat = 0, maxSat = 0;
        int prefixSum = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            prefixSum += satisfaction[i];
            currSat += prefixSum;                   // main logic (same as above code)
            if (currSat < maxSat) {
                break;
            }
            maxSat = currSat;
        }
        return maxSat;
    }
}