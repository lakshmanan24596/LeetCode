/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  
If multiple answers exist, you may return any of them.
(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Note:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/



/*
    DP + LCS
        time: (m * n) + (m + n)
        space: (m * n)
        
    1) find lcs string
    2) remove lcs from s1, s2
*/

class Solution {
    public String shortestCommonSupersequence(String s1, String s2) {
        String lcs = findLcs(s1, s2);                                                           // main logic
        StringBuilder shortCommonSeq = new StringBuilder();
        int index1 = 0, index2 = 0;
        
        for (int i = 0; i < lcs.length(); i++) {
            while (index1 < s1.length() && lcs.charAt(i) != s1.charAt(index1)) {
                shortCommonSeq.append(s1.charAt(index1));
                index1++;
            }
            while (index2 < s2.length() && lcs.charAt(i) != s2.charAt(index2)) {
                shortCommonSeq.append(s2.charAt(index2));
                index2++;
            }
            shortCommonSeq.append(lcs.charAt(i));
            index1++;
            index2++;
        }
        shortCommonSeq.append(s1.substring(index1));
        shortCommonSeq.append(s2.substring(index2));
        return shortCommonSeq.toString();
    }
    
    public String findLcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        String[][] lcsMemo = new String[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            Arrays.fill(lcsMemo[i], "");
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    lcsMemo[i][j] = lcsMemo[i - 1][j - 1] + s1.charAt(i - 1);                       // diagnol + 1
                } else {
                    lcsMemo[i][j] = (lcsMemo[i - 1][j].length() > lcsMemo[i][j - 1].length()) ?     // max (left, top)
                                       lcsMemo[i - 1][j] : lcsMemo[i][j - 1] ;
                }
            }
        }
        return lcsMemo[m][n];
    }
}


/*
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int dp[][] = new int[m+1][n+1];
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<m+1; i++) {
            dp[i][0] = i;
        }
        for(int i=0; i<n+1; i++) {
            dp[0][i] = i;
        }
        for(int i=1; i<m+1; i++){
            for(int j=1; j<n+1; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        while(m > 0 && n > 0){
            if(str1.charAt(m-1) == str2.charAt(n-1)){
                sb.append(str1.charAt(m-1));
                m--;
                n--;
            }else if(dp[m-1][n] < dp[m][n]){
                 sb.append(str1.charAt(m-1));
                 m--;
            }else {
                sb.append(str2.charAt(n-1));
                 n--;
            }
        }
        while (m > 0){
            sb.append(str1.charAt(m-1));
            m--;
        }
        while (n > 0){
            sb.append(str2.charAt(n-1));
            n--;
        }
        return sb.reverse().toString();
    }
}
*/