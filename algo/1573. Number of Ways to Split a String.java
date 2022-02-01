/*
Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.
Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. 
Since the answer may be too large, return it modulo 109 + 7.


Example 1:
Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"

Example 2:
Input: s = "1001"
Output: 0

Example 3:
Input: s = "0000"
Output: 3
Explanation: There are three ways to split s in 3 parts.
"0|0|00"
"0|00|0"
"00|0|0"

Constraints:
3 <= s.length <= 105
s[i] is either '0' or '1'.
*/


/*
    time: n
    space: 1
    logic: math
    
    To split it into 3 parts, we need 2 splits in all possible middle positions.
    the oneCount should be the same in all 3 parts
    so 1's in each part = totalOneCount / 3
    
    ex: if totalOneCount = 6, then split should be made at all possible positions of currOneCount = 2 and currOneCount = 4
    output = firstSplit * secondSplit
    
    https://leetcode.com/problems/number-of-ways-to-split-a-string/discuss/830455/JavaPython-3-Multiplication-of-the-ways-of-1st-and-2nd-cuts-w-explanation-and-analysis.
*/

class Solution {
    public int numWays(String s) {
        long oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                oneCount++;
            }
        }
        if (oneCount % 3 != 0) {            // example 2
            return 0;
        }
        if (oneCount == 0) {                // example 3
            long n = s.length() - 2;
            return (int) (n * (n + 1) / 2 % 1_000_000_007);
        }
        long firstSplit = 0, secondSplit = 0;
        long currCount = 0;
        long eachBlock = oneCount / 3;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                currCount++;
            }
            
            if (currCount == eachBlock) {
                firstSplit++;
            } else if (currCount == eachBlock * 2) {
                secondSplit++;
            } else if (currCount > eachBlock * 2) {
                break;
            }
        }
        return (int) (firstSplit * secondSplit % 1_000_000_007);    // main logic
    }
}
