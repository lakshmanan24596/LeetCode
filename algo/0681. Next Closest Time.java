/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. 
There is no limit on how many times a digit can be reused.
You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:
Input: time = "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
It is not 19:33, because this occurs 23 hours and 59 minutes later.

Example 2:
Input: time = "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

Constraints:
time.length == 5
time is a valid time in the form "HH:MM".
0 <= HH < 24
0 <= MM < 60
*/


/*
    1) brute: 
        generate all 1440 numbers and check which is closer
        time = 24*60 = 1440
    2) brute: 
        only 4 unique values are possible
        generate state space tree, where each level 4 nodes and total level = 4
        time = 4^4 = 256
    3) sort or treemap
        https://leetcode.com/problems/next-closest-time/discuss/107773/Java-AC-5ms-simple-solution-with-detailed-explaination
        time: 4*log4
*/

class Solution {
    TreeSet<Character> set;
    
    public String nextClosestTime(String time) {
        char[] res = time.toCharArray();
        Character[] digits = new Character[]{res[0],res[1],res[3],res[4]};
		this.set = new TreeSet<Character>(Arrays.asList(digits));

		res[4] = getNext(res[4], '9');
		if(res[4] > time.charAt(4)) {
            return new String(res);
        }
        
		res[3] = getNext(res[3], '5');
		if(res[3] > time.charAt(3)) {
            return new String(res);
        }
        
		res[1] = getNext(res[1], res[0] == '2' ? '3' : '9');
		if(res[1] > time.charAt(1)) {
            return new String(res);
        }
        
		res[0] = getNext(res[0], '2');
		return new String(res);
    }
    
    public char getNext(char c, char limit) {
		Character next = set.higher(c);                                     // main logic: find next greatest element
		return (next == null || next > limit) ? set.first() : next;
	}
}