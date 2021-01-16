/*
Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.

Example 1:
Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].

Example 2:
Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.

Example 3:
Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].
 
Constraints:
1 <= arr.length <= 10^5
-10^4 <= arr[i], difference <= 10^4
*/


/*
// Time: n^2, Space: n
class Solution {
    int difference, n;
    int[] arr, DP;
    
    public int longestSubsequence(int[] arr, int difference) {
        this.arr = arr;
        this.difference = difference;
        n = arr.length;
        DP = new int[n];
        int output = 1;
        
        for (int i = 0; i < n; i++) {
            output = Math.max(output, dfs(i));
        }
        return output;
    }
    
    public int dfs(int curr) {
        if (curr >= n) {
            return 0;
        }
        if (DP[curr] > 0) {
            return DP[curr];
        }
        
        int output = 1;
        for (int next = curr + 1; next < n; next++) {   // this loop makes the the time complexity n^2
            if (arr[curr] + difference == arr[next]) {
                output = 1 + dfs(next);
                break;
            }
        }
        return DP[curr] = output;
    }
}
*/


/*
    DP using hashmap
    same logic as above solution
    for searching, hashmap is used instead of linear search, which reduces the time from n^2 to n
    Time: n, Space: n
*/
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dpMap = new HashMap<Integer, Integer>();
        int output = 0, currOutput;
        
        for (int i = arr.length - 1; i >= 0; i--) {
            currOutput = 1 + dpMap.getOrDefault(arr[i] + difference, 0);    // main logic
            dpMap.put(arr[i], currOutput);
            output = Math.max(output, currOutput);
        }
        return output;
    }
}


/*
    exactly same as above solution, but instead of hashmap, array of size 20,000 is used
    hashmap time = 34 ms and array time = 4 ms
*/
/*
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int size = 10000;
        int[] dp = new int[(size * 2) + 1];  // arr[i] range --> -10^4 to 10^4 --> (10,000 * 2) + 1
        int output = 0, currOutput;
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] + difference < size * -1 || arr[i] + difference > size) {    // corner case
                currOutput = 1;
            } else {
                currOutput = 1 + dp[arr[i] + difference + size];    // main logic
            }
            dp[arr[i] + size] = currOutput;
            output = Math.max(output, currOutput);
        }
        return output;
    }
}
*/