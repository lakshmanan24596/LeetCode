/*
Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, 
return the shortest distance between these two words in the list.
Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.

Example 1:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1

Example 2:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
Output: 3

Constraints:
1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
*/


/*
    keep 2 pointers for word1 and word2
    time: wordsDict * wordLength
    space: 1
*/
class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        Integer wordPointer1 = null;
        Integer wordPointer2 = null;
        String word;
        int shortDist = Integer.MAX_VALUE;
        int currDist;
        boolean isSame = word1.equals(word2);
        
        for (int i = 0; i < wordsDict.length; i++) {
            word = wordsDict[i];
            if (isSame) {
                if (word.equals(word1)) {
                    if (wordPointer1 != null) {
                        shortDist = Math.min(shortDist, i - wordPointer1);
                    }
                    wordPointer1 = i;
                }
            } else {
                if (word.equals(word1)) {
                    wordPointer1 = i;
                    if (wordPointer2 != null) {
                        shortDist = Math.min(shortDist, wordPointer1 - wordPointer2);
                    }
                } else if (word.equals(word2)) {
                    wordPointer2 = i;
                    if (wordPointer1 != null) {
                        shortDist = Math.min(shortDist, wordPointer2 - wordPointer1);
                    }
                }
            }
        }
        return shortDist;
    }                                                           
}