/*
In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. 
For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. 
If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
Return the sentence after the replacement.

Example 1:
Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Example 2:
Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"

Example 3:
Input: dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
Output: "a a a a a a a a bbb baba a"

Example 4:
Input: dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Example 5:
Input: dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
Output: "it is ab that this solution is ac"

Constraints:
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 10^6
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Each two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
*/


/*
    This ques is about finding startsWith in the dictionary which is the basic use case of trie data structure
    Time:  size of dictionary + length of the sentence = n
    Space: summation of all word length in dictionary + length of new sentence = n
*/

class Solution {
    TrieNode root;
    String sentence;
    
    public String replaceWords(List<String> dictionary, String sentence) {
        this.sentence = sentence;
        root = new TrieNode();
        int start = 0, end;
        StringBuilder output = new StringBuilder();
        
        for (String dictWord : dictionary) {
            root.insert(dictWord);
        }
        for (end = 0; end < sentence.length(); end++) {
            if (sentence.charAt(end) == ' ') {
                output.append(root.startsWith(start, end));
                output.append(' ');
                start = end + 1;
            }
        }
        output.append(root.startsWith(start, end));
        return output.toString();
    }

    class TrieNode {
        TrieNode[] children;
        boolean isLeaf = false;
        
        TrieNode() {
            children = new TrieNode[26];
        }
        
        public void insert(String word) {
            int index;
            TrieNode curr = root;
            int len = word.length();
            
            for (int i = 0; i < len; i++) {
                index = word.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isLeaf = true;
        }
        
        public String startsWith(int start, int end) {
            int index;
            TrieNode curr = root;
            
            for (int i = start; i < end; i++) {
                index = sentence.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    return sentence.substring(start, end);
                }
                curr = curr.children[index];
                if (curr.isLeaf) {
                    return sentence.substring(start, i + 1);     // main logic: startsWith got matched
                }
            }
            return sentence.substring(start, end);
        }
    }
}