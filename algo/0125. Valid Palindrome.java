/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:
Input: "race a car"
Output: false
*/

class Solution 
{
    public boolean isPalindrome(String s) 
    {
        if(s == null)
            return false;
        
        int left, right;
        
        for(int i = 0, j = s.length() - 1; i < j; i++, j--)
        {
            while(i < j && !isAlphaNumeric(s.charAt(i)))
                i++;
            
            while(i < j && !isAlphaNumeric(s.charAt(j)))
                j--;
            
            // convert caps to small letter.. caps: 65 to 90 and small: 97 to 122.. so minus 32 to convert it   
            left = (s.charAt(i) >= 97) ? s.charAt(i) - 32 : s.charAt(i);    
            right = (s.charAt(j) >= 97) ? s.charAt(j) - 32 : s.charAt(j);                    
            
            if(left != right)
                return false;
        }        
        
        return true;
    }
    
    public boolean isAlphaNumeric(char c)
    {
        return  (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }
}