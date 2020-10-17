/*
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/


/*
brute force: Time = n^2 * 26

class Solution 
{    
    public int longestSubstring(String s, int k)
    {
        int output = 0;
        int n = s.length();
        int[] freq;
        
        for(int i = 0; i < n; i++)
        {
            freq = new int[26];
            for(int j = i; j < n; j++)
            {
                freq[s.charAt(j) - 'a']++;
                if(j - i + 1 >= k && isFreqAtLeastK(freq, k)) {
                    output = Math.max(output, j - i + 1);
                }
             }
        }
        return output;
    }
    
    public boolean isFreqAtLeastK(int[] freq, int k)
    {
        for(int i = 0; i < 26; i++) {
            if(freq[i] != 0 && freq[i] < k) {
                return false;
            }
        }
        return true;
    }
}
*/


/*
Divide and conquer: Time = n^2 because the split may not occur in the mid position. It can be skewed type also which is O(n^2)
Example for this case is, s = "abcdefgh" and k = 2.

class Solution 
{    
    String s;
    int k;
    public int longestSubstring(String s, int k)   
    {
        this.s = s;
        this.k = k;
        return recur(0, s.length());
    }
    
    public int recur(int start, int end)
    {
        if(end - start < k) {
            return 0;
        }
        
        int[] freq = new int[26];
        for(int i = start; i < end; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        for(int i = start; i < end; i++)
        {
            if(freq[s.charAt(i) - 'a'] < k)
            {
                // Main logic: curr char cant be in the output substring as its freq < k. So recur left and right.
                int left = recur(start, i);
                int right = recur(i+1, end);
                return Math.max(left, right);
            }
        }
        return end - start;
    }
}
*/


/*
    Divide and conquer: Time = n*logn
    Same as above solution with small optimization
    ex: s = "abcdefgh" and k = 2. This is worst case example which takes n^2 time.
    While recuring right side, we can recur only if right side curr char freq atleast K. Otherwise no need to recur, which inturn reduced the time.
*/
class Solution 
{   
    String s;
    int k;
    public int longestSubstring(String s, int k)   
    {
        this.s = s;
        this.k = k;
        return recur(0, s.length());
    }
    
    public int recur(int start, int end)
    {
        if(end - start < k) {
            return 0;
        }
        
        int[] freq = new int[26];
        for(int i = start; i < end; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        for(int i = start; i < end; i++)
        {
            if(freq[s.charAt(i) - 'a'] < k)
            {
                // Main logic: curr char cant be in the output substring as its freq < k. So recur left and right.
                int left = recur(start, i);
                int j = i+1;
                while(j < end && freq[s.charAt(j) - 'a'] < k) {     // optimization compared to above solution
                    j++;
                }
                int right = recur(j, end);
                return Math.max(left, right);
            }
        }
        return end - start;
    }
}
