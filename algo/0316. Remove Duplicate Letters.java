/*
Given a string s, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.
Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"
 
Constraints:
1 <= s.length <= 104
s consists of lowercase English letters.
*/

/*
    ex: cdbcd and output = bcd
    c --> push c
    d --> push d because c < d. It is in lexi order.
    b --> d < b is not in lexi order. So we need to remove d if there is a future d. (main logic) 
          For checking future d existance, we have countArr for all char
          then c also < b. So remove c.
    c --> push c
    d --> push d
*/

class Solution 
{
    public String removeDuplicateLetters(String s) 
    {
        char[] charArr = s.toCharArray();
        boolean[] visited = new boolean[26];    // true for characters present in the stack
        
        int[] countArr = new int[26];
        for(char ch : charArr) {
            countArr[ch - 'a']++;
        }
        
        Stack<Character> stack = new Stack<Character>();
        for(char curr : charArr)
        {
            countArr[curr-'a']--;
            if(visited[curr-'a']) {	// curr char already present in stack
                continue;
            }
            
            while(!stack.isEmpty() && curr-'a' <= stack.peek()-'a' && countArr[stack.peek()-'a'] >= 1) 	// main logic
            {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(curr);
            visited[curr-'a'] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
