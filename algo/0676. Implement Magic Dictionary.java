/*
Design a data structure that is initialized with a list of different words. 
Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

Implement the MagicDictionary class:
MagicDictionary() Initializes the object.
void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.

Example 1:
Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation:
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False

Constraints:
1 <= dictionary.length <= 100
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case English letters.
All the strings in dictionary are distinct.
1 <= searchWord.length <= 100
searchWord consists of only lower-case English letters.
buildDict will be called only once before search.
At most 100 calls will be made to search.
*/



/*
    logic:
        1) we can set any letter as "*" and search it in the dict
            ex: hello = *ello, h*llo, etc..
            time: WL
        2) we can form all possible new word 
            ex: hello = aello, bello, etc.. expect hello
            time: 25 * WL
        
    Implementation:
        1) hashmap
        2) trie
    
    Below solution:
        time: build = W * WL * WL, search = WL * WL
        space: W * WL
*/

class MagicDictionary {
    Set<String> wordSet;
    Map<String, Integer> wordsFreqMap;
    final char star = '*';
    
    public MagicDictionary() {
        wordSet = new HashSet<String>();
        wordsFreqMap = new HashMap<String, Integer>();
    }
    
    public void buildDict(String[] dictionary) {
        char[] wordArr;
        char letter;
        String neigh;
        
        for (String word : dictionary) {                        // W
            wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {          // WL
                letter = wordArr[i];
                wordArr[i] = star;
                neigh = new String(wordArr);                    // WL
                wordsFreqMap.put(neigh, wordsFreqMap.getOrDefault(neigh, 0) + 1);
                wordArr[i] = letter;
            }
            wordSet.add(word);
        }
    }
    
    public boolean search(String word) {
        char[] wordArr = word.toCharArray();
        char letter;
        String neigh;
        int count;
        
        for (int i = 0; i < wordArr.length; i++) {              // WL
            letter = wordArr[i];
            wordArr[i] = star;
            neigh = new String(wordArr);                        // WL
            count = wordsFreqMap.getOrDefault(neigh, 0);
            if (count > 1 || (count == 1 && !wordSet.contains(word))) {     // main logic
                return true;
            }
            wordArr[i] = letter;
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */