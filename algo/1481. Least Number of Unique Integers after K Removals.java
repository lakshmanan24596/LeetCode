/*
Given an array of integers arr and an integer k. 
Find the least number of unique integers after removing exactly k elements.

Example 1:
Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.

Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
*/


/*
    Logic:
        remove the lowest freq numbers first

    Implementation:    
        1) hashmap + pQueue
            time: n*logn
            space: n
        2) hashmap + arr
            time: n
            space: n
            Logic: the freq values will always range between 0 to n, so use arr of size n+1
*/


/*
// time: n*logn, space: n
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int arrVal : arr) {
            freqMap.put(arrVal, freqMap.getOrDefault(arrVal, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pQueue = new PriorityQueue<Map.Entry<Integer, Integer>>((entry1, entry2) -> entry1.getValue() - entry2.getValue());
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pQueue.add(entry);
        }
        
        int totalUniqueCount = pQueue.size();
        int removedCount = 0;
        Map.Entry<Integer, Integer> curr;

        while (!pQueue.isEmpty()) {
            curr = pQueue.remove();
            k -= curr.getValue();
            if (k <= 0) {
                return (totalUniqueCount - removedCount) - ((k == 0) ? 1 : 0);
            }
            removedCount++;
        }
        return totalUniqueCount - removedCount;
    }
}
*/


// time: n, space: n
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int arrVal : arr) {
            freqMap.put(arrVal, freqMap.getOrDefault(arrVal, 0) + 1);
        }
        
        int[] freqArr = new int[arr.length + 1];        // instead of pQueue, we use arr to reduce time
        for (int val : freqMap.values()) {
            freqArr[val]++;
        }
        
        int totalUniqueCount = freqMap.size();
        int removedCount = 0;
        for (int i = 0; i <= freqArr.length; i++) {
            while (freqArr[i]-- > 0) {
                k -= i;                                 // main logic: use index as value
                if (k <= 0) {
                    return (totalUniqueCount - removedCount) - ((k == 0) ? 1 : 0);
                }
                removedCount++;
            }
        }
        return totalUniqueCount - removedCount;
    }
}