/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Example:
Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]
Output: 2

Note:
The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
*/


/*
    All we need is a brick whose length ends at the same point across maximum rows.
    For ex-1: 4 rows ends of same point 4.
    So output = total rows - no of rows which ends at same point
              = 6 - 4
              = 2
    Logic: Use a hashmap to store end point of all bricks with the value as its freq. 
    Finally we can fetch the maxFreq from the map
*/
class Solution 
{
    public int leastBricks(List<List<Integer>> wall)    // Time: n, Space: n
    {
        int row = wall.size();
        int col;
        List<Integer> currWall;
        int currBrick;
        int sum;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();    // (key, value) =  (sum, count)
        
        for(int i = 0; i < row; i++)
        {
            currWall = wall.get(i);
            col = currWall.size();
            sum = 0;
            
            for(int j = 0; j < col-1; j++)       // no need to consider last brick because we cant draw line after last brick
            {
                currBrick = currWall.get(j);
                sum += currBrick;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        int maxEndPointFreq = 0;
        for(int currEndPointFreq : map.values()) {
            maxEndPointFreq = Math.max(maxEndPointFreq, currEndPointFreq);
        }
        return row - maxEndPointFreq;
    }
}
