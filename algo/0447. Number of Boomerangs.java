/*
You are given n points in the plane that are all distinct, where points[i] = [xi, yi]. 
A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
Return the number of boomerangs.

Example 1:
Input: points = [[0,0],[1,0],[2,0]]
Output: 2
Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].

Example 2:
Input: points = [[1,1],[2,2],[3,3]]
Output: 2

Example 3:
Input: points = [[1,1]]
Output: 0

Constraints:
n == points.length
1 <= n <= 500
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
*/



/*
    logic: hashmap + math
    explanation:
        (j i k) and (k i j) are the two possibilites
        where "i" is the center always
        so for each "i", we need to find the 2 points (j and k) which are at equal distance from "i"
        to find equal distance points, we make use of hashmap to store distanceVsCount
        
        Now for this i, we have to calculate all possible permutations of (j,k) from these equidistant points.
        Total number of permutations of size 2 from n different points is --> nP2 = n!/(n-2)! = n * (n-1)

    time: n^2
    space: n
*/

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int boomerangs = 0;
        int distance;
        Map<Integer, Integer> distFreqMap = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                distance = getDistance(points[i], points[j]);
                distFreqMap.put(distance, distFreqMap.getOrDefault(distance, 0) + 1);
            }
            for (int dist : distFreqMap.values()) {
                boomerangs += dist * (dist - 1);        // permutation: nP2
            }
            distFreqMap.clear();                        // use new hashmap for each "i"
        }
        return boomerangs;
    }
    
    public int getDistance(int[] i, int[] j) {
        int dx = i[0] - j[0];
        int dy = i[1] - j[1];
        return (dx * dx) + (dy * dy);                   // ((x1-x2)^2) + ((y1-y2)^2)
    }
}
