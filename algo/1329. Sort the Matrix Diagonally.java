/*
Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

Example 1:
Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
*/

class Solution 
{
    // get it diagnol elements, sort it, put them back
    
    public int[][] diagonalSort(int[][] mat) 
    {
        if(mat == null || mat.length <= 1) {
            return mat;
        }
        int row = mat.length;
        int col = mat[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        int key;
        PriorityQueue<Integer> pQueue;
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                key = i - j;    // logic for diagnol
                
                if(!map.containsKey(key))  
                {
                    pQueue = new PriorityQueue<Integer>();
                    map.put(key, pQueue);
                } 
                else {
                    pQueue = map.get(key);
                }
                pQueue.add(mat[i][j]);
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                key = i - j;    // logic for diagnol
                pQueue = map.get(key);
                mat[i][j] = pQueue.poll();
            }
        }
        return mat;
    }
}
