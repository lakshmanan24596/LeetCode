/*
Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts 
where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. 
Since the answer can be a huge number, return this modulo 10^9 + 7.

Example 1:
Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.

Example 2:
Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.

Example 3:
Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 
Constraints:
2 <= h, w <= 10^9
1 <= horizontalCuts.length < min(h, 10^5)
1 <= verticalCuts.length < min(w, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
It is guaranteed that all elements in horizontalCuts are distinct.
It is guaranteed that all elements in verticalCuts are distinct.
*/


/*
    1) n^2 --> for each point check every other point
       In ex-2: output = (1,1) and (3,4) 
                        = (3 - 1) * (4 - 1) 
                        = 2 * 3 
    2) n* logn --> sorting
       sort horiCut and vertCut array
       distance between 2 gaps gives the output
       output = maxHoriDist * maxVertDist
*/
class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {    // time: hc*log(hc) + vc*log(vc)
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long maxHoriDist = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        long maxVertDist = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxHoriDist = Math.max(maxHoriDist, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            maxVertDist = Math.max(maxVertDist, verticalCuts[i] - verticalCuts[i-1]);
        }
        return (int)((maxHoriDist * maxVertDist) % 1000000007); // main logic
    }
}