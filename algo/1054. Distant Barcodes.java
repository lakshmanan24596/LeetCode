/*
In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].
Rearrange the barcodes so that no two adjacent barcodes are equal. 
You may return any answer, and it is guaranteed an answer exists.

Example 1:
Input: barcodes = [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]

Example 2:
Input: barcodes = [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,1,2,1,2]

Constraints:
1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000
*/

/*
    Time: n*logn, Space: n
    Logic: Max heap
    Pick top 2 max freq barcode and fill it
*/
/*
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int barcode : barcodes) {
            countMap.put(barcode, countMap.getOrDefault(barcode, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pQueue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            pQueue.add(entry);
        }
        
        int i = 0;
        Map.Entry<Integer, Integer> first, second;
        while (pQueue.size() > 1) {
            first = pQueue.remove();
            barcodes[i++] = first.getKey();
            second = pQueue.remove();
            barcodes[i++] = second.getKey();
            if (first.getValue() != 1) {
                first.setValue(first.getValue() - 1);
                pQueue.add(first);
            }
            if (second.getValue() != 1) {
                second.setValue(second.getValue() - 1);
                pQueue.add(second);
            }
        }
        if(!pQueue.isEmpty()) {
            first = pQueue.remove();
            barcodes[i] = first.getKey();
        }
        return barcodes;
    }
}
*/


/*
    Time: n*logn, Space: n
    Logic: fill the elements by leaving a space in-between
    https://leetcode.com/problems/distant-barcodes/discuss/299371/C%2B%2B-with-picture-O(N)
*/
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int barcode : barcodes) {
            countMap.put(barcode, countMap.getOrDefault(barcode, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(countMap.entrySet());
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
        
        int i = 0, count, barcode;
        for (Map.Entry<Integer, Integer> entry : list) {
            barcode = entry.getKey();
            count = entry.getValue();
            while (count-- > 0) {
                barcodes[i] = barcode;
                i += 2;
                if (i >= barcodes.length) {
                    i = 1;
                }
            }
        }
        return barcodes;
    }
}

/*
    Another approach: Bucket sort
    Since the barcodes values are limited to 10,000, we can create 10,000 buckets and do bucket sort
    Time: n, space: 10,000
*/