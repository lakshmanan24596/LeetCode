/*
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. 
The difficulty of a day is the maximum difficulty of a job done in that day.
Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

Example 1:
Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7

Example 2:
Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.

Example 3:
Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.

Example 4:
Input: jobDifficulty = [7,1,7,1,7,1], d = 3
Output: 15

Example 5:
Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
Output: 843

Constraints:
1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10
*/


/*
    DP memo
    DP states: startIndex, d
    time: O(n * d * n) 
    space: O(n * d)
*/

class Solution {
    int[] jobDifficulty;
    int n;
    Integer[][] DP;
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        this.n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        this.jobDifficulty = jobDifficulty;
        this.DP = new Integer[n][d+1]; 
        
        int output = dfs(0, d);
        return (output == Integer.MAX_VALUE) ? -1 : output;
    }
    
    public int dfs(int start, int d) {
        if (d == 0) {
            return (start == n) ? 0 : Integer.MAX_VALUE;
        }
        if (DP[start][d] != null) {
            return DP[start][d];
        }
        int output = Integer.MAX_VALUE, currOutput;
        int max = Integer.MIN_VALUE;
        
        for (int i = start; i <= n - d; i++) {
            max = Math.max(max, jobDifficulty[i]);
            currOutput = dfs(i + 1, d - 1);
            if (currOutput != Integer.MAX_VALUE) {
                currOutput += max;
                output = Math.min(output, currOutput);
            }
        }
        return DP[start][d] = output;
    }
}


/*
    monotonic stack
    time: O(n * d)
    space: n
    https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/JavaC%2B%2BPython3-DP-O(nd)-Solution
*/
/*
class Solution {
    public int minDifficulty(int[] A, int D) {
        int n = A.length;
        if (n < D) return -1;
        int[] dp = new int[n], dp2 = new int[n], tmp;
        Arrays.fill(dp, 1000);
        Deque<Integer> stack = new ArrayDeque<Integer>();

        for (int d = 0; d < D; ++d) {
            stack.clear();
            for (int i = d; i < n; i++) {
                dp2[i] = i > 0 ? dp[i - 1] + A[i] : A[i];
                while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                    int j = stack.pop();
                    dp2[i] = Math.min(dp2[i], dp2[j] - A[j] + A[i]);
                }
                if (!stack.isEmpty()) {
                    dp2[i] = Math.min(dp2[i], dp2[stack.peek()]);
                }
                stack.push(i);
            }
            tmp = dp;
            dp = dp2;
            dp2 = tmp;
        }
        return dp[n - 1];
    }
} 
*/