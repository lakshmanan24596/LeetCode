/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return all the possible results. You may return the answer in any order. 

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]

Constraints:
1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
*/


/*
    Backtracking with pruning
        time: 2^n
        space: n
*/
class Solution {
    Set<String> output = new HashSet<String>();
    String s;
    
    public List<String> removeInvalidParentheses(String s) { 
        this.s = s;
        int[] toBeRemoved = findMisplacedChar();
        findValidParenthesis(0, 0, 0, toBeRemoved[0], toBeRemoved[1], "");
        return new ArrayList<String>(output);
    }
    
    public int[] findMisplacedChar() {                          // find no of left and right brace that needs to be removed
        int leftRemove = 0;
        int rightRemove = 0;
        
        for (int i = 0; i < s.length(); i++) {                  // refer hint 5
            if (s.charAt(i) == '(') {
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }
        return new int[] {leftRemove, rightRemove};
    }
    
    /*
        left = no of ( added in currOutput
        right = no of ) added in currOutput
        leftRemain = no of remaining ( that needs to be removed in future
        leftRemain = no of remaining ) that needs to be removed in future
    */
    public void findValidParenthesis(int index, int left, int right, int leftRemain, int rightRemain, String currOutput) {
        if (index == s.length()) {
            if (leftRemain == 0 && rightRemain == 0) {            // both left and right remaining are removed fully
                output.add(currOutput);
            }
            return;
        }
        char letter = s.charAt(index);
        
        if (letter == '(') {
            findValidParenthesis(index + 1, left + 1, right, leftRemain, rightRemain, currOutput + letter);     // add left
            if (leftRemain > 0) {
                findValidParenthesis(index + 1, left, right, leftRemain - 1, rightRemain, currOutput);          // remove left
            }
        } 
        else if (letter == ')') {
            if (left > right) {     // additional condition to maintain balanced parenthesis
                findValidParenthesis(index + 1, left, right + 1, leftRemain, rightRemain, currOutput + letter); // add right
            }
            if (rightRemain > 0) {
                findValidParenthesis(index + 1, left, right, leftRemain, rightRemain - 1, currOutput);          // remove right
            }
        } else {
            findValidParenthesis(index + 1, left, right, leftRemain, rightRemain, currOutput + letter);         // add alplabet
        }
    }
}