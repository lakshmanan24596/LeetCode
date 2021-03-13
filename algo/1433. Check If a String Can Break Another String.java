/*
Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa. 
In other words s2 can break s1 or vice-versa.
A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.

Example 1:
Input: s1 = "abc", s2 = "xya"
Output: true
Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".

Example 2:
Input: s1 = "abe", s2 = "acd"
Output: false 
Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.

Example 3:
Input: s1 = "leetcodee", s2 = "interview"
Output: true

Constraints:
s1.length == n
s2.length == n
1 <= n <= 10^5
All strings consist of lowercase English letters.
*/


/*
    logic: greedy, sort
    time: n*logn
    space: 1
*/
/*
class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] ch1 = s1.toCharArray(); 
        char[] ch2 = s2.toCharArray(); 
        int n = ch1.length;
        Arrays.sort(ch1); 
        Arrays.sort(ch2); 
        int greaterString = 0;                  // (0, 1, 2) ==> (equal, 1>2, 2>1)
        
        for (int i = 0; i < n; i++) {
            if (ch1[i] > ch2[i]) {
                if (greaterString == 2) {
                    return false;
                }
                greaterString = 1;
            } else if (ch2[i] > ch1[i]) {
                if (greaterString == 1) {
                    return false;
                }
                greaterString = 2;
            }
        }
        return true;
    }
}
*/


/*
    logic: same as above
    we can use bucket sort of size c = 26
    time: n+c
    space: c
*/
class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] ch1 = s1.toCharArray(); 
        char[] ch2 = s2.toCharArray(); 
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        int n = ch1.length;
        int greaterString = 0;                  // (0, 1, 2) ==> (equal, 1>2, 2>1)
        int count1 = 0, count2 = 0;
        
        for (int i = 0; i < n; i++) {
            freq1[ch1[i] - 'a']++;
            freq2[ch2[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            count1 += freq1[i];
            count2 += freq2[i];
            
            if (count1 > count2) {
                if (greaterString == 2) {
                    return false;
                }
                greaterString = 1;
            } else if (count2 > count1) {
                if (greaterString == 1) {
                    return false;
                }
                greaterString = 2;
            }
        }
        return true;
    }
}