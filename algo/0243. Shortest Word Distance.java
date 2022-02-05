/*
Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, 
return the shortest distance between these two words in the list.

Example 1:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3

Example 2:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1
 

Constraints:
1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
*/


class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int shortDist = Integer.MAX_VALUE;
        String word;
        
        for (int i = 0; i < wordsDict.length; i++) {
            word = wordsDict[i];
            if (word.equals(word1)) {
                pos1 = i;
                if (pos2 != -1) {
                    shortDist = Math.min(shortDist, pos1 - pos2);
                }
            } else if (word.equals(word2)) {
                pos2 = i;
                if (pos1 != -1) {
                    shortDist = Math.min(shortDist, pos2 - pos1);
                }
            }
        }
        return shortDist;
    }
}