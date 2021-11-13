/*
Given a string s and an integer k, rearrange s such that the same characters are at least distance k from each other. 
If it is not possible to rearrange the string, return an empty string "".

Example 1:
Input: s = "aabbcc", k = 3
Output: "abcabc"
Explanation: The same letters are at least a distance of 3 from each other.

Example 2:
Input: s = "aaabc", k = 3
Output: ""
Explanation: It is not possible to rearrange the string.

Example 3:
Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least a distance of 2 from each other.

Constraints:
1 <= s.length <= 3 * 105
s consists of only lowercase English letters.
0 <= k <= s.length
*/



/*
    logic: priority queue
    we pop k elements in each iteration
    
    base case: k = 0 and k > 26
    corner case:
        example-1:
        maxHeap data: a=2, b=2, c=2
        pop k=3 times, so now output = "abc" and maxHeap data is a=1, b=1, c=1
        now if we pop "b" or "c" then it is wrong, becuase we need to pop "a" next
        to do this, if freq are equal, we pop with lexi order using pQueue comparator a[0] - b[0]
        
    time: O(n * log26) = O(n)
    space: O(n + 26)
*/

class Solution {
    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        if (k > 26) {
            return "";
        }
        int[] freq = new int[26];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a, b) -> (
            b[1] == a[1] ? a[0] - b[0] : b[1] - a[1]            // maxHeap based on freq (suppose, if equal then follow lexi order)
        ));
        StringBuilder output = new StringBuilder();
        int[] poppedElem;
        List<int[]> removedElems;
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                maxHeap.add(new int[] {i, freq[i]});
            }
        }
        
        while (!maxHeap.isEmpty()) {
            removedElems = new ArrayList<int[]>();
            for (int i = 0; i < k; i++) {                       // main logic: remove k elements from maxHeap
                if (maxHeap.isEmpty()) {
                    return (output.length() == s.length()) ? output.toString() : "";
                }
                poppedElem = maxHeap.remove();
                output.append((char) (poppedElem[0] + 97));
                removedElems.add(poppedElem);
            }
            for (int[] elem : removedElems) {                   // add the removed elements
                --elem[1];
                if (elem[1] != 0) {
                    maxHeap.add(elem);
                }
            }
        }
        return output.toString();
    }
}
