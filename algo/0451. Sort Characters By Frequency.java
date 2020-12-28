/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input: "tree"
Output: "eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:"cccaaa"
Output:"cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:"Aabb"
Output:"bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

class Solution 
{
    public String frequencySort(String s)   // Time: n * 256
    {
        int[] freq = new int[256];          // we can also use hashmap
        for(char ch : s.toCharArray()) {
            freq[(int)ch]++;
        }
        
        int n = s.length();
        char[] output = new char[n];
        int outputIndex = 0, maxIndex, count;
        
        while(outputIndex < n) 
        {
            maxIndex = findMax(freq);       // we can also sort this array
            count = freq[maxIndex];
            while(count-- > 0) {
                output[outputIndex++] = (char) maxIndex;
            }
            freq[maxIndex] = 0;
        }
        return new String(output);
    }
    
    public int findMax(int[] freq)
    {
        int maxIndex = 0, maxValue = freq[0];
        for(int i = 1; i < 256; i++) 
        {
           if(freq[i] > maxValue) {
               maxValue = freq[i];
               maxIndex = i;
           }
        }
        return maxIndex;
    }
}
