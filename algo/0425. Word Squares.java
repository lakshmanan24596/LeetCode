/*
Given an array of unique strings words, return all the word squares you can build from words. 
You can return the answer in any order.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

Example 1:
Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Example 2:
Input: words = ["abat","baba","atan","atal"]
Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 5
All words[i] have the same length.
words[i] consists of only lowercase English letters.
All words[i] are unique.
*/


/*
    logic: BACKTRACKING
    https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
    we can find a symentric property in the output word square
    
    Implementation to find prefix:
        1) hashmap
        2) trie
    
    time: O(r * (26^c))
          where r = no of words
                c = word length
*/

class Solution {
    String[] words;
    int rows, cols;
    Trie trieRoot;
    List<List<String>> output;
    String prefix;
    
    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        trieRoot = new Trie();
        output = new ArrayList<List<String>>();
        rows = words.length;
        cols = words[0].length();
        List<Integer> currList;
        
        for (int i = 0; i < rows; i++) {
            trieRoot.insert(words[i], i);
        }
        for (int i = 0; i < rows; i++) {
            currList = new ArrayList<Integer>();
            currList.add(i);
            dfs(currList);                                  // try to start with all possible words
        }
        return output;
    }
    
    public void dfs(List<Integer> currList) {
        if (currList.size() == cols) {
            List<String> currOutput = new ArrayList<String>();
            for (int currIndex : currList) {
                currOutput.add(words[currIndex]);
            }
            output.add(currOutput);
            return;
        }
        
        StringBuilder prefix = new StringBuilder();
        int size = currList.size();
        for (int currIndex : currList) {
            prefix = prefix.append(words[currIndex].charAt(size));                  // create prefix
        }
        
        List<Integer> matchingPrefixes = trieRoot.prefixSearch(prefix.toString());  // main logic
        for (int matchedPrefix : matchingPrefixes) {
            currList.add(matchedPrefix);
            dfs(currList);
            currList.remove(currList.size() - 1);                                   // backtrack
        }
    }
}

class Trie {
    Trie[] children;
    List<Integer> cachedPrefix;
    
    Trie() {
        children = new Trie[26];
        cachedPrefix = new ArrayList<Integer>();
    }
    
    public void insert(String word, int wordIndex) {
        int index;
        Trie curr = this;
        
        for (Character letter : word.toCharArray()) {
            index = letter - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new Trie();
            }
            curr = curr.children[index];
            curr.cachedPrefix.add(wordIndex);       // useful during prefix search
        }
    }
    
    public List<Integer> prefixSearch(String prefix) {
        int index;
        Trie curr = this;
        
        for (Character letter : prefix.toCharArray()) {
            index = letter - 'a';
            curr = curr.children[index];
            if (curr == null) {
                return new ArrayList<Integer>();
            }
        }
        return curr.cachedPrefix;                   // instead of dfs, we can cache it
        // return dfs(curr);
    }
    
    /*
    public List<Integer> dfs(Trie curr) {
        List<Integer> output = new ArrayList<Integer>();
        if (curr.leafIndex != -1) {
            output.add(curr.leafIndex);
        }
        
        for (int i = 0; i < 26; i++) {
            if (curr.children[i] != null) {
                output.addAll(dfs(curr.children[i]));
            }
        }
        return output;
    }
    */
}