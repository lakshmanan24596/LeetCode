/*
A string s is called good if there are no two different characters in s that have the same frequency.
Given a string s, return the minimum number of characters you need to delete to make s good.
The frequency of a character in a string is the number of times it appears in the string. 
For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

Example 1:
Input: s = "aab"
Output: 0
Explanation: s is already good.

Example 2:
Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".

Example 3:
Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).

Constraints:
1 <= s.length <= 105
s contains only lowercase English letters.
*/


/*
    greedy, sort
        time: n + 26log26 + 26 ==> n
        space: 26    
*/

class Solution {
    public int minDeletions(String s) {
        int[] freq = new int[26];
        int deletions = 0;
        int currDel = 0;
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        Arrays.sort(freq);
        
        for (int i = 25-1; i >= 0; i--) {
            if (freq[i] == 0) {
                break;
            }
            if (freq[i] >= freq[i + 1]) {
                if (freq[i + 1] != 0) {
                    currDel = freq[i] - freq[i + 1] + 1;        // main logic
                } else {
                    currDel = freq[i];                          // corner case: ex: acebbbb
                }
                freq[i] -= currDel;
                deletions += currDel;
            }
        }
        return deletions;
    }
}