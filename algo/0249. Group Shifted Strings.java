/*
We can shift a string by shifting each of its letters to its successive letter.
For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. 
You may return the answer in any order. 

Example 1:
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:
Input: strings = ["a"]
Output: [["a"]]

Constraints:
1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
*/



/*
    1) Brute force:
        time: W * W * WL
        space: 1
        
    2) HashMap:
        time: W * WL
        space: W * WL
        
        logic: 
        1) how to compare abc and bcd are the same?
            hash (abc) = (b-a) + (c-b) ==> 11 ==> aa
            hash (bcd) = (c-b) + (d-c) ==> 11 ==> aa
            group same hashvalues into same group
            
        2) why +26 and &26 are done?
            az and ba are the same
            hash (az) = (z-a) = 25      = y
            hash (ba) = (a-b) = -1 = 25 = y
            answer: for converting negative diff to positive diff
*/
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groups = new HashMap<String, List<String>>();
        String key;
        
        for (String str : strings) {
            key = "";
            for (int i = 1; i < str.length(); i++) {
                key += (char) (((str.charAt(i) - str.charAt(i - 1)) + 26) % 26);    // main logic
            }
            
            List<String> currGroup = groups.get(key);
            if (currGroup == null) {
                currGroup = new ArrayList<String>();
                groups.put(key, currGroup);
            }
            currGroup.add(str);
        }
        return new ArrayList<List<String>>(groups.values());
    }
}