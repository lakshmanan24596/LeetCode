/*
Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example 1:
Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 6
Explanation: Given three friends living at (0,0), (0,4), and (2,2).
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
So return 6.

Example 2:
Input: grid = [[1,1]]
Output: 1

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
There will be at least two friends in the grid.
*/



/*
    1) brute force:
        time: (m * n) * (m * n)
        space: 1
        implementation: any point can be a meeting point
        so try checking in every point
        
    2) sorting:
        best meeting point in 1D array is median (ie; sum / size)
        in 2D array, we have rows and cols and we can find it independently
        after finding we need to sort rowHouses and colHouses list
        time: m * n * log(m*n)
        space: m + n
        
    3) collect co-ordinates in sorted order:
        iterate row-wise and collect rowHouses
        iterate col-wise and collect colHouses
        then find median
        time: m * n
        space: m + n

    ex:1
        rowHouses = 0, 0, 2 --> meeting point = 0
        colHouses = 0, 2, 4 --> meeting point = 2
        distance from meeting point to houses = 6
*/

class Solution {
    List<Integer> rowHouses, colHouses;
    int rows, cols;
    int[][] grid;
    
    public int minTotalDistance(int[][] grid) {
        this.grid = grid;
        this.rowHouses = new ArrayList<Integer>();
        this.colHouses = new ArrayList<Integer>();
        this.rows = grid.length;
        this.cols = grid[0].length;
        
        fillRowHouses();
        fillColHouses();
        return getBestMeetingPoint1D(rowHouses) + getBestMeetingPoint1D(colHouses);
    }
    
    public void fillRowHouses() {
        for (int i = 0; i < rows; i++) {            // iterate row-wise
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    rowHouses.add(i);               // collect row houses
                }
            }
        }
    }
    
    public void fillColHouses() {
        for (int j = 0; j < cols; j++) {            // iterate col-wise
            for (int i = 0; i < rows; i++) {
                if (grid[i][j] == 1) {
                    colHouses.add(j);               // collect col houses
                }
            }
        }
    }
    
    public int getBestMeetingPoint1D(List<Integer> houses) {
        int meetingPoint = houses.get(houses.size() / 2);           // main logic
        int distance = 0;
        
        for (int i = 0; i < houses.size(); i++) {
            distance += Math.abs(meetingPoint -  houses.get(i));    // main logic
        }
        return distance;
    }
}


// calculate the distance without knowing the median using a "two pointer approach"
/*
     public int getBestMeetingPoint1D(List<Integer> houses) {
        int distance = 0;
        int i = 0;
        int j = houses.size() - 1;
        while (i < j) {
            distance += houses.get(j) - houses.get(i);
            i++;
            j--;
        }
        return distance;
    }
*/