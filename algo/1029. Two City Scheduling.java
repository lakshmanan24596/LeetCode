/*
A company is planning to interview 2n people. 
Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.

Example 1:
Input: costs = [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.
The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

Example 2:
Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
Output: 1859

Example 3:
Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
Output: 3086
 
Constraints:
2 * n == costs.length
2 <= costs.length <= 100
costs.length is even.
1 <= aCosti, bCosti <= 1000
*/


/*
    recursion + memo
    at each level, either pick A or pick B
*/
/*
class Solution 
{
    int[][] costs;
    Integer[][][] DP;
    int n;
    
    public int twoCitySchedCost(int[][] costs) 
    {
        this.costs = costs;
        n = costs.length;
        DP = new Integer[n][(n / 2 ) + 1][(n / 2) + 1];
        return recur(0, 0, 0);
    }
    
    public int recur(int level, int aCount, int bCount)
    {
        if(level == n) {
            return 0;
        }
        if(DP[level][aCount][bCount] != null) {
            return DP[level][aCount][bCount];
        }
        
        int pickA = Integer.MAX_VALUE, pickB = Integer.MAX_VALUE;
        if(aCount < n / 2) {
            pickA = costs[level][0] + recur(level + 1, aCount + 1, bCount);
        }
        if(bCount < n / 2) {
            pickB = costs[level][1] + recur(level + 1, aCount, bCount + 1);
        }
        return DP[level][aCount][bCount] = Math.min(pickA, pickB);
    }
}
*/


/*
    Same as above solution
    We can see that "level = aCount + bCount" is the above algo
    So we can skip that state in the DP
    DP is reduced from 3D to 2D
    Time: n^2, Space: n^2
    https://leetcode.com/problems/two-city-scheduling/discuss/278731/Java-DP-Easy-to-Understand
*/
/*
class Solution 
{
    int[][] costs;
    Integer[][] DP;
    int n;
    
    public int twoCitySchedCost(int[][] costs) 
    {
        this.costs = costs;
        n = costs.length;
        DP = new Integer[n][n];
        return recur(0, 0);
    }
    
    public int recur(int aCount, int bCount)
    {
        if(aCount + bCount == n) {
            return 0;
        }
        if(DP[aCount][bCount] != null) {
            return DP[aCount][bCount];
        }
        
        int pickA = Integer.MAX_VALUE, pickB = Integer.MAX_VALUE;
        if(aCount < n / 2) {
            pickA = costs[aCount + bCount][0] + recur(aCount + 1, bCount);
        }
        if(bCount < n / 2) {
            pickB = costs[aCount + bCount][1] + recur(aCount, bCount + 1);
        }
        return DP[aCount][bCount] = Math.min(pickA, pickB);
    }
}
*/


/*
    Logic: greedy
    Time: n*logn
    Savings = costA - costB
    "Sort saving descending because we need to save the cost"
    example 2: 
         savings       = -511, 394, 259, 45, 722, 117
         after sorting = 722, 394, 259, 117, 45, -511
         pick B city in first half and pick A city in second half
*/
class Solution
{
    public int twoCitySchedCost(int[][] costs) 
    {
        Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (b[0] - b[1]) - (a[0] - a[1]);   // saving should be higher, so sort the difference in descending order
            }
        });
        
        int output = 0;
        int halfSize = costs.length / 2;
        for(int i = 0; i < halfSize; i++) 
        {
            output += costs[i][1];                  // pick B in 1st half
            output += costs[i + halfSize][0];       // pick A in 2nd half
        }
        return output;
    }
}
