/*
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
Return the maximum possible length of s.

Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.

Example 2:
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".

Example 3:
Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 
Constraints:
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
*/

class Solution 
{    
    List<String> inputList;
    
    public int maxLength(List<String> arr)                              // Time: (2^n) * n and Space = n because height of tree in n
    {
        this.inputList = arr;
        boolean[] boolArr = new boolean[26];
        return recur(0, boolArr, 0);
    }
    
    public int recur(int level, boolean[] boolArr, int currOutput)      // similar to SumOfSubsets problem (max(exclude, include))
    {
        if(level == inputList.size())
        {
            return currOutput;
        }
        
        int exclude = recur(level + 1, boolArr.clone(), currOutput);
        
        boolean canInclude = true;
        String currString = inputList.get(level);
        int currIndex;
        for(int i = 0; i < currString.length(); i++)
        {
            currIndex = currString.charAt(i) - 'a';
            if(boolArr[currIndex])
            {
                canInclude = false;
                break;
            }
            boolArr[currIndex] = true;
        }
        int include = (canInclude) ? recur(level + 1, boolArr.clone(), currOutput + currString.length()) : 0;
        
        return Math.max(exclude, include);
    }
}


// Same time as above solution ((2^n) * n)
// class Solution 
// {    
//     public int max = 0;
//     List<String> input;
    
//     public int maxLength(List<String> input) 
//     {
//      this.input = input;
//         dfs(0, "");
//         return max;
//     }

//     public void dfs(int index, String str)                              // Time: (2^n) * n
//     {
//         if (isUnique(str)) 
//          max = Math.max(max, str.length());
//         else
//             return;
        
//         for (int i = index; i < input.size(); i++)
//             dfs(i + 1, str + input.get(i));                             // string append will take time
//     }
    
//     public boolean isUnique(String s) 
//     {
//         boolean[] boolArr = new boolean[26];
//         int index;
        
//         for (int i = 0; i < s.length(); i++)
//         {
//          index = s.charAt(i) - 'a';
//          if(boolArr[index])
//              return false;
//          boolArr[index] = true;
//         }    
//         return true;
//     }
// }