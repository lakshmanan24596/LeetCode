/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...

Example 1:
Input: 1
Output: "A"

Example 2:
Input: 28
Output: "AB"

Example 3:
Input: 701
Output: "ZY"
*/

class Solution 
{
    static char[] alphabet = "ZABCDEFGHIJKLMNOPQRSTUVWXY".toCharArray();
        
    public String convertToTitle(int n)
    {        
        StringBuilder output = new StringBuilder();
        char ch;
        
        // logic: n % 26 and n / 26
        while(n > 0)
        {
            ch = alphabet[n % 26];
            output = output.insert(0, ch);
            if(ch == 'Z') 
                n = n-1;
            n /= 26;
        }
        
        return output.toString();
    }
}