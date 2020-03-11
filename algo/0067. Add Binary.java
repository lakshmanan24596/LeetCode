/*
Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"
*/

class Solution 
{
    public String addBinary(String a, String b) 
    {
        if(a == null || a.equals(""))
            return b;
        if(b == null || b.equals(""))
            return a;
        
        int len1 = a.length();
        int len2 = b.length();
        
        int carry = 0, val1, val2, sum, currOutput;
        StringBuilder output = new StringBuilder();
        
        for(int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0; i--, j--)
        {
            val1 = (i >= 0) ? a.charAt(i) - '0' : 0;
            val2 = (j >= 0) ? b.charAt(j) - '0' : 0;
            
            sum = val1 + val2 + carry;  // sum can be 0,1,2,3 
            carry = (sum > 1) ? 1 : 0;
            
            currOutput = ((sum & 1) == 0) ? 0 : 1;  // sum 0,2 ==> currOutput = 0     and     sum 1,3 ==> currOutput = 1
            output = output.insert(0, currOutput);  // insert front in stringBuilder
        }
        
        if(carry == 1)
            output = output.insert(0, carry);
        return output.toString();
    }
}