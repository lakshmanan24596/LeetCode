/*
Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
Substrings that occur multiple times are counted the number of times they occur. 

Example 1:
Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:
Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

Constraints:
1 <= s.length <= 105
s[i] is either '0' or '1'.
*/



/*
    logic: group by character and count them
    https://imgur.com/rGdjF0f
    
    example: s = "110001111000000"
             group count = 0, 2, 3, 4, 6 because we can split as 11 000 1111 000000
             output = min(0, 2) + min(2, 3) + min(3, 4) + min(4, 6)
    
    time: n
    space: 1
*/

class Solution {
    public int countBinarySubstrings(String s) {
        int prevGroupCount = 0;
        int currGroupCount = 1;
        int output = 0;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currGroupCount++;
            } else {
                output += Math.min(prevGroupCount, currGroupCount);
                prevGroupCount = currGroupCount;
                currGroupCount = 1;
            }
        }
        output += Math.min(prevGroupCount, currGroupCount);
        return output;
    }
}
