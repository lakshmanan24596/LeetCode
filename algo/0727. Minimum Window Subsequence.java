/*
Given strings s1 and s2, find the minimum (contiguous) substring part of s1, so that s2 is a subsequence of part.

If there is no such window in s1 that covers all characters in s2, return the empty string "". 
If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:
Input: 
s1 = "abcdebdde", s2 = "bde"
Output: "bcde"

Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of s2 in the window must occur in order.

Note:
All the strings in the input will only contain lowercase letters.
The length of s1 will be in the range [1, 20000].
The length of s2 will be in the range [1, 100].
*/



/*
    1) sliding window
        time: (s1 * s1) + s2 in worst case, because we reset i, j each time when there is a match
        space: 1
    
    2) DP
        time: s1 * s2
        space: s1 * s2
        
        clean and optimal solution:
        https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
        
        if equal then fetch from i-1, j-1
        else fetch from i, j - 1
*/

// sliding window
class Solution {
    int startIndex = -1, minLength = Integer.MAX_VALUE;
    char[] arr1, arr2;
    
    public String minWindow(String s1, String s2) {
        if (s2.length() > s1.length()) {
            return "";
        }
        this.arr1 = s1.toCharArray();
        this.arr2 = s2.toCharArray();
        
        for (int i = 0, j = 0; i < arr1.length; i++) {      // check feasibility from left to right of s2
            if (arr1[i] == arr2[j]) {
                j++;
                if (j == arr2.length) {
                    i = findBestWindow(i);                  // reset i, j
                    j = 0;
                }
            }
        }
        return (startIndex == -1) ? "" : s1.substring(startIndex, startIndex + minLength);
    }
    
    public int findBestWindow(int end) {
        int i = end;
        int j = arr2.length - 1;
        
        while (j >= 0) {                                   // check optimization from right to left of s2
            if (arr1[i] == arr2[j]) {
                j--;
            }
            i--;
        }
        i++;
        
        if (end - i + 1 < this.minLength) {
            this.minLength = end - i + 1;
            this.startIndex = i;
        }
        return i;
    }
}

/*
    DP solution
    clean and optimal solution
    refer: https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
*/
