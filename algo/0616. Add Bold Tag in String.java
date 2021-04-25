/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. 
If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. 
Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
 
Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Constraints:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
*/



/*
    trie of size 256
    time: (dictWords * dictWordLength) + (sLength * sLength)
    space: size of dictionary + sLength
*/
/*
class Solution {
    Trie root;
    
    public String addBoldTag(String s, String[] dict) {
        if (s == null || s.equals("")) {
            return s;
        }
        this.root = new Trie();
        boolean[] boldTag = new boolean[s.length()];
        int index, start;
        String startTag = "<b>", endTag = "</b>";
        StringBuilder output = new StringBuilder();
        
        for (String word: dict) {                             // dictWords * dictWordLength
            root.insertReverse(word);
        }
        for (int end = 0; end < s.length(); end++) {          // sLength * sLength
            Trie curr = root;
            start = -1;
            
            for (int j = end; j >= 0; j--) {
                index = (int) s.charAt(j);
                if (curr.children[index] == null) {
                    break;
                }
                curr = curr.children[index];
                if (curr.isLeaf) {
                    start = j;
                }
            }
            if (start != -1) {
                for (int j = start; j <= end; j++) {
                    boldTag[j] = true;
                }
            }
        }
        
        for (int i = 0; i < s.length(); i++) {                // sLength               
            if (!boldTag[i]) {
                output.append(s.charAt(i));
            } else {
                int j = i + 1;
                while (j < s.length() && boldTag[j]) {
                    j++;
                }
                output.append(startTag).append(s.substring(i, j)).append(endTag);
                i = j - 1;
            }
        }
        return output.toString();
    }
    
    class Trie {
        Trie[] children;
        boolean isLeaf;
        
        Trie() {
            this.children = new Trie[256];
            this.isLeaf = false;
        }
        
        public void insertReverse(String word) {
            Trie curr = root;
            int index;
            
            for (int i = word.length() - 1; i >= 0; i--) {    // insert in reverse
                index = (int) word.charAt(i);
                if (curr.children[index] == null) {
                    curr.children[index] = new Trie();
                }
                curr = curr.children[index];
            }
            curr.isLeaf = true;
        }
    }
}
*/


/*
    brute
    time: sLength * dictWords * dictWordLength
    space: sLength
    https://leetcode.com/problems/add-bold-tag-in-string/discuss/104248/Java-Solution-boolean-array
*/
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] boldTag = new boolean[s.length()];
        String startTag = "<b>", endTag = "</b>";
        StringBuilder output = new StringBuilder();
        
        for (int i = 0, end = 0; i < s.length(); i++) {       // sLength
            for (String word : dict) {                        // dictWords
                if (s.startsWith(word, i)) {                  // dictWordLength (startWith word, startIndex in s)
                    end = Math.max(end, i + word.length());
                }
            }
            boldTag[i] = end > i;
        }
        
        for (int i = 0; i < s.length(); i++) {                // sLength (same as above solution)          
            if (!boldTag[i]) {
                output.append(s.charAt(i));
            } else {
                int j = i + 1;
                while (j < s.length() && boldTag[j]) {
                    j++;
                }
                output.append(startTag).append(s.substring(i, j)).append(endTag);
                i = j - 1;
            }
        }
        return output.toString();
    }
}