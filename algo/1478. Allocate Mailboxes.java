/*
Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.
Return the minimum total distance between each house and its nearest mailbox.
The answer is guaranteed to fit in a 32-bit signed integer.

Example 1:
Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 

Example 2:
Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.

Example 3:
Input: houses = [7,4,6,1], k = 1
Output: 8

Example 4:
Input: houses = [3,6,14,10], k = 4
Output: 0

Constraints:
n == houses.length
1 <= n <= 100
1 <= houses[i] <= 10^4
1 <= k <= n
Array houses contain unique integers.
*/


/*
    if k = 1, the minimum distance is obtained allocating the mailbox in the median of the array houses.
    
    consider example-1:
        n = 5, k = 3
        main logic --> we need to split n into k pieces
            peice 1 --> house 1, 2  --> output 0 + 3
            piece 2 --> house 3, 4  --> output 0 + 2
            piece 3 --> house 5     --> output 0
                                    --> totalOutput = 5
        
        in each piece, we place one mailbox (which is k = 1)
        if there are many houses in a single piece, then we place the mailbox in the median house
        
        
    DP memo
    DP states: startIndex, remainingK --> (which is n, k)
    time: n * k * n
    space: n * k
*/

class Solution {
    int[] houses;
    int n, k;
    Integer[][] DP;
    
    public int minDistance(int[] houses, int k) {
        this.houses = houses;
        this.k = k;
        n = houses.length;
        if (k >= n) {
            return 0;
        }
        Arrays.sort(houses);
        this.DP = new Integer[n][k+1];
        return dfs(0, k);
    }
    
    public int dfs(int start, int remainingK) {                             // main logic: we need to split n into k pieces
        if (remainingK == 0) {
            return (start == n) ? 0 : Integer.MAX_VALUE;
        }
        if (DP[start][remainingK] != null) {
            return DP[start][remainingK];
        }
        int output = Integer.MAX_VALUE, currOutput;
        
        for (int i = start; i <= n - remainingK; i++) {
            currOutput = dfs(i + 1, remainingK - 1);
            
            if (currOutput != Integer.MAX_VALUE) {
                int median = (start + i) / 2;                               // place mailbox in median position
                for (int j = start; j <= i; j++) {
                    currOutput += Math.abs(houses[j] - houses[median]);     // calculate distance
                }
                output = Math.min(output, currOutput);
            }
        }
        return DP[start][remainingK] = output;
    }
}