/*
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.

Example 1:
Input: data = [1,0,1,0,1]
Output: 1
Explanation: 
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.

Example 2:
Input: data = [0,0,0,1,0]
Output: 0
Explanation: 
Since there is only one 1 in the array, no swaps needed.

Example 3:
Input: data = [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation: 
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].

Example 4:
Input: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
Output: 8

Constraints:
1 <= data.length <= 105
data[i] is 0 or 1.
*/


/*
    sliding window
    time: 2n, space: 1
*/
class Solution {
    public int minSwaps(int[] data) {
        int oneCount = 0;
        int windowSize = 0;
        int maxOneCount = 0;
        
        // windowSize = Arrays.stream(data).sum();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                windowSize++;                                   // window size = no of one's in the array (fixed size)
            }    
        }
        if (windowSize <= 1 || windowSize == data.length) {     // no need swaps
            return 0;
        }
        
        for (int i = 0; i < data.length; i++) {
            if (i >= windowSize) {
                maxOneCount = Math.max(maxOneCount, oneCount);
                if (data[i - windowSize] == 1) {                // startIndex = i - windowSize
                    oneCount--;                                 // start++ of window
                } 
            }
            if (data[i] == 1) {
                oneCount++;                                     // end++ of window
            }
        }
        maxOneCount = Math.max(maxOneCount, oneCount);
        return windowSize - maxOneCount;
    }
}