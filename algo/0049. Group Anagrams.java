/*
Given an array of strings, group anagrams together.

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.
*/

class Solution 
{
    public List<List<String>> groupAnagrams(String[] strs)  // Time: O(nk)
    {     
        int[] primeArr = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,101};
        
        Map<Long,List<String>> map = new HashMap<Long,List<String>>();  // key: hashCode, value: list of Strings
        int length = strs.length;
        
        for(int i=0; i<length; i++)
        {
            long hashCode = 1;
            int currLen = strs[i].length();          
            for(int j=0; j<currLen; j++)
            {
                char ch = strs[i].charAt(j);
                hashCode *= primeArr[ch - 'a'];     // multiply the hash codes
            }
            
            List<String> val = map.get(hashCode);
            if(val == null) val = new ArrayList<String>();
            
            val.add(strs[i]);
            map.put(hashCode, val);
        }
        
        List<List<String>> output = new ArrayList<List<String>>();
        output.addAll(map.values());        
        return output;
    }
}