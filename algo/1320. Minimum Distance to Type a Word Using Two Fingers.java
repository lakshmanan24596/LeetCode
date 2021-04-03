/*
You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, 
or example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).

Given the string word, return the minimum total distance to type such string using only two fingers. 
The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|. 
Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.

Example 1:
Input: word = "CAKE"
Output: 3
Explanation: 
Using two fingers, one optimal way to type "CAKE" is: 
Finger 1 on letter 'C' -> cost = 0 
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
Finger 2 on letter 'K' -> cost = 0 
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
Total distance = 3

Example 2:
Input: word = "HAPPY"
Output: 6
Explanation: 
Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6

Example 3:
Input: word = "NEW"
Output: 3

Example 4:
Input: word = "YEAR"
Output: 7

Constraints:
2 <= word.length <= 300
Each word[i] is an English uppercase letter.
*/



/*
    DP memo:
        states: index, prev char typed in finger1, prev char typed in finger2
        time: n * 27 * 27
        space: n * 27 * 27

        we can convert it to tabulation space optimized
        state "index" depends only on previous state
        so space = 27 * 27
*/

class Solution {
    String word;
    Integer[][][] minDistCache;
    
    public int minimumDistance(String word) {
        this.word = word;
        this.minDistCache = new Integer[word.length()][27][27];
        return minimumDistanceUtil(0, -1, -1);
    }
    
    public int minimumDistanceUtil(int index, int prev1, int prev2) {
        if (index == word.length()) {
            return 0;
        }
        if (minDistCache[index][prev1 + 1][prev2 + 1] != null) {
            return minDistCache[index][prev1 + 1][prev2 + 1];
        }
        int curr = word.charAt(index) - 'A';
        
        int dist1 = getDistance(prev1, curr) + minimumDistanceUtil(index + 1, curr, prev2);
        int dist2 = getDistance(prev2, curr) + minimumDistanceUtil(index + 1, prev1, curr);
        return minDistCache[index][prev1 + 1][prev2 + 1] = Math.min(dist1, dist2);
    }
    
    public int getDistance(int from, int to) {
        if (from == -1) {
            return 0;
        }
        // Math.abs((x1 - x2) + (y1 - y2)) and 6 rows, 6 cols present in total in input
        return Math.abs((from / 6) - (to / 6)) + Math.abs((from % 6) - (to % 6));
    }
}