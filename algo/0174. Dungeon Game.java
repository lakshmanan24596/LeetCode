/*
The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. 
The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. 
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; 
other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).

To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
Return the knight's minimum initial health so that he can rescue the princess.
Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

Example 1:
Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.

Example 2:
Input: dungeon = [[0]]
Output: 1

Constraints:
m == dungeon.length
n == dungeon[i].length
1 <= m, n <= 200
-1000 <= dungeon[i][j] <= 1000
*/



/*
https://leetcode.com/problems/dungeon-game/discuss/52784/Who-can-explain-why-u201cfrom-the-bottom-right-corner-to-left-top.u201d

states:
    1) min initial HP
    2) max HP left
    
If we start from top-left, in addition to minimize "initial HP" required to get (i,j), we also have to maximize "HP left" when we get (i,j) in order to decide whether we need more initial HP in the next step. It doesn't directly depend on things at (i-1,j) and (i,j-1).
For example, at some point we have two paths, from left or from up.
    Left: min HP required is 1, max HP left is 1
    Up: min HP required is 5, max HP left is 100
How do we choose now? If we choose smaller min HP required, this requirement may increase to 5 later anyway and 95 HP is wasted.

For many other problems such as 'Minimum Path Sum', both formulation (top down formulation and bottom up formulation) would work.
But for this problem, only bottom up formulation will work.

Main logic:
    DP + POST ORDER (Do it from reverse)
    Start from princess (m-1, n-1) and end at initial position (0, 0)
    
We can also convert all numbers as positive and then apply normal DP.

DP for example 1: solving bottom right to top left (Post Order)
    7 5 2
    6 11 5
    1 1 6
*/

class Solution {
    int[][] dungeon;
    int m, n;
    Integer[][] cacheHP;
    
    public int calculateMinimumHP(int[][] dungeon) {
        this.m = dungeon.length;
        this.n = dungeon[0].length;
        this.dungeon = dungeon;
        this.cacheHP = new Integer[m][n];
        return calculateMinimumHPUtil(0, 0);
    }
    
    public int calculateMinimumHPUtil(int row, int col) {
        if (row == m || col == n) {
            return Integer.MAX_VALUE;
        }
        if (row == m - 1 && col == n - 1) {
            return dungeon[row][col] <= 0 ? (1 - dungeon[row][col]) : 1;
        }
        if (cacheHP[row][col] != null) {
            return cacheHP[row][col];
        }
        
        int right = calculateMinimumHPUtil(row, col + 1);                       // main logic: Post Order traversal
        int down = calculateMinimumHPUtil(row + 1, col);
        
        int minHPneeded = Math.min(right, down) - dungeon[row][col];            // main logic: negate dungeon[row][col]
        return cacheHP[row][col] = (minHPneeded <= 0) ? 1 : minHPneeded;
    }
}