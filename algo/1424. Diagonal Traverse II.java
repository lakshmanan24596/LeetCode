/*
Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

Example 3:
Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
Output: [1,4,2,5,3,8,6,9,7,10,11]

Example 4:
Input: nums = [[1,2,3,4,5,6]]
Output: [1,2,3,4,5,6]

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= nums[i][j] <= 10^9
There at most 10^5 elements in nums.
*/


/*
    1) Hashmap based where key = i + j, because i + j will be the same for all elements in the diagnol
    https://leetcode.com/problems/diagonal-traverse-ii/discuss/597698/JavaC%2B%2B-HashMap-with-Picture-Clean-code-O(N)
    Time: n, Space: n
    
    2) simulation  (time limit exceeded)
    Time: n, Space: 1
    
    3) tree bfs
    bottom and right are the two children of the node
    Time: n, Space: n
*/


/*
// time limit exceeded, because time = maxRow * maxCol, instead of number of elements

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int row = nums.size(), col = 0;
        int i, j;
        int outputSize = 0, outputIndex = 0;
        for (int index = 0; index < row; index++) {
            outputSize += nums.get(index).size();
        }
        int[] output = new int[outputSize];
        
        for (int index = 0; index < row; index++) {
            col = Math.max(col, nums.get(index).size());
            i = index;
            j = 0;
            while (i >= 0) {
                if (j < nums.get(i).size()) {
                    output[outputIndex++] = nums.get(i).get(j);
                }
                i--;
                j++;
            }
        } 
        
        for (int index = 1; index < col; index++) {
            i = row - 1;
            j = index;
            while (i >= 0) {
                if (j < nums.get(i).size()) {
                    output[outputIndex++] = nums.get(i).get(j);
                }
                i--;
                j++;
            }
        }
        return output;
    }
}
*/


// time: n, space: n, logic: tree bfs
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int outputSize = 0, outputIndex = 0;
        for (int index = 0; index < nums.size(); index++) {
            outputSize += nums.get(index).size();
        }
        int[] output = new int[outputSize];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0});
        
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];
            output[outputIndex++] = nums.get(x).get(y);
            
            if (x < nums.size() - 1 && y == 0) {
                queue.add(new int[]{x + 1, y});     // bottom (1st column alone)
            }
            if (y < nums.get(x).size() - 1) {
                queue.add(new int[]{x, y + 1});     // right
            }
        }
        return output;
    }
}