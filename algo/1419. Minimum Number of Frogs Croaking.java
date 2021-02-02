/*
Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple “croak” are mixed. 
Return the minimum number of different frogs to finish all the croak in the given string.
A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. 
The frogs have to print all five letters to finish a croak. 
If the given string is not a combination of valid "croak" return -1.

Example 1:
Input: croakOfFrogs = "croakcroak"
Output: 1 
Explanation: One frog yelling "croak" twice.

Example 2:
Input: croakOfFrogs = "crcoakroak"
Output: 2 
Explanation: The minimum number of frogs is two. 
The first frog could yell "crcoakroak".
The second frog could yell later "crcoakroak".

Example 3:
Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from different frogs.

Example 4:
Input: croakOfFrogs = "croakcroa"
Output: -1

Constraints:
1 <= croakOfFrogs.length <= 10^5
All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
*/


/*
    Simulation technique by using count array[5]
    Time: n, Space: 5
    https://leetcode.com/problems/minimum-number-of-frogs-croaking/discuss/586543/C%2B%2BJava-with-picture-simulation
*/
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs == null || croakOfFrogs.length() == 0 || croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        int[] count = new int[5];
        char ch;
        int index;
        int frogs = 0, totalFrogs = 0;
        String croak = "croak";
        
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            ch = croakOfFrogs.charAt(i);
            index = croak.indexOf(ch);
            if(index == -1) {
                return -1;
            }
            
            if (index != 0) {
                if (count[index - 1] < 1) {
                    return -1;
                }
                count[index - 1]--;     // decrement prev char
            } else {
                frogs++;
                totalFrogs = Math.max(totalFrogs, frogs);
            }
            
            if (index != 4) {
                count[index]++;         // increment curr char
            } else {
                frogs--;
            }
        }
        return totalFrogs;
    }
}