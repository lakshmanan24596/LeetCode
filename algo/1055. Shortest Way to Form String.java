/*
From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. 
If the task is impossible, return -1.

Example 1:
Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:
Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.

Example 3:
Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".

Constraints:
Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
*/


/*
    1) brute force:
        time: O((output * sourceLength) + targetLength)
        space: O(1)
        logic: 2-pointer
               one pointer for source and another pointer for target
               ex-3: output * xyz --> 3 * xyz --> xyzxyzxyz
               ie; when source pointer reaches the end, then reset it to 0 and increment output
               
    2) inverted index + binary search
        time: O(targetLength * log(sourceLength))
        space: O(sourceLength)
        https://leetcode.com/problems/shortest-way-to-form-string/discuss/304662/Python-O(M-%2B-N*logM)-using-inverted-index-%2B-binary-search-(Similar-to-LC-792)
        
    3) inverted index + O(1) search
        time: O(26 * sourceLength) + O(targetLength + sourceLength)
        space: O(26 * sourceLength)
        https://leetcode.com/problems/shortest-way-to-form-string/discuss/330938/Accept-is-not-enough-to-get-a-hire.-Interviewee-4-follow-up
        
    Inverted index storage:
        ex: source = xyzx
            x = 0, 3, 3, 3
            y = 1, 1, -1, -1
            z = 2, 2, 2, -1
*/

class Solution {
    public int shortestWay(String source, String target) {
        int[][] invertedIndex = new int[26][source.length()];
        int shortestWay = 1;
        int sourceIndex, targetIndex
        
        for (int i = 0; i < 26; i++) {
            sourceIndex = -1;
            for (int j = source.length() - 1; j >= 0; j--) {
                if (source.charAt(j) - 'a' == i) {
                    sourceIndex = j;
                }
                invertedIndex[i][j] = sourceIndex;          // main logic: "inverted index" for source string
            }
        }
        
        int j = -1;
        for (int i = 0; i < target.length(); i++) {
            targetIndex = target.charAt(i) - 'a';
            if (j == source.length() - 1) {
                shortestWay++;
                j = -1;
            }
            j = invertedIndex[targetIndex][j + 1];          // main logic: O(1) search
            if (j == -1) {
                shortestWay++;
                j = invertedIndex[targetIndex][0];          // reset j to first occurence
                if (j == -1) {
                    return -1;                              // no occurence of that letter, so return -1
                }
            }
        }
        return shortestWay;
    }
}