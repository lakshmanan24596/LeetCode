/*
You are given a series of video clips from a sporting event that lasted T seconds.  
These video clips can be overlapping with each other and have varied lengths.
Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  
We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  
If the task is impossible, return -1.

Example 1:
Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
Output: 3
Explanation: 
We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
Then, we can reconstruct the sporting event as follows:
We cut [1,9] into segments [1,2] + [2,8] + [8,9].
Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].

Example 2:
Input: clips = [[0,1],[1,2]], T = 5
Output: -1
Explanation: 
We can't cover [0,5] with only [0,1] and [1,2].

Example 3:
Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
Output: 3
Explanation: 
We can take clips [0,4], [4,7], and [6,9].

Example 4:
Input: clips = [[0,4],[2,8]], T = 5
Output: 2
Explanation: 
Notice you can have extra video after the event ends.

Constraints:
1 <= clips.length <= 100
0 <= clips[i][0] <= clips[i][1] <= 100
0 <= T <= 100
*/


/*
    1) DP memo
        a) sort the clips array based on start time
        b) use DP array
           apply proper condition for nextIndex --> (clips[next][0] <= endTime && clips[next][1] > endTime)
        
        DP state: currIndex   
        Time: n^2
        Space: n
    
    2) greedy + sort
        time: n*logn
        space: 1
        https://leetcode.com/problems/video-stitching/discuss/269984/java-O(N*lgN)-greedy
        
    3) greedy + jumpGame
        time: n
        space: 1
        refer 1326. Minimum Number of Taps to Open to Water a Garden
*/

// DP memo
class Solution {
    int[][] clips;
    int[] DP;
    int n, T;
    
    public int videoStitching(int[][] clips, int T) {
        if(T == 0) {
            return 0;
        }
        this.clips = clips;
        this.T = T;
        this.n = clips.length;
        Arrays.sort(clips, (a, b) -> (a[0] - b[0]));        // sort based on start time
        this.DP = new int[n];
        
        int output = n + 1, currOutput;
        for (int i = 0; i < n && clips[i][0] == 0; i++) {   // do dfs from nodes which has startTime = 0
            currOutput = dfs(i);
            output = Math.min(output, currOutput);
        }
        return output == n + 1 ? -1 : output;
    }
    
    public int dfs(int curr) {
        if (DP[curr] > 0) {
            return DP[curr];
        }
        int endTime = clips[curr][1];
        if (endTime >= T) {     // base case
            return 1;
        }
        
        int output = n + 1, currOutput;
        for (int next = curr + 1; next < n; next++) {
            if (clips[next][0] <= endTime && clips[next][1] > endTime) {    // main logic
                currOutput = 1 + dfs(next);
                output = Math.min(output, currOutput);
            }
        }
        return DP[curr] = output;
    }
}
