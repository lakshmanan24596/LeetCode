/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

class Solution 
{
    public String minWindow(String text, String patt) 
    {
        // char --> fixed 256 letetrs.. so always use ARRAY[256], instead of hashMap
        
        if(patt.length() > text.length())
            return "";
        
        char[] textInput = text.toCharArray();
        char[] pattInput = patt.toCharArray();
        int textLength = textInput.length;
        int pattLength = pattInput.length;        

        int[] pattMap = new int[256];
        int[] textMap = new int[256];
        int index;    
        int currStart = 0, start = -1;
        int currWindowSize, minWindowSize = Integer.MAX_VALUE;         
        
        // fill pattMap
        for(int i = 0; i < pattLength; i++)
        {
            index = pattInput[i];
            pattMap[index]++;
        }    
       
        int matchedCount = 0;
        for(int i = 0; i < textLength; i++)
        {
            index = textInput[i];
            textMap[index]++;           
            if(textMap[index] <= pattMap[index])
                matchedCount++;
            
            // window found
            if(matchedCount == pattLength) 
            {
                // remove starting characters
                index = textInput[currStart];
                while(textMap[index] > pattMap[index])
                {
                    textMap[index]--;   // remove front
                    currStart++;
                    index = textInput[currStart];
                }
                
                // update minWindowSize and start
                currWindowSize = i - currStart + 1;
                if(currWindowSize <= minWindowSize)
                {
                    minWindowSize = currWindowSize;
                    start = currStart;
                }
            }
        }
        return (start == -1) ? "" : text.substring(start, start + minWindowSize);
    }
}