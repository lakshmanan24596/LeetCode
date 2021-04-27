/*
Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}


Example:
Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

Notes:
The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
*/


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */



/*
    Question understanding:
        Dont Know:
            1) grid layout (where grid is the room)
            2) curr pos of robot
        Know:
            1) curr dir is top
            2) available 4 api's
            3) grid is surrounded by wall on 4 corners
        Value:
            val = 1 refer dirt and needs to be cleaned
            val = 0 refers blocked
        Problem:
            We need to clean the entire room 
            ie; we need to move to all possible positions and call robot.clean()
     
     Initution:
        In normal DFS problem, we need currPos and a visited array to solve a problem
        How to find current position of the robot? We dont know that.
        Answer:
            Example-1: currPosition = 1, 3 and totalRows = 5, totalCols = 8
            Assume, currPos (1,3) as --> (0,0)
            Assume, Grid layout from (0,0) to (4,7) as --> from (-1, -3) to (3, 4)
            refer solution tab image
    
    Implementation:    
        1) array cannot be used for marking visited, because the assumed grid layout goes to negative values also, so use "hashset"
        2) dir[][] order matter --> each dir should be right to the prev dir because we are calling turnRight() in each iteration
        3) after eaach successful move(), we need to backtrack (ie; goBack)
       
    Logic: DFS + backtracking
    Time: (cell with val = 1) * 4
    space: (cell with val = 1)
*/

class Solution {
    Robot robot;
    int[][] dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};   // top, right, bottom, left (Order matters)
    Set<Position> visited;
    
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        this.visited = new HashSet<Position>();
        cleanRoomUtil(0, 0, 0);
    }
    
    public void cleanRoomUtil(int currX, int currY, int currDir) {                  // states: currPos, currDir
        robot.clean();
        visited.add(new Position(currX, currY));
        int nextX, nextY;
        int dirCount = dir.length;
        
        while (dirCount-- > 0) {
            nextX = currX + dir[currDir][0];
            nextY = currY + dir[currDir][1];
            
            if (!visited.contains(new Position(nextX, nextY)) && robot.move()) {    // main logic
                cleanRoomUtil(nextX, nextY, currDir);
                goBack();   // backtrack: after each successfull move(), we need to come back to its original position
            }
            robot.turnRight();              // to move to next direction, turnRight() is called in each iteraation
            currDir = (currDir + 1) % 4;    // since we have turnedRight, we need to change to currDir val to next right
        }
    }
    
    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
        
        @Override
        public boolean equals(Object posObj) {
            Position pos = (Position) posObj;
            return (this.x == pos.x) && (this.y == pos.y);
        }
    }
}