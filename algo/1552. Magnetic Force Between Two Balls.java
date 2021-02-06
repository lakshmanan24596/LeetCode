/*
In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. 
Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
Given the integer array position and the integer m. Return the required force.

Example 1:
Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. 
The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

Example 2:
Input: position = [5,4,3,2,1,1000000000], m = 2
Output: 999999999
Explanation: We can use baskets 1 and 1000000000.

Constraints:
n == position.length
2 <= n <= 10^5
1 <= position[i] <= 10^9
All integers in position are distinct.
2 <= m <= position.length
*/


/*
    Logic: binary search on answer
    https://leetcode.com/problems/magnetic-force-between-two-balls/discuss/794176/Java-binary-search
    similar to problem 1482, 1011, etc.
    
    Time: n * log(range) where range = 0 to maxPosition value
    Space: 1
*/
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int left = 0, right = position[n - 1], mid;
        int output = 0;
        
        while (left <= right) {
            mid = ((right - left) / 2) + left;
            if (check(mid, position, m, n)) {
                left = mid + 1;
                output = mid;
            } else {
                right = mid - 1;
            }
        }
        return output;
    }
    
    public boolean check(int ans, int[] position, int m, int n) {
        int prevBall = position[0];     // Always place first object at position[0]
        m--;
        
        for (int i = 1; i < n; i++) {
            if (position[i] - prevBall >= ans) {
                m--;
                if (m == 0) {
                    return true;        // all m balls are places, so return true
                }
                prevBall = position[i];
            }
        }
        return false;
    }
}