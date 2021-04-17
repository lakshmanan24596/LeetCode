/*
Given a m * n matrix seats  that represent seats distributions in a classroom. 
If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. 
Return the maximum number of students that can take the exam together without any cheating being possible..
Students must be placed in seats in good condition.

Example 1:
Input: seats = [["#",".","#","#",".","#"],
                [".","#","#","#","#","."],
                ["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam. 

Example 2:
Input: seats = [[".","#"],
                ["#","#"],
                ["#","."],
                ["#","#"],
                [".","#"]]
Output: 3
Explanation: Place all students in available seats. 

Example 3:
Input: seats = [["#",".",".",".","#"],
                [".","#",".","#","."],
                [".",".","#",".","."],
                [".","#",".","#","."],
                ["#",".",".",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.


Constraints:
seats contains only characters '.' and'#'.
m == seats.length
n == seats[i].length
1 <= m <= 8
1 <= n <= 8
*/



/*
    DP memo:
        time: O((rows * 2^cols) * 2^cols)
        space: O(rows * 2^cols)
        
    DP states:
        currRowIndex, prevRowMask --> which is rows * 2^cols
        for each state we need to form all possible combination which is 2^cols
        so total time = O((rows * 2^cols) * 2^cols) 
        
    https://leetcode.com/problems/maximum-students-taking-exam/discuss/503491/JAVA-Top-Down-DP-Solution
*/

class Solution {
    int rows, cols;
    char[][] seats;
    Integer[][] memo;
    
    int currRow; 
    int prevRowMask;
    List<Integer> maskList;
    
    public int maxStudents(char[][] seats) {
        this.seats = seats;
        this.rows = seats.length;
        this.cols = seats[0].length;
        this.memo = new Integer[rows][1 << cols];                       // rows * 2^cols
        return findMaxStudents(0, 0);
    }
    
    public int findMaxStudents(int currRow, int prevRowMask) {          // ((rows * 2^cols) * 2^cols)
        if (currRow >= rows) {
            return 0;
        }
        if (memo[currRow][prevRowMask] != null) {
            return memo[currRow][prevRowMask];
        }
        
        this.maskList = new ArrayList<Integer>();
        this.currRow = currRow;
        this.prevRowMask = prevRowMask;
        getMaskList(0, 0);
        
        int maxStudents = 0;
        int currStudents;
        for (int currRowMask : maskList) {
            currStudents = Integer.bitCount(currRowMask) + findMaxStudents(currRow + 1, currRowMask);   // main logic
            maxStudents = Math.max(maxStudents, currStudents);
        }
        return memo[currRow][prevRowMask] = maxStudents;
    }
    
    // find all possible combination for currRow - (similar to 0/1 knapsack)
    public void getMaskList(int currCol, int currRowMask) {             // 2^cols
        if (currCol >= cols) {
            maskList.add(currRowMask);
            return;
        }
        
        // dont pick
        getMaskList(currCol + 1, currRowMask);
            
        // pick
        if ((seats[currRow][currCol] == '.')
                && (currCol == 0 || ((currRowMask & (1 << (currCol - 1))) == 0))                  // left
                && (currCol == 0 || ((prevRowMask & (1 << (currCol - 1))) == 0))                  // prevRow left
                && (currCol == cols - 1 || ((prevRowMask & (1 << (currCol + 1))) == 0))) {        // prevRow right
            int nextMask = currRowMask | (1 << currCol);
            getMaskList(currCol + 1, nextMask);
        }
    }
}