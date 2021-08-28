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
        
        for(int i = 0; i < pattLength; i++)                 // fill pattMap
        {
            index = pattInput[i];
            pattMap[index]++;
        }    
       
        int matchedCount = 0;
        for(int i = 0; i < textLength; i++)
        {
            index = textInput[i];
            textMap[index]++;           
            if(textMap[index] <= pattMap[index])            // main logic
                matchedCount++;
            
            if(matchedCount == pattLength)                  // window found
            {
                index = textInput[currStart];
                while(textMap[index] > pattMap[index])      // main logic
                {
                    textMap[index]--;                       // remove starting characters
                    currStart++;
                    index = textInput[currStart];
                }
                
                currWindowSize = i - currStart + 1;
                if(currWindowSize <= minWindowSize)         // update minWindowSize and start
                {
                    minWindowSize = currWindowSize;
                    start = currStart;
                }
            }
        }
        return (start == -1) ? "" : text.substring(start, start + minWindowSize);
    }
}