/*
You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. 
Your task is to make these two strings equal to each other. 
You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].
Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.

Example 1:
Input: s1 = "xx", s2 = "yy"
Output: 1
Explanation: 
Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".

Example 2: 
Input: s1 = "xy", s2 = "yx"
Output: 2
Explanation: 
Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.

Example 3:
Input: s1 = "xx", s2 = "xy"
Output: -1

Example 4:
Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
Output: 4

Constraints:
1 <= s1.length, s2.length <= 1000
s1, s2 only contain 'x' or 'y'.
*/


/*
    Greedy, countArr
    time: n
    space: 1
    
    Initution from given examples:
        ("xx", "yy") => 1 swap
        ("xy", "yx") => 2 swaps
        So apply case 1 as much as possible, then apply case 2
            
    logic:
        for equal char, dont do anything
        s1 = x, s2 = y --> xyCount++
        s2 = y, s2 = x --> yxCount++
        
    For example-4:
        xyCount = 3, yxCount = 3
        output = (xyCount / 2) + (yxCount / 2) + ((xyCount % 2 == 1) && (yxCount % 2 == 1) ? 2 : 0)
               = 1 + 1 + 2
*/

class Solution {
    public int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int n = s1.length();
        int xyCount = 0, yxCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x') {
                    xyCount++;
                } else {
                    yxCount++;
                }
            }
        }
        
        if ((xyCount + yxCount) % 2 != 0) {
            return -1;
        }

        int swaps = (xyCount / 2) + (yxCount / 2);              // main logic: case1 in explanation
        if ((xyCount % 2 == 1) && (yxCount % 2 == 1)) {
            swaps += 2;                                         // main logic: case2 in explanation
        }
        return swaps;
    }
}