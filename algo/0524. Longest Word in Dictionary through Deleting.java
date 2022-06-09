/*
Given a string s and a string array dictionary, 
return the longest string in the dictionary that can be formed by deleting some of the given string characters. 

If there is more than one possible result, return the longest word with the smallest lexicographical order. 
If there is no possible result, return the empty string.

Example 1:
Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
Output: "apple"

Example 2:
Input: s = "abpcplea", dictionary = ["a","b","c"]
Output: "a"

Constraints:
1 <= s.length <= 1000
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 1000
s and dictionary[i] consist of lowercase English letters.
*/


/*
    1) brute
        time: d*s
        space: 1
    
    2) count + binary search
        logic: preprocess s and store the count of each character
               do ceil binary seach in the stored count
        time: s + (d*log(s))
        space: s
*/

class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        ArrayList<Integer>[] sCount = new ArrayList[26];
        int index, prevIndex, currCeilIndex;
        ArrayList<Integer> sCountList;
        boolean isFound;
        String output = "";
        
        for (int i = 0 ; i < 26; i++) {
            sCount[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            sCount[index].add(i);
        }
        
        for (String dict : dictionary) {
            prevIndex = -1;
            isFound = true;
            
            for (int i = 0; i < dict.length(); i++) {
                index = dict.charAt(i) - 'a';
                sCountList = sCount[index];
                currCeilIndex = ceilBinarySearch(sCountList, prevIndex);    // main logic
                if (currCeilIndex <= prevIndex) {
                    isFound = false;
                    break;
                }
                prevIndex = currCeilIndex;
            }
            
            if (isFound &&
                ((dict.length() > output.length()) ||
                 (dict.length() == output.length() && output.compareTo(dict) > 0))) {   // smallest lexicographical order
                output = dict;
            }
        }
        return output;
    }
    
    public int ceilBinarySearch(ArrayList<Integer> list, int val) {
        int left = 0;
        int right = list.size() - 1;
        int mid;
        int ceilIndex = -1;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (list.get(mid) > val) {
                ceilIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ceilIndex == -1 ? -1 : list.get(ceilIndex);
    }
}
