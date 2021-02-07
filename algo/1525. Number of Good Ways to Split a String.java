/*
You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
Return the number of good splits you can make in s.

Example 1:
Input: s = "aacaba"
Output: 2

Explanation: There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.

Example 2:
Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").

Example 3:
Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.

Example 4:
Input: s = "acbadbaada"
Output: 2

Constraints:
s contains only lowercase English letters.
1 <= s.length <= 10^5
*/


/*
    at each index we need leftSide unique count and rightSide unique count
    so we pre-process both side initially and then check leftCount[i] == rightCount[i+1]
    time: 3n, space: 2n
*/
class Solution {
    public int numSplits(String s) {
        int n = s.length();
        int[] leftCount = new int[n];
        int[] rightCount = new int[n];
        boolean[] visited = new boolean[26];
        int index, currCount = 0;
        int output = 0;
        
        for (int i = 0; i < n; i++) {
            index = s.charAt(i) - 'a';
            if (!visited[index]) {
                visited[index] = true;
                currCount++;
            }
            leftCount[i] = currCount;
        }
        
        visited = new boolean[26];
        currCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            index = s.charAt(i) - 'a';
            if (!visited[index]) {
                visited[index] = true;
                currCount++;
            }
            rightCount[i] = currCount;
        }
        
        for (int i = 0; i < n - 1; i++) {               // i is the split index
            if (leftCount[i] == rightCount[i+1]) {      // main logic
                output++;
            }
        }
        return output;
    }
}