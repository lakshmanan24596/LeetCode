/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/

class WordDictionary 
{
    class TrieNode
    {
        TrieNode[] children;
        boolean isLeaf;
        TrieNode()
        {
            this.children = new TrieNode[26];                      // because input can be a to z
            this.isLeaf = isLeaf;
        }
    }
    
    final char dot = '.';
    TrieNode head;
    
    public WordDictionary() 
    {
        head = new TrieNode();
    }
    
    public void addWord(String word) 
    {
        int index;
        int len = word.length();
        TrieNode curr = head;
        
        for(int i = 0; i < len; i++)
        {
            index = word.charAt(i) - 'a';
            if(curr.children[index] == null)
                curr.children[index] = new TrieNode();              // allocate memory to children
            curr = curr.children[index];
        }
        curr.isLeaf = true;
    }
    
    // Time: 1) map  --> word * length
    //       2) trie --> length * 26
    public boolean search(String word) 
    {
        TrieNode curr = head;
        return dfs(word, curr, 0);  
    }
    
    public boolean dfs(String word, TrieNode curr, int startIndex)  // Time: length * 26 in worst case
    {    
        for(int i = startIndex; i < word.length(); i++)
        {
            if(word.charAt(i) != dot)
            {
                int index = word.charAt(i) - 'a';
                if(curr.children[index] == null)
                    return false;
                curr = curr.children[index];
            }
            else    // dot
            {
                for(int j = 0; j < 26; j++)                         // dot can be matched with any char
                {
                    if(curr.children[j] != null)
                    {
                        if(dfs(word, curr.children[j], i + 1))      // i + 1 will be next startIndex
                            return true;
                    }
                }
                return false;                                       // if dot is not matched with any char, then return false
            }
        }  
        return curr.isLeaf;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


// class WordDictionary 
// {    
//     public HashMap<Integer, List<String>> map;      // key: word length, value: all words with that particular length
    
//     public WordDictionary() 
//     {
//         map = new HashMap<Integer, List<String>>();
//     }

//     public void addWord(String word) 
//     {
//         int index = word.length();
//         if (!map.containsKey(index)) 
//         {
//             List<String> list = new ArrayList<>();
//             list.add(word);
//             map.put(index, list);
//         }
//         else 
//         {
//             map.get(index).add(word);
//         }
//     }
    
//    public boolean search(String word)                
//    {
//         int index = word.length();
//         if (!map.containsKey(index))                
//             return false;
        
//         List<String> list = map.get(index);
//         for (String s : list)                        // O(words * length)             
//         {
//             if (isSame(s, word)) 
//                 return true;
//         }
//         return false;
//     }
    
//     private boolean isSame(String s, String word) 
//     {
//         for (int i=0; i<s.length(); i++)
//             if(word.charAt(i) != '.' && s.charAt(i) != word.charAt(i))
//                 return false;

//         return true;
//     }
// }