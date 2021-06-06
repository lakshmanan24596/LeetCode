/*
You are given a string s and an integer k, 
a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, 
causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:
1 <= s.length <= 105
2 <= k <= 104
s only contains lower case English letters.
*/


/*
    1) brute                        : O(n * (n/k))
    2) stack with letter            : O(n * k)
    3) stack with pair(letter, freq): O(n)
*/

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<Pair>();
        char[] arr = s.toCharArray();
        StringBuilder output = new StringBuilder();
        Pair peekPair;
        
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || stack.peek().letter != arr[i]) {
                stack.push(new Pair(arr[i], 1));
            } else {
                peekPair = stack.pop();
                if (peekPair.freq != k - 1) {
                    peekPair.freq += 1;
                    stack.push(peekPair);
                }
            }
        }
        while (!stack.isEmpty()) {
            peekPair = stack.pop();
            while (peekPair.freq-- > 0) {
                output.append(peekPair.letter);
            }
        }
        return output.reverse().toString();
    }
}

class Pair {
    char letter;
    int freq;
    
    Pair(char letter, int freq) {
        this.letter = letter;
        this.freq = freq;
    }
}