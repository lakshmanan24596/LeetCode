/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

class Solution 
{   
    // DP
    public int numDecodings(String digit) 
    {
        if(digit == null || digit.equals("") || digit.charAt(0) == '0') 
            return 0;
        
        int[] DP = new int[digit.length() + 1];
        DP[0] = DP[1] = 1;
        
        for(int i=1; i<digit.length(); i++)
        {
            int one = (digit.charAt(i) != '0') ? DP[i] : 0;
            int two = (digit.charAt(i-1) == '1' || (digit.charAt(i-1) == '2' && digit.charAt(i)-'0' <= 6)) ? DP[i-1] : 0;
            
            DP[i+1] = one + two;    // fibonacci series (depends on previous two outputs)
        }
        return DP[digit.length()];
    }
    
//  ---------------------------------------------------------------------------------------------------------------------------
    
//     Dont append or create any string.. we just need the count.. so decrement n

//     String digit;
//     public int numDecodings(String s) 
//     {
//         if(s == null || s.equals("") || s.charAt(0) == '0') 
//            return 0;
//         digit = s;
//         return numDecodings(s.length());   
//     }
    
//     public int numDecodings(int n)
//     {
//         if(n <= 1)
//             return 1;
        
//         int output = 0;
//         if(digit.charAt(n-1) != '0')
//             output += numDecodings(n-1);
        
//         if((digit.charAt(n-2) == '1') || (digit.charAt(n-2) == '2' && digit.charAt(n-1)-'0' <= 6))
//             output += numDecodings(n-2);
        
//         return output;
//     }
    
// ----------------------------------------------------------------------------------------------------------------------------
     
//     APPEND the string only if we need the exact strings as ouput.. but here we need only COUNT
   
//     int result = 0;
// 	   String input, one, two;  
     
//     public int numDecodings(String s) 
//     {
//         if(s.equals("")) return 0;
//     	   this.input = s;
//     	   numDecodingsUtil("");
//         return result;
//     }
   
//     public void numDecodingsUtil(String curr)
//     {
//         if(curr.equals(input))
//         {
//             result++;
//             return;
//         }
       
//         int level = curr.length();
       
//         one = curr + input.charAt(level);      
//         if(one.charAt(one.length()-1) != '0')
//         {
//             numDecodingsUtil(one);
//         }    
      
//         if(level < input.length() - 1)
//         {
//             two = curr + input.substring(level, level+2);         

//             if((two.charAt(two.length()-2) - '0' == 1) || 
//                (two.charAt(two.length()-2) - '0' == 2 && two.charAt(two.length()-1) - '0' <=6))
//                 numDecodingsUtil(two);        	
//         }   
//     }
}