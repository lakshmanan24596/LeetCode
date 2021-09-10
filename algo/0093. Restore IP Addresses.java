/*
Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. 
You can return them in any order.
A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. 
For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses. 

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

Example 2:
Input: s = "0000"
Output: ["0.0.0.0"]

Example 3:
Input: s = "1111"
Output: ["1.1.1.1"]

Example 4:
Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]

Example 5:
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:
0 <= s.length <= 3000
s consists of digits only.
*/


class Solution {
    String s;
    List<String> output;
    char dot = '.';
    int totalLevels = 4;
    
    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        this.output = new ArrayList<String>();
        recur(0, 0, "");
        return output;
    }
    
    public void recur(int startIndex, int level, String currOutput) {    // backtracking
        if (level == totalLevels) {
            if (startIndex == s.length()) {
                output.add(currOutput);
            }
            return;
        }
        int currIp = 0;
        String nextIp;
        
        for (int i = startIndex; i < s.length(); i++) {
            if (i != startIndex && currIp == 0) {       // cannot have leading zeros
                break;
            }
            currIp = (currIp * 10) + (s.charAt(i) - '0');
            if (currIp >= 0 && currIp <= 255) {
                nextIp = (level != totalLevels - 1) ? String.valueOf(currIp) + dot : String.valueOf(currIp);
                recur(i + 1, level + 1, currOutput + nextIp);           
            } else {
                break;                                  // exceeded 255 range
            }
        }
    }
}