/*
A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:
sx, sy, tx, ty will all be integers in the range [1, 10^9].
*/



/*
    1) brute force recursion
    2) add DP memo to above solution with states x, y
    3) math: convert addition to modulo
        a) https://leetcode.com/problems/reaching-points/discuss/230588/Easy-to-understand-diagram-and-recursive-solution
        b) https://leetcode.com/problems/reaching-points/discuss/155811/c%2B%2B-easy-to-explain-and-impl-in-2mins.-interviewer-loves-it-save-ur-time-for-another-hard
*/


/*
class Solution {
    int tx, ty;
    
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        this.tx = tx;
        this.ty = ty;
        return reachingPointsUtil(sx, sy);
    }
    
    public boolean reachingPointsUtil(int x, int y) {
        if (x > tx || y > ty) {
            return false;
        }
        if (x == tx && y == ty) {
            return true;
        }
        // todo: add memo with states x, y
        return reachingPointsUtil(x, x + y) || reachingPointsUtil(x + y, y);
    }
}
*/

// https://leetcode.com/problems/reaching-points/discuss/230588/Easy-to-understand-diagram-and-recursive-solution
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (tx < ty) {
            return reachingPointsUtil(sx, sy, tx, ty);
        } else {
            return reachingPointsUtil(sy, sx, ty, tx);
        }
    }
    
    public boolean reachingPointsUtil(int sx, int sy, int tx, int ty) {     // logic: math
        if (sx > tx) {
            return false;
        }
        if (sx == tx) {
            return ((ty - sy) % sx) == 0;
        }
        return reachingPointsUtil(sy, sx, ty % tx, tx);
    }
}
