/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create. You can return the output in any order.

Example 1:
Input: S = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]

Example 2:
Input: S = "3z4"
Output: ["3z4","3Z4"]

Example 3:
Input: S = "12345"
Output: ["12345"]

Example 4:
Input: S = "0"
Output: ["0"]
 
Constraints:
S will be a string with length between 1 and 12.
S will consist only of letters or digits.
*/


/*
class Solution 
{
    List<String> output = new ArrayList<String>();
    String str;
    
    public List<String> letterCasePermutation(String str) 
    {
        this.str = str;
        recur(0, "");
        return output;
    }
    
    public void recur(int index, String curr)
    {
        if(index == str.length()) {
            output.add(curr);
            return;
        }
        
        char ch = str.charAt(index);
        if(Character.isLetter(ch))
        {
            recur(index + 1, curr + Character.toLowerCase(ch));
            recur(index + 1, curr + Character.toUpperCase(ch));
        }
        else
        {
            recur(index + 1, curr + ch);
        }
    }
}
*/

 
// Logic: DFS, Time: 2^n, Space: n
// instead of string concat, we set value in array which is much faster
// https://leetcode.com/problems/letter-case-permutation/discuss/255071/Java-detailed-explanation-of-DFSBacktracking-solution
class Solution 
{
    List<String> output = new ArrayList<String>();
    char[] arr;
    
    public List<String> letterCasePermutation(String str) 
    {
        arr = str.toCharArray();
        recur(0);
        return output;
    }
    
    public void recur(int index)
    {
        if(index == arr.length) {
            output.add(new String(arr));
            return;
        }
        
        char ch = arr[index];
        if(Character.isLetter(ch))
        {
            arr[index] = Character.toLowerCase(ch);
            recur(index + 1);
            
            arr[index] = Character.toUpperCase(ch);
            recur(index + 1);
        }
        else
        {
            recur(index + 1);
        }
    }
}
