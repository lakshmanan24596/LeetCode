/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".

Example 2:
Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".

Example 3:
Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
*/

class Solution 
{
    public int maxProduct(String[] words) 
    {
         /*
            a = 2^0 = 1 (left shift 0 time)
            b = 2^1 = 2 (left shift 1 time)
            c = 2^2 = 4 (left shift 2 time)
            d = 2^3 = 8 (left shift 3 time)
            
            ex: abc and cde
                abc --> 0000000000000000111
                def --> 0000000000000111000
                
            if(abc & def) == 0 --> then no unique char found between 2 strings. So ans = 3*3 = 9     
         */
        
        int n = words.length;
        if(n < 2) {
            return 0;
        }
        int[] bitValueArr = new int[n];
        
        for(int i = 0; i < n; i++) 
        {
            int bitVal = 0;
            int strLen = words[i].length();
            for(int j = 0; j < strLen; j++)
            {
                bitVal |= 1 << (words[i].charAt(j) - 'a');  // main logic
            }
            bitValueArr[i] = bitVal;
        }
        
        int currOutput, maxOutput = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = i+1; j < n; j++)
            {
                if((bitValueArr[i] & bitValueArr[j]) == 0)
                {
                    currOutput = words[i].length() * words[j].length();
                    maxOutput = Math.max(maxOutput, currOutput);
                }
            }
        }
        return maxOutput;
    }
}