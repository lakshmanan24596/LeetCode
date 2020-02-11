// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

// Example:
// Input: "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

// Note:
// Although the above answer is in lexicographical order, your answer could be in any order you want.


class Solution 
{
    String digits;
    String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> outputList;
    
    public List<String> letterCombinations(String digits) 
    {       
        if(digits.equals("")) return new ArrayList<String>();    
        
        this.digits = digits;     
        this.outputList = new ArrayList<String>();
        
        recur(0, new StringBuilder());
        return outputList;
    }
    
    public void recur(int level, StringBuilder currSb)
    {
        if(level == digits.length())
        {
            outputList.add(currSb.toString());
            return;
        }
        
        int num = digits.charAt(level) - '0';
        String currStr = phone[num];
        int currStrLen = currStr.length();
        
        for(int i=0; i<currStrLen; i++)
        {
            currSb.append(currStr.charAt(i));         // append
            recur(level+1, currSb);
            currSb.deleteCharAt(currSb.length()-1);   // backtrack
        }
    }
}