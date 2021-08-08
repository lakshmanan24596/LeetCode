/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".

Note:
All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
*/


/*
    1) brute force: words * s.length()
    2) binary search: https://leetcode.com/problems/number-of-matching-subsequences/discuss/220447/Java-Binary-Search-(Explanation)
    3) trie: https://leetcode.com/problems/number-of-matching-subsequences/discuss/157065/Java-Trie-Solution
    4) queue: https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
    
    Trie can be used only for startsWith, since this ques is about sub-seq, str.indexOf(char, fromIndex) is used
    
    Binary search --> pre-process string -->  input = "abacbca" ---> POS = ['a','b','c'] = [[0,2,6], [1,4], [3,5]]
    Trie, Queue --> pre-process words
*/


/*
// brute force: words * s.length()
class Solution 
{
    public int numMatchingSubseq(String str, String[] words) 
    {
        if(str == null || str.length() == 0) {
            return 0;
        }
        
        int strLength = str.length(), wordLength;
        int output = 0;
        int i, j;
        String word;
        
        for(int index = 0; index < words.length; index++)
        {
            word = words[index];
            wordLength = word.length();
            i = 0;
            j = 0;
            
            while(i < wordLength && j < strLength) {
                if(word.charAt(i) == str.charAt(j)) {
                    i++;
                }
                j++;
            }
            if(i == wordLength) {
                output++;
            } 
        }
        return output;
    }
}
*/


/*
Using queue: Time: O(s.length() + (words * word.length)) which is linear
Iterate each char of string and "process all words together" which are waiting for that particular char
*/
class Solution 
{
    class Node 
    {
        int idx;
        char[] chars;
        Node (String word) 
        {
            this.idx = 0;
            this.chars = word.toCharArray();
        }
    }
    
   public int numMatchingSubseq(String str, String[] words) 
   {
        Queue<Node>[] waiting = new LinkedList[26];
        for (int i =0; i < 26; i++) {
            waiting[i] = new LinkedList<Node>();
        }   
        
        for (String word : words) {
            Node curr = new Node(word);
            waiting[curr.chars[0] - 'a'].add(curr);     // pre-process words --> [0] = {a, acd, ace} and [1] = {bb}
        }
            
        int output = 0;
        char[] strToCharArray = str.toCharArray();
       
        for (char ch : strToCharArray) 
        {
            Queue<Node> queue = waiting[ch - 'a'];
            int size = queue.size();
            
            for (int i = 0; i < size; i++) 
            {
                Node curr = queue.remove();             // remove first
                curr.idx++;
                if (curr.idx == curr.chars.length) {
                    output++;
                }
                else {
                    waiting[curr.chars[curr.idx] - 'a'].add(curr);
                } 
            }
        }
        return output;
    }
}
