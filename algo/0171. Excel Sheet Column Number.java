/*
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...

Example 1:
Input: "A"
Output: 1

Example 2:
Input: "AB"
Output: 28

Example 3:
Input: "ZY"
Output: 701
*/

class Solution 
{
    public int titleToNumber(String s) 
    {
        // logic::: AB --> (1 * (26 pow 1)) + (2 * (26 pow 0)) === 26 + 2 === 28
        
        if(s == null)
            return 0;
        
        int output = 0;
        
        for(int i = 0; i < s.length(); i++)
        {
            output += (s.charAt(i) - 65 + 1) * Math.pow(26, s.length()-i-1);  
        }
        
        return output;
    }
}