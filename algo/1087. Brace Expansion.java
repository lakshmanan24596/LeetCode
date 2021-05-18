/*
You are given a string s representing a list of words. Each letter in the word has one or more options.
If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. 
The original list is ["ab", "ac"].
Return all words that can be formed in this manner, sorted in lexicographical order.

Example 1:
Input: s = "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]

Example 2:
Input: s = "abcd"
Output: ["abcd"]

Constraints:
1 <= s.length <= 50
s consists of curly brackets '{}', commas ',', and lowercase English letters.
s is guaranteed to be a valid input.
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
*/


// classical backtracking
class Solution {
    List<String> output;
    char[] inputArr;
    
    public String[] expand(String s) {
        this.inputArr = s.toCharArray();
        this.output = new ArrayList<String>();
        
        dfs(0, new StringBuilder());
        return convertListToArray();
    }
    
    public void dfs(int curr, StringBuilder currOutput) {
        if (curr == inputArr.length) {
            output.add(currOutput.toString());
            return;
        }
        
        if (inputArr[curr] == '{') {
            curr++;
            List<Character> braceInputs = new ArrayList<Character>();
            while (inputArr[curr] != '}') {
                if (inputArr[curr] != ',') {
                    braceInputs.add(inputArr[curr]);
                }
                curr++;
            }
            Collections.sort(braceInputs);                                  // main logic: sort brace input
            for (char nextInput : braceInputs) {
                currOutput.append(nextInput);
                dfs(curr + 1, currOutput);                                  // dfs --> each brace char
                currOutput.deleteCharAt(currOutput.length() - 1);           // backtracking
            }
        } else {
            currOutput.append(inputArr[curr]);
            dfs(curr + 1, currOutput);
            currOutput.deleteCharAt(currOutput.length() - 1);
        }
    }
    
    public String[] convertListToArray() {
        String[] outputArr = new String[output.size()];
        
        for (int i = 0; i < output.size(); i++) {
            outputArr[i] = output.get(i);
        }
        return outputArr;
    }
}