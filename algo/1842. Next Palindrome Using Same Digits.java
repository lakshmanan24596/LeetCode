/*
You are given a numeric string num, representing a very large palindrome.
Return the smallest palindrome larger than num that can be created by rearranging its digits. 
If no such palindrome exists, return an empty string "".
A palindrome is a number that reads the same backward as forward.

Example 1:
Input: num = "1221"
Output: "2112"
Explanation: The next palindrome larger than "1221" is "2112".

Example 2:
Input: num = "32123"
Output: ""
Explanation: No palindromes larger than "32123" can be made by rearranging the digits.

Example 3:
Input: num = "45544554"
Output: "54455445"
Explanation: The next palindrome larger than "45544554" is "54455445".

Constraints:
1 <= num.length <= 105
num is a palindrome.
*/


/*
    logic:
        1) in palindrome, first half = second half, so processing first half alone is enough
            in example-3: num = 4554 --> output = 5445
            second half output = reverse of first half output = 5445
            so output = 5445 + 5445 = 54455445
        2) to find output of first half, we need to use next greater element technique (find, swap, reverse)
        
    time: n
    space: n
    https://leetcode.com/problems/next-palindrome-using-same-digits/discuss/1976343/Java-Simple-O(n)-using-Next-Greater-Element-III-approach
*/

class Solution {
    public String nextPalindrome(String num) {
        if (num.length() <= 2) {
            return "";
        }
        char[] nums = num.toCharArray();
        int mid = (nums.length / 2) - 1;
        int i = -1, j = -1;
        
        // find i
        for (i = mid - 1; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i == -1) {
            return "";
        }
        
        // find j such that it is greater than nums[j] > nums[i]
        for (j = mid; j >= 0; j--) {
            if (nums[j] > nums[i]) {
                break;
            }
        }
        
        // logic of "next greater element"
        swap(nums, i, j);
        reverse(nums, i + 1, mid);
        
        // second half = reverse of first half
        i = mid; 
        j = (nums.length % 2 == 0) ? mid + 1 : mid + 2;
        while (j < nums.length) {
            nums[j++] = nums[i--];
        }
        return new String(nums);
    }
    
    public void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(char[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
