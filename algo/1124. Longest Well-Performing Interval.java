/*
We are given hours, a list of the number of hours worked per day for a given employee.
A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
Return the length of the longest well-performing interval. 

Example 1:
Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].

Example 2:
Input: hours = [6,6,6]
Output: 0 

Constraints:
1 <= hours.length <= 104
0 <= hours[i] <= 16
*/


/*
    1) brute: n*n, 1
    2) optimal: n, n
        a) sliding window wont work because --> ex: 1 0 0 0 1 1 1 --> expected = 7, actual = 3
           so we need leftmost element 
        b) so need hashmap which stores prefixSum
            if prefixSum is +ve, then entire arr itself is longest
            if prefixSum is -ve, we need to check leftmost index which has currSum - 1 
            why currSum -1? because, we need count(True) > count(False)
*/

class Solution {
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> prefixSumIndex = new HashMap<Integer, Integer>();
        prefixSumIndex.put(0, -1);
        int longestWPI = 0;
        int currWPI;
        int currSum = 0;
        
        for (int i = 0; i < hours.length; i++) {
            currSum += (hours[i] > 8) ? 1 : -1;                         // main logic
            
            if (currSum > 0) {
                longestWPI = Math.max(longestWPI, i + 1);               // main logic
            } else {
                if (prefixSumIndex.containsKey(currSum - 1)) {  
                    currWPI = i - prefixSumIndex.get(currSum - 1);      // main logic
                    longestWPI = Math.max(longestWPI, currWPI);
                }
            }
            prefixSumIndex.putIfAbsent(currSum, i);
        }
        return longestWPI;
    }
}