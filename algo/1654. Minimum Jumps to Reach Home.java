/*
A certain bug's home is on the x-axis at position x. 
Help them get there from position 0.

The bug jumps according to the following rules:
It can jump exactly a positions forward (to the right).
It can jump exactly b positions backward (to the left).
It cannot jump backward twice in a row.
It cannot jump to any forbidden positions.
The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. 
If there is no possible sequence of jumps that lands the bug on position x, return -1.

Example 1:
Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
Output: 3
Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.

Example 2:
Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
Output: -1

Example 3:
Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
Output: 2
Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.

Constraints:
1 <= forbidden.length <= 1000
1 <= a, b, forbidden[i] <= 2000
0 <= x <= 2000
All the elements in forbidden are distinct.
Position x is not forbidden.
*/


/*
    BFS + visited array
        Time: endRange * 2
        Space: endRange * 2
        where endRange = max(x, maxForbidden) + a + b

    DFS + DP
        same time and space
        states of DP : currIndex and prevDirection
        same logic, but we should always choose BFS for finding "min"
*/
class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] {0, 0});
        int[] currArr;
        int curr, isPrevStateBack, queueSize, level = 0;
        
        int maxForbidden = 0;
        for (int forb : forbidden) {
            maxForbidden = Math.max(maxForbidden, forb);
        }
        int endRange = Math.max(x, maxForbidden) + a + b;       // formula for endRange
        boolean[][] visited = new boolean[endRange + 1][2];
        for (int forb : forbidden) {
            visited[forb][0] = true;
            visited[forb][1] = true;                            // put forbidden position in visited array
        }
        
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            while (queueSize-- > 0) {
                currArr = queue.remove();
                curr = currArr[0];
                isPrevStateBack = currArr[1];
                if (curr == x) {
                    return level;
                }
                if (curr + a <= endRange && !visited[curr + a][0]) {                    // forward
                    visited[curr + a][0] = true;
                    queue.add(new int[] {curr + a, 0});
                }
                if (isPrevStateBack != 1 && curr - b >= 0 && !visited[curr - b][1]) {   // backward
                    visited[curr - b][1] = true;
                    queue.add(new int[] {curr - b, 1});
                }
            }
            level++;
        }
        return -1;
    }
}


/*
// DFS + memorization

class Solution {
    Set<Integer> forbiddenSet;
    int a, b, x;
    final int MAX = Integer.MAX_VALUE;
    Integer[][] DP;
    
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        this.a = a;
        this.b = b;
        this.x = x;
        forbiddenSet = new HashSet<Integer>();
        for (int forb : forbidden) {
            forbiddenSet.add(forb);
        }
        DP = new Integer[x + b + 1][2];             // where endRange = x + b, which is actually wrong
        int output = dfs(0, 0);
        return (output == MAX) ? -1 : output;
    }
    
    public int dfs(int curr, int isPrevStateBack) {
        if(curr == x) {
            return 0;
        }
        if(DP[curr][isPrevStateBack] != null) {     // states of DP : currIndex and prevDirection
            return DP[curr][isPrevStateBack];
        }
        int forward = MAX;
        int backward = MAX;
        int currOutput;
        
        if (curr + a <= x + b && !forbiddenSet.contains(curr + a)) {
            currOutput = dfs(curr + a, 0);
            forward = (currOutput == MAX) ? MAX : 1 + currOutput;
        }
        if (isPrevStateBack != 1 && curr - b >= 0 && !forbiddenSet.contains(curr - b)) {
            currOutput = dfs(curr - b, 1);
            backward = (currOutput == MAX) ? MAX : 1 + currOutput;
        }
        return DP[curr][isPrevStateBack] = Math.min(forward, backward);
    }
}
*/