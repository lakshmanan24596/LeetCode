/*
You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
You have an array arr of length length with all zeros, and you have some operation to apply on arr. 
In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
Return arr after applying all the updates.

Example 1:
Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]

Example 2:
Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]

Constraints:
1 <= length <= 105
0 <= updates.length <= 104
0 <= startIdxi <= endIdxi < length
-1000 <= inci <= 1000
*/


/*
    1) brute force: 
        iterate from start to end index
        time: updates * length
        space: length
        
    2) segment tree for range based query:
        time: (updates * log(length)) + length
        space: length
    
    3) prefixSum:
        time: updates + length
        space: length
        logic: start element   --> += val
               end element + 1 --> -= val
*/

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] arr = new int[length];
        int start, end, val;
        
        for (int[] update : updates) {
            start = update[0];
            end = update[1];
            val = update[2];
            
            arr[start] += val;              // main logic: update first element
            if (end != length - 1) {
                arr[end + 1] -= val;        // main logic: update last element
            }
        }
        for (int i = 1; i < length; i++) {  // prefixSum calculation
            arr[i] = arr[i] + arr[i - 1];
        }
        return arr;
    }
}
