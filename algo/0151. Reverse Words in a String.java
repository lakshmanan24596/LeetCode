/*
Given an input string, reverse the string word by word.

Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Note:
A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Follow up:
For C programmers, try to solve it in-place in O(1) extra space.
*/

class Solution 
{  
// not possible to do it in O(1) space in java.. so iterate from last and add in stringBuilder
// no need to reverse in this case
    
    public String reverseWords(String s)
    {        
        StringBuilder sb = new StringBuilder();
        int end = -1;
               
        for(int i = s.length() - 1; i >= 0; i--)
        {
            if(s.charAt(i) == ' ')
            {
                if(end != -1)
                {
                    sb = sb.append(s.substring(i+1, end+1)).append(" ");    // main logic
                    end = -1;
                }
            }
            else
            {
                if(end == -1)
                    end = i;
            }   
        }
        
        if(end != -1) 
            sb = sb.append(s.substring(0, end+1)).append(" ");              // first word in input
        
        if(sb.length() != 0)
            sb.deleteCharAt(sb.length() - 1) ;                              // remove last space, which is added unnecessarily
        
        return sb.toString();
    }
    
    
// reverse individual strings and then finally reverse the full string    
// time = 2n and space = 1.. but in java it is n space
    
//     public String reverseWords(String s) 
//     {
//         s = s.trim().replaceAll(" +", " ");      // trim and then replace 2 or more spaces to 1 space
        
//         int start = -1;
//         for(int i = 0; i < s.length(); i++)
//         {
//             if(s.charAt(i) == ' ')
//             {
//                  if(start != -1)
//                  {
//                      s = reverse(s, start, i-1); 
//                      start = -1;
//                  }
//             }
//             else
//             {
//                  if(start == -1)
//                      start = i;                  // reset start for next batch
//             }    
//         }
//         s = reverse(s, start, s.length()-1);     // reverse last batch
           
//         return reverse(s, 0, s.length()-1);      // reverse full string
//     }
    
//     public String reverse(String s, int start, int end)
//     {
//         StringBuilder sb = new StringBuilder(s);
       
//         for(int i = start, j = end; i < j; i++, j--)
//         {
//             char temp = sb.charAt(i);            // swap char at i and j positions
//             sb.setCharAt(i, sb.charAt(j));
//             sb.setCharAt(j, temp);
//         }        
//         return sb.toString();
//     }
}