/*
Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
Examples:

s = "leetcode"
return 0.

s = "loveleetcode"
return 2.
 
Note: You may assume the string contains only lowercase English letters.
*/

class Solution 
{
    public int firstUniqChar(String s) 
    {
        /* logic: for each char we need count and startIndex
        time: O(s.length + 26) */
        
        int[] count = new int[26];
        int[] start = new int[26];
        int currIndex;
        
        for(int i = 0; i < s.length(); i++)
        {
            currIndex = s.charAt(i) - 'a';
            if(count[currIndex] == 0) {
                start[currIndex] = i;
            }
            count[currIndex] += 1;
        }
        
        int output = Integer.MAX_VALUE;
        for(int i = 0; i < 26; i++)
        {
            if(count[i] == 1){
                output = Math.min(output, start[i]);
            }
        }
        return output == Integer.MAX_VALUE ? -1 : output;
    }
}

/*
    1ms solution
    time: O(s.length * 26)
    logic: 
        s.indexOf(ch) gives first index and s.lastIndexOf(ch) gives last index
        if first and last index are same then its occurence is 1 and update output

class Solution {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0)
            return -1;
        
        int ans = Integer.MAX_VALUE;
        
        for(char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if(index != -1 && index == s.lastIndexOf(ch)){
                ans = Math.min(ans, index);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
*/