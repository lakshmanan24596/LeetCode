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