/*
You are given m arrays, where each array is sorted in ascending order.
You can pick up two integers from two different arrays (each array picks one) and calculate the distance. 
We define the distance between two integers a and b to be their absolute difference |a - b|.
Return the maximum distance.

Example 1:
Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Example 2:
Input: arrays = [[1],[1]]
Output: 0

Example 3:
Input: arrays = [[1],[2]]
Output: 1

Example 4:
Input: arrays = [[1,4],[0,5]]
Output: 4

Constraints:
m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
*/


/*
    time: n, 
    space: 1
    logic:
        track: firstMin, secondMin, firstMax, secondMax
        if (firstMin != firstMax) then output is firstMin - firstMax
        else outpus is max(firstMin - secondMax, secondMin - firstMax)
*/
/*
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int n = arrays.size();
        int firstMin = 0, secondMin = n;
        int firstMax = 0, secondMax = n;
        int val;
        
        for (int i = 1; i < n; i++) {                   // track: firstMin, secondMin, firstMax, secondMax
            val = arrays.get(i).get(0);
            if (val < arrays.get(firstMin).get(0)) {
                secondMin = firstMin;
                firstMin = i;
            } else if (secondMin == n || val < arrays.get(secondMin).get(0)) {
                secondMin = i;
            }
            
            val = arrays.get(i).get(arrays.get(i).size() - 1);
            if (val > arrays.get(firstMax).get(arrays.get(firstMax).size() - 1)) {
                secondMax = firstMax;
                firstMax = i;
            } else if (secondMax == n || val > arrays.get(secondMax).get(arrays.get(secondMax).size() - 1)) {
                secondMax = i;
            }
        }
        
        // main logic
        if (firstMin != firstMax) {
            return Math.abs(arrays.get(firstMin).get(0) - arrays.get(firstMax).get(arrays.get(firstMax).size() - 1));
        } else {
            return Math.max(
                Math.abs(arrays.get(firstMin).get(0) - arrays.get(secondMax).get(arrays.get(secondMax).size() - 1)),
                Math.abs(arrays.get(secondMin).get(0) - arrays.get(firstMax).get(arrays.get(firstMax).size() - 1))
            );
        }
    }
}
*/


/*
    time: n,
    space: 1
    track min, max alone
*/
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int n = arrays.size();
        if (n <= 1) {
            return 0;
        }
        
        List<Integer> array = arrays.get(0);
        int min = array.get(0);
        int max = array.get(array.size() - 1);
        int maxDist = 0, currDist;
        
        for (int i = 1; i < n; i++) {
            array = arrays.get(i);
            currDist = Math.max(max - array.get(0), array.get(array.size() - 1) - min);     // main logic
            maxDist = Math.max(maxDist, currDist);
            
            min = Math.min(min, array.get(0));
            max = Math.max(max, array.get(array.size() - 1));
        }
        return maxDist;
    }
}