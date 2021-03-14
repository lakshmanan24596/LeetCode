/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
10 <= low <= high <= 10^9
*/

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> seqDigits = new ArrayList<Integer>();
        int val = 1;
        int elevenPowerVal = 1;                                     // 1, 11, 111, 111, 1111, etc...
        int startVal;
        
        while (val < low) {                                         // find first val                               
            elevenPowerVal = (elevenPowerVal * 10) + 1;
            val += elevenPowerVal;                                  // val will be 1, 12, 123, 1234, etc...
        }
        val = val - elevenPowerVal;
        elevenPowerVal = elevenPowerVal / 10;
        startVal = val;
        
        while (val <= high) {
            if (val >= low) {
                seqDigits.add(val);                                 // val >= low and val <= high
            }
            
            if (val % 10 == 9) {
                elevenPowerVal = (elevenPowerVal * 10) + 1;
                val = startVal + elevenPowerVal;
                startVal = val;
            } else {
                val += elevenPowerVal;
            }
        }
        return seqDigits;
    }
}