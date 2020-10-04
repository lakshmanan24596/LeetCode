/*
Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/

class Solution
{
    public String decodeString(String input)
    {
        StringBuilder output = new StringBuilder();
        int len = input.length();
        
        for(int i = 0; i < len; i++)
        {
            char ch = input.charAt(i);
            if(Character.isDigit(ch))
            {
                int count = Character.getNumericValue(ch);
                while(Character.isDigit(input.charAt(i+1))) 
                {
                    count = (count * 10) + Character.getNumericValue(input.charAt(++i)); // ex: 32 = (3 * 10) + 2;
                }
                
                int braceCount = 0, endIndex = -1;
                for(int j = i+1; j < len; j++)  // to find endIndex of ']' close brace
                {
                    if(input.charAt(j) == '[') 
                    {
                        braceCount++;
                    }
                    else if(input.charAt(j) == ']') 
                    {
                        braceCount--;
                        if(braceCount == 0) 
                        {
                            endIndex = j;
                            break;
                        }
                    }
                }  
                
                String subOutput = decodeString(input.substring(i+2, endIndex)); // recursion of substring
                for(int j = 0; j < count; j++) 
                {
                    output = output.append(subOutput);
                }
                i = endIndex; // substring got processed 
            }
            else if(Character.isLetter(ch))
            {
                output = output.append(ch);
            }
        }
        return output.toString();
    }
}
