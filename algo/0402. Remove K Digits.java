/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/

class Solution 
{
    /*
        example: 436, k=1 --> output = 36  --> because 4 > 3 so remove 4
                 So if currElement > nextElement, then remove it. (Most significant digit > least sigificant digit case)
                 This is the main logic
        consider,
                 236, k=1 --? output = 23  --> it is in increasing order. so remove from last
                 
        example: 1432219, k=3 --> output = 1219
                 (4>3 remove 4), (3>2 remove 3), (2>1 remove 2)
                 
        example: 178923, k=3, output = 123
                 (9>2 remove 9), (8>2 remove 8), (7>2 remove 7) 
                 we need a inner loop for this case
         
        logic: greedy, monotonic stack
        time: 2n, because each element is added and removed form the output only once and space = n
    */
    
    public String removeKdigits(String num, int k)  // Time: O(2n) and space O(1)
    {
        int n = num.length();
        if(n <= k) {
            return "0";
        }
        StringBuilder output = new StringBuilder(); // we can also use monotonic stack for this problem because output is traversed from last
        int i, currVal, nextVal;
        
        for(i = 0; i < n-1; i++)
        {
            nextVal = Character.getNumericValue(num.charAt(i+1));
            currVal = Character.getNumericValue(num.charAt(i));
            
            if(k > 0 && currVal > nextVal)  // main logic
            {
                k--;
                while(output.length() > 0)
                {
                    currVal = Character.getNumericValue(output.charAt(output.length()-1));  // iterate output from last
                    if(k > 0 && currVal > nextVal) {
                        output.deleteCharAt(output.length()-1); 
                        k--;
                    }
                    else {
                        break;
                    }
                }
            }
            else
            {
                output = output.append(num.charAt(i));
            }
        }  
        output = output.append(num.charAt(i));
        
        while(k-- > 0) {
            output.deleteCharAt(output.length()-1);     // for remaining left out k, delete in the last
        }
        
        while(output.charAt(0) == '0' && output.length() > 1) {
            output.deleteCharAt(0);     // delete leading zeros in the output (example-2)
        }
        return output.toString();
    }
}
