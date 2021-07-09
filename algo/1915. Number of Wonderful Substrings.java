/*
A wonderful string is a string where at most one letter appears an odd number of times.
For example, "ccjjc" and "abab" are wonderful, but "ab" is not.

Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. 
If the same substring appears multiple times in word, then count each occurrence separately.
A substring is a contiguous sequence of characters in a string. 

Example 1:
Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"

Example 2:
Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"

Example 3:
Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"

Constraints:
1 <= word.length <= 105
word consists of lowercase English letters from 'a' to 'j'.
*/


/*
    explanation: https://leetcode.com/problems/number-of-wonderful-substrings/discuss/1299773/Intuitive-explanation-easy-to-understand
    image: https://leetcode.com/problems/number-of-wonderful-substrings/discuss/1299525/Count-bitmasks-with-picture
    
    main logic:
        if a char occurs even number of times, then unset a bit
        if a char accurs odd number of time, then set a bit
    implementation logic: 
        to flip a bit and change it in mask, use xor (^)
        mask ^= 1 << i
*/

// time: n*10, space: 2*10, logic: prefix + bitmask
class Solution {
    public long wonderfulSubstrings(String word) {
        long output = 0;
        long[] count = new long[1024];                          // 2 power 10
        count[0] = 1;
        int mask = 0;
        
        for (int i = 0; i < word.length(); i++) {               // refer explanation link
            mask ^= 1 << (word.charAt(i) - 'a');
            output += count[mask];                              // main logic: (even) (atmost zero)
            
            for (int j = 0; j < 10; j++) {
                output += count[mask ^ (1 << j)];               // main logic: (odd) (atmost one) (flip each bit and check)
            }
            count[mask]++; 
        }
        return output;
    }
}