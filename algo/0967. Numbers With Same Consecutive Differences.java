/*
Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
You may return the answer in any order.

Example 1:
Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.

Example 2:
Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Example 3:
Input: n = 2, k = 0
Output: [11,22,33,44,55,66,77,88,99]

Example 4:
Input: n = 2, k = 2
Output: [13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]

Constraints:
2 <= n <= 9
0 <= k <= 9
*/


class Solution {
    ArrayList<Integer> outputList;
    int k;
    
    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        outputList = new ArrayList<Integer>();
        int outputIndex = 0;
        int[] outputArr;
        
        for (int i = 1; i <= 9; i++) {              // main logic
            dfs(i, n - 1);
        }
        
        outputArr = new int[outputList.size()];
        for (int output : outputList) {
            outputArr[outputIndex++] = output;
        }
        return outputArr;
    }
    
    public void dfs(int num, int n) {               // Time: 2^n, exponential
        if (n == 0) {
            outputList.add(num);
            return;
        }
        int newNum;
        int lastDigit = num % 10;
        
        if (lastDigit - k >= 0) {
            newNum = (num * 10) + lastDigit - k;
            dfs(newNum, n - 1);
        }
        if (k != 0 && lastDigit + k <= 9) {
            newNum = (num * 10) + lastDigit + k;
            dfs(newNum, n - 1);
        } 
    }
}