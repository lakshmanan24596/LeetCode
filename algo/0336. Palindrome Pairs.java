/*
Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

Example 1:
Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]

Example 3:
Input: words = ["a",""]
Output: [[0,1],[1,0]]

Constraints:
1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lower-case English letters.
*/



/*
   let W  = no fo words
       WL = length of the word,
       
   1) brute: 
        time: W * W * WL
        space: 1
    
    2) trie and reverse iteration
        https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
        similar to https://leetcode.com/problems/stream-of-characters/
        time: W * WL * WL
        space: min(26 * 26, W * WL)
*/

class Solution {
    Trie root;
    List<List<Integer>> palinPairs;
    String[] words;
    
    public List<List<Integer>> palindromePairs(String[] words) {
        root = new Trie();
        palinPairs = new ArrayList<List<Integer>>();
        this.words = words;
        
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            search(words[i], i);
        }
        return palinPairs;
    }
    
    public void insert(String word, int index) {
        Trie curr = root;
        int letterIndex;
        
        for (int i = 0; i < word.length(); i++) {
            letterIndex = word.charAt(i) - 'a';
            if (curr.children[letterIndex] == null) {
                curr.children[letterIndex] = new Trie();
            }
            curr = curr.children[letterIndex];
            
            if (i != word.length() - 1 && isPalindrome(word, i + 1, word.length() - 1)) {
                curr.indexList.add(index);
            }
        }
        curr.index = index;
    }
    
    public void search(String word, int i) {
        if (word.equals("")) {                                  // corner case
            for (int j = 0; j < words.length; j++) {
                if (i != j && isPalindrome(words[j], 0, words[j].length() - 1)) {
                    palinPairs.add(Arrays.asList(i, j));
                    palinPairs.add(Arrays.asList(j, i));
                }
            }
            return;
        }
        Trie curr = root;
        int letterIndex;
        
        for (int j = word.length() - 1; j >= 0; j--) {          // main logic: iterate from last character
            letterIndex = word.charAt(j) - 'a';
            curr = curr.children[letterIndex];
            if (curr == null) {
                return;
            }
            if (curr.index != null && curr.index != i && isPalindrome(word, 0, j - 1)) {     // main logic
                palinPairs.add(Arrays.asList(curr.index, i));
            }
        }
        for (int index : curr.indexList) {
            if (index != i) {
                palinPairs.add(Arrays.asList(index, i));
            }
        }
    }
    
    public boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
    
    class Trie {
        Trie[] children;
        Integer index;                  // index will be != null for isLeaf nodes
        List<Integer> indexList;
        
        Trie() {
            children = new Trie[26];
            index = null;
            indexList = new ArrayList<Integer>();
        }
    }
}