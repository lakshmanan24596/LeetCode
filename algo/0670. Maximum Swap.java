/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. 
Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

Note:
The given number is in the range [0, 108]
*/

// logic: greedy, time: n, space: 1 (additional n space to convert num to arr)
class Solution 
{
    public int maximumSwap(int num) 
    {
        char[] arr = Integer.toString(num).toCharArray();
        int length = arr.length;
        int maxIndex = length-1, output1 = length-1, output2 = length-1;
        
        for(int i = length - 2; i >= 0; i--)    // main logic: iterate in reverse
        {  
            if(arr[i] - '0' == arr[maxIndex] - '0') {
                continue;
            } 
            else if(arr[i] - '0' > arr[maxIndex] - '0') {
                maxIndex = i;
            } 
            else {
                output1 = maxIndex;
                output2 = i;
            }
        }
        
        char temp = arr[output1];           // output1, output2 are the 2 index which need to be swaped
        arr[output1] = arr[output2];
        arr[output2] = temp;
        return Integer.valueOf(new String(arr));
    }
}
