/*
(This problem is an interactive problem.)
Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.
You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true If there is at least one ship in the rectangle represented by the two points, including on the boundary.

Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. 
It is guaranteed that there are at most 10 ships in that rectangle.

Submissions making more than 400 calls to hasShips will be judged Wrong Answer. 
Also, any solutions that attempt to circumvent the judge will result in disqualification.

Example :
Input: 
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
Output: 3
Explanation: From [0,0] to [4,4] we can count 3 ships within the range.

Constraints:
On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In other words, you must find the answer using the given hasShips API, without knowing the ships position.
0 <= bottomLeft[0] <= topRight[0] <= 1000
0 <= bottomLeft[1] <= topRight[1] <= 1000
topRight != bottomLeft
*/



/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */


/*
    Divide and conquer:
        we need to check in both sides
        but the main pruning is, if (sea.hasShips()) == false, then no need to check that portion
        
    time: T(n) = 2*T(n/2) ==> O(n)
    but max 10 ships only can be present, so mostly sea.hasShips() will return false which inturn reduced the time complexity.
    
    Also instead of 2 portions, we can also divide the grid into 4 portions
    refer: https://leetcode.com/problems/number-of-ships-in-a-rectangle/discuss/440768/Java-Simple-divide-and-conquer-solution-with-explanation
*/

class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        return countShipsUtil(sea, bottomLeft, topRight);   // params are swapped just to avoid confusion in names
    }
    
    public int countShipsUtil(Sea sea, int[] bottomLeft, int[] topRight) {
        if (!sea.hasShips(topRight, bottomLeft)) {                              // base case
            return 0;
        }
        int x1 = bottomLeft[0];
        int y1 = bottomLeft[1];
        int x2 = topRight[0];
        int y2 = topRight[1];
        int mid, left, right;
 
        if (x1 == x2 && y1 == y2) {                                             // same point (base case)
            return sea.hasShips(topRight, bottomLeft) ? 1 : 0;
        } 
        else if (x1 == x2) {                                                    // same line
            mid = (y1 + y2) / 2;
            left = countShipsUtil(sea, bottomLeft, new int[] {x2, mid}) ;
            right = countShipsUtil(sea, new int[] {x1, mid + 1}, topRight);
            return left + right;
        } 
        else {                                                                  // main case
            mid = (x1 + x2) / 2;
            left = countShipsUtil(sea, bottomLeft, new int[] {mid, y2}) ;
            right = countShipsUtil(sea, new int[] {mid + 1, y1}, topRight);
            return left + right; 
        }
    }
}