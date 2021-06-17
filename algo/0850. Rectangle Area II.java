/*
We are given a list of (axis-aligned) rectangles. 
Each rectangle[i] = [xi1, yi1, xi2, yi2] , where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner of the ith rectangle.
Find the total area covered by all rectangles in the plane. Since the answer may be too large, return it modulo 109 + 7.

Example 1:
Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.

Example 2:
Input: rectangles = [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 1018 modulo (109 + 7), which is (109)2 = (-7)2 = 49. 

Constraints:
1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 109
The total area covered by all rectangles will never exceed 263 - 1 and thus will fit in a 64-bit signed integer.
*/


/*
    1) coordinate compression + brute force
        Time: n^3, Space: n^2
        https://www.quora.com/What-is-coordinate-compression-and-what-is-it-used-for
        https://leetcode.com/problems/rectangle-area-ii/solution/
        logic:
            a) map the coordinates as array index
            b) create a boolean grid where is used track an area is covered or not
                grid for ex1 --> [[true, true, false, false], 
                                  [true, true, true, false], 
                                  [true, false, false, false], 
                                  [false, false, false, false]]
            c) then iterate the grid and if(grid[x][y]) then area += (x2 - x1) * (y2 - y1)   

    2) line sweep technique and segment tree implementation
        yet to learn
*/

// Coordinate Compression, Time: n^3, Space: n^2
class Solution {
    public int rectangleArea(int[][] rectangles) {
        Set<Integer> xSet = new HashSet();
        Set<Integer> ySet = new HashSet();
        
        for (int[] rec: rectangles) {
            xSet.add(rec[0]);
            xSet.add(rec[2]);
            ySet.add(rec[1]);
            ySet.add(rec[3]);
        }                                            
        Integer[] xArr = xSet.toArray(new Integer[0]);      
        Arrays.sort(xArr);
        Integer[] yArr = ySet.toArray(new Integer[0]);
        Arrays.sort(yArr);

        Map<Integer, Integer> xMap = new HashMap();         
        Map<Integer, Integer> yMap = new HashMap();
        for (int i = 0; i < xArr.length; i++) {
            xMap.put(xArr[i], i);
        }
        for (int i = 0; i < yArr.length; i++) {
            yMap.put(yArr[i], i);
        }

        int row = xArr.length;
        int col = yArr.length;
        boolean[][] grid = new boolean[row][col];
        int xStart, xEnd, yStart, yEnd;
        long output = 0;
        
        for (int[] rec: rectangles) {
            xStart = xMap.get(rec[0]);                      // use map to fetch index from given value
            xEnd = xMap.get(rec[2]);
            yStart = yMap.get(rec[1]);
            yEnd = yMap.get(rec[3]);
            
            for (int x = xStart; x < xEnd; x++) {
                for (int y = yStart; y < yEnd; y++) {
                    if (!grid[x][y]) {
                        output += (long) (xArr[x+1] - xArr[x]) * (yArr[y+1] - yArr[y]); // use arr to fetch value from given index
                        grid[x][y] = true;
                    }
                }
            }
        }
        return (int) (output % 1_000_000_007);
    }
}