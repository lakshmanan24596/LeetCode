/*
Implement a trie with insert, search, and startsWith methods.

Example:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:
You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
*/

class Trie 
{
    class TrieNode
    {
        TrieNode[] children;
        boolean isLeaf;
        TrieNode()
        {
            this.children = new TrieNode[26];               // because input can be a to z
            this.isLeaf = isLeaf;
        }
    }
    
    TrieNode head;    
    public Trie() 
    {
        head = new TrieNode();
    }
    
    public void insert(String word) 
    {
        int index;
        int len = word.length();
        TrieNode curr = head;
        
        for(int i = 0; i < len; i++)
        {
            index = word.charAt(i) - 'a';
            if(curr.children[index] == null)
                curr.children[index] = new TrieNode();      // allocate memory to children
            curr = curr.children[index];
        }
        curr.isLeaf = true;
    }
    
    public boolean search(String word) 
    {
        int index;
        int len = word.length();
        TrieNode curr = head;
        
        for(int i = 0; i < len; i++)
        {
            index = word.charAt(i) - 'a';
            if(curr.children[index] == null)
                return false;
            curr = curr.children[index];
        }  
        return curr.isLeaf;
    }
    
    public boolean startsWith(String prefix) 
    {
        int index;
        int len = prefix.length();
        TrieNode curr = head;
        
        for(int i = 0; i < len; i++)
        {
            index = prefix.charAt(i) - 'a';
            if(curr.children[index] == null)
                return false;
            curr = curr.children[index];
        }  
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */