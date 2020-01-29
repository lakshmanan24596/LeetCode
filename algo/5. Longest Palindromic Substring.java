class Solution 
{
    public String longestPalindrome(String s) 
    {
        if(s.equals("") || s==null || s.length() == 1)
            return s;
        
        int maxLen = 1, 
            start = 0, 
            length = s.length(), 
            i,
            j;
        
        // consider curr as mid and go both sides with i, j
        for(int curr=1; curr<length; curr++)
        {
            // odd length palindrome
            i = curr-1; 
            j = curr+1;
            while(i>=0 && j<length && s.charAt(i)==s.charAt(j))
            {
                int currLen = j-i+1;
                if(currLen > maxLen)
                {
                    maxLen = currLen;
                    start = i;
                }
                i--; 
                j++;
            }
            
            // even length palindrome
            i = curr-1; 
            j = curr;
            while(i>=0 && j<length && s.charAt(i)==s.charAt(j))
            {
                int currLen = j-i+1;
                if(currLen > maxLen)
                {
                    maxLen = currLen;
                    start = i;
                }
                i--;
                j++;
            }
        }
        
        return s.substring(start, start+maxLen);
    }
}