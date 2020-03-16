/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example:
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

class Solution 
{
    List<List<String>> output = new ArrayList<List<String>>();
    List<String> currOutput = new ArrayList<String>();
    int count = 0;
    String input;
    
    public List<List<String>> partition(String s)
    {
        this.input = s;
        recurPartition(0);
        return output;
    }
    
    public void recurPartition(int start)                   // Time = O(n! * n)
    {
        if(count == input.length())
        {
            output.add(new ArrayList<String>(currOutput));
            return;
        }   
        
        // check for prefix and recur for suffix.. similar to work break
        for(int i = start; i < input.length(); i++)
        {
            String str = input.substring(start, i+1);
            if(isPalindrome(str))                           // if prefix is not a plaindrome, then we need not recur for suffix
            {
                count += str.length();
                currOutput.add(str);
                
                recurPartition(count);                      // recur suffix (instead of entire string, send remaining count alone)
                
                currOutput.remove(currOutput.size() - 1);   // backtrack
                count -= str.length();                      // backtrack
            }
        }
    }
    
    public boolean isPalindrome(String s)
    {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--)
            if(s.charAt(i) != s.charAt(j))
                return false;
        
        return true;
    }
}