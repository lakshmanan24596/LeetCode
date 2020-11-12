/*
We are given two arrays A and B of words.  Each word is a string of lowercase letters.
Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  
For example, "wrr" is a subset of "warrior", but is not a subset of "world".
Now say a word a from A is universal if for every b in B, b is a subset of a. 
Return a list of all universal words in A.  You can return the words in any order.

Example 1:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]

Example 2:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]

Example 3:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]

Example 4:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]

Example 5:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]
 
Note:
1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].
*/

class Solution 
{
    public List<String> wordSubsets(String[] A, String[] B) 
    {
        List<String> output = new ArrayList<String>();
        if(A.length == 0 || B.length == 0) {
            return output;
        }       
        int[] overallB = new int[26];
        int[] currA, currB;
        
        for(String str : B) 
        {
            currB = findCount(str);
            for(int i = 0; i < 26; i++) {
                overallB[i] = Math.max(overallB[i], currB[i]);
            }
        }
        
        for(String str : A)
        {
            currA = findCount(str);
            int i;
            for(i = 0; i < 26; i++) {
                if(currA[i] < overallB[i]) {    // main logic
                    break;
                }
            }
            if(i == 26) {
                output.add(str);
            }
        }
        return output;
    }
    
    public int[] findCount(String str)
    {
        int[] count = new int[26];
        for(int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        return count;
    }
}
