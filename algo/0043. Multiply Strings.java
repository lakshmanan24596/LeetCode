/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Note:
The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

class Solution 
{
    public String multiply(String num1, String num2) 
    {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        
        int len1 = num1.length(), len2 = num2.length();    
        int[] arr = new int[len1 + len2];
        int mul;
        int start = 0;
             
        // main logic
        for(int i = len1 - 1; i >= 0; i--)
        {
            int k = (len1 + len2 - 1) - start;           
            for(int j = len2 - 1; j >= 0; j--)
            {
                mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                arr[k] += mul % 10;
                arr[k-1] += mul / 10;
                k--;
            }          
            start++;
        }
        //end
               
        int carry;
        for(int i = len1 + len2 - 1; i > 0; i--)
        {
        	if(arr[i] > 9)
        	{
        		carry = arr[i] / 10;
        		arr[i] = arr[i] % 10;
        		arr[i-1] += carry;
        	}
        }
                
        StringBuilder output = new StringBuilder();
        boolean started = false;
        for(int i = 0; i < len1 + len2; i++)
        {
            if(arr[i] != 0) // remove front zeros in the array
                started = true;
            
            if(started)
                output = output.append(arr[i]);
        }
        
        return output.toString();
    }
}