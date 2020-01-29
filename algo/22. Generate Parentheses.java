class Solution 
{
    List<String> output = new ArrayList<String>();
    int n;
    
    public List<String> generateParenthesis(int n) 
    {
        this.n = n;
        recur("(", 1, 0);   
        return output;
    }
    
    public void recur(String curr, int open, int close)
    {
        if(curr.length() == 2*n)
        {
            output.add(curr);
            return;
        }
        
        if(open < n)
            recur(curr+"(", open+1, close);

        if(close < open)
            recur(curr+")", open, close+1);
    }
}