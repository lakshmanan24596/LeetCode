/*
Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.
A substring is a contiguous sequence of characters in a string.

Example 1:
Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".

Example 2:
Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".

Constraints:
1 <= s.length <= 1000
1 <= part.length <= 1000
s and part consists of lowercase English letters.
*/


/*
    Similar to 1209 Remove All Adjacent Duplicates in String II    
    
    1) brute
        https://leetcode.com/problems/remove-all-occurrences-of-a-substring/discuss/1298844/Java-recursive-one-line
        time: s * part
        space: s
        
    2) stack
        time: s + part
        space: s 
        example: s="kakaki" and part="kaki"
                 expected="ka" and output="kakaki"
                 it breaks for this input becuase, char 'k' can match inbetween index and also first index
        so this algo will not work
                
    3) kmp
        time: s + part
        space: s
*/


// brute force, time = s * part
class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuffer sb = new StringBuffer(s);
        
        while (true) {
            int index = sb.indexOf(part);   // main logic
            if (index == -1) {
                break;
            }
            sb.delete(index, index + part.length());
        }
        return sb.toString();
    }
}


/*
// stack (will not work)

class Solution {
    public String removeOccurrences(String s, String part) {
		Stack<Pair> stack = new Stack<Pair>();
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (!stack.isEmpty() && s.charAt(i) == part.charAt(stack.peek().partIndex + 1)) {   // match inbetween char
				if (stack.peek().partIndex + 1 == part.length() - 1) {
					for (int count = 0; count < part.length() - 1; count++) {
                        stack.pop();
                    }
				} else {
					stack.push(new Pair(s.charAt(i), stack.peek().partIndex + 1));
				}
			} 
			else if (s.charAt(i) == part.charAt(0)) {                                           // match first char
				if (part.length() != 1) {
					stack.push(new Pair(s.charAt(i), 0));
				}
			} 
			else {                                                                              // not match 
				stack.push(new Pair(s.charAt(i), -1));
			}
		}
		while (!stack.isEmpty()) {                                                              // form output
			output.append(stack.pop().letter);
		}
		return output.reverse().toString();
	}
}

class Pair {
    char letter;
    int partIndex;
    
    Pair(char letter, int partIndex) {
        this.letter = letter;
        this.partIndex = partIndex;
    }
}
*/