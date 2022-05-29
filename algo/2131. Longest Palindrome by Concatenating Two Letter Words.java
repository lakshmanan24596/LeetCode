/*
You are given an array of strings words. Each element of words consists of two lowercase English letters.
Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. 
Each element can be selected at most once.

Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
A palindrome is a string that reads the same forward and backward.


Example 1:
Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.

Example 2:
Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.

Example 3:
Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx".

Constraints:
1 <= words.length <= 105
words[i].length == 2
words[i] consists of lowercase English letters.
*/


/*
    Logic: hashmap
        if we get a word "ty" then we need to search "yt" in the given words
        output += 4 in this case
        this case also automatically handles even frequency ("gg", "gg")
    
    corner case: for single frequency "gg" we need output += 2 (only once)
    
    time: n
    space: n
*/

class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        String reverseWord;
        int output = 0;
        
        for (String word: words) {
            reverseWord = word.charAt(1) + "" + word.charAt(0);
            if (wordMap.containsKey(reverseWord)) {    // main logic
                output += 4;
                if (wordMap.get(reverseWord) == 1) {
                    wordMap.remove(reverseWord);
                } else {
                    wordMap.put(reverseWord, wordMap.get(reverseWord) - 1);
                }
            } else {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }  
        }
        
        for (String word : wordMap.keySet()) {
            if (word.charAt(0) == word.charAt(1)) {
                output += 2;
                break;  // because there can be only one "gg" which is placed at center of palindrome
            }
        }
        return output;
    }
}
