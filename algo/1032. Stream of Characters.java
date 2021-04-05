/*
Implement the StreamChecker class as follows:
StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.

Example:
StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist

Note:
1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
*/



/*
    logic: trie
    https://leetcode.com/problems/stream-of-characters/discuss/278769/Java-Trie-Solution
    similar to https://leetcode.com/problems/palindrome-pairs/
    
    time:
        StreamChecker: words * wordLength
        query: queries.size()
    space:
        min(words * wordLength, 26 * 26)
*/

class StreamChecker {
    Trie root;
    LinkedList<Character> queries;
    int longestWordLength;
    
    public StreamChecker(String[] words) {
        this.root = new Trie();
        this.queries = new LinkedList<Character>();
        int longestLength = 0;
        
        for (int i = 0; i < words.length; i++) {
            insertIntoTrie(words[i]);
            longestWordLength = Math.max(longestWordLength, words[i].length());
        }
    }
    
    public boolean query(char letter) {
        queries.addLast(letter);
        if (queries.size() > longestWordLength) {
            queries.removeFirst();                            // space optimization (remove unwanted queries)
        }
        int letterIndex;
        Trie curr = root;
        
        for (int i = queries.size() - 1; i >= 0; i--) {       // main logic: check from last query
            letterIndex = queries.get(i) - 'a';
            if (curr.children[letterIndex] == null) {
                return false;
            }
            curr = curr.children[letterIndex];
            if (curr.isLeaf) {
                return true;
            }
        }
        return false;
    }
    
    public void insertIntoTrie(String word) {
        int letterIndex;
        Trie curr = root;

        for (int i = word.length() - 1; i >= 0; i--) {        // main logic: insert from last letter
            letterIndex = word.charAt(i) - 'a';
            if (curr.children[letterIndex] == null) {
                curr.children[letterIndex] = new Trie();
            }
            curr = curr.children[letterIndex];
        }
        curr.isLeaf = true;
    }

    class Trie {
        Trie[] children;
        boolean isLeaf;
        
        Trie() {
            this.children = new Trie[26];
            isLeaf = false;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */