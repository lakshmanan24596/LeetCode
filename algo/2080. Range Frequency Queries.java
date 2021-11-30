/*
Design a data structure to find the frequency of a given value in a given subarray.
The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

Implement the RangeFreqQuery class:
RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive). 

Example 1:
Input
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
Output
[null, 1, 2]

Explanation
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.

Constraints:
1 <= arr.length <= 105
1 <= arr[i], value <= 104
0 <= left <= right < arr.length
At most 105 calls will be made to query
*/


/*
    1) brute:
        time: qn
        space: n
    2) pre-process, time efficient:
        time: q
        space: n*n
        logic: at each index, create a hashmap
               which means map (k,v) = (int, map)

    3) logic: hashmap + binary search
        time: q*logn
        space: n
    
        logic:
            Use a hash table that stored for each value, the indices where that value appeared.
            Use binary search over the indices of a value to find its range frequency.
            map (k,v) ==> (arr value, list of its indexes)
            do binary search in list of index
            ex-1: key = 33, value = [1, 7]
            now do binary search in [1, 7] for the given left, right range
        
        alternate solution using treemap:
        https://leetcode.com/problems/range-frequency-queries/discuss/1589040/Java-TreeMap
*/

class RangeFreqQuery {
    Map<Integer, List<Integer>> freqMap = new HashMap<Integer, List<Integer>>();    // arr value, list of its indexes
    
    public RangeFreqQuery(int[] arr) {
        List<Integer> indexes;
        for (int i = 0; i < arr.length; i++) {
            freqMap.putIfAbsent(arr[i], new ArrayList<Integer>());
            freqMap.get(arr[i]).add(i);
        }
    }
    
    public int query(int left, int right, int value) {
        List<Integer> indexes = freqMap.get(value);
        if (indexes == null || indexes.size() < 1) {                // value not present in given arr
            return 0;
        }
        
        int lowerArrIndex = binarySearch(true, left, indexes);      // ceil
        int upperArrIndex = binarySearch(false, right, indexes);    // floor
        if (lowerArrIndex == -1 || upperArrIndex == -1) {           // value not present in given left, right arr range
            return 0;
        }
        return upperArrIndex - lowerArrIndex + 1;
    }
    
    public int binarySearch(boolean isCeil, int val, List<Integer> list) {
        int left = 0; 
        int right = list.size() - 1;
        int mid, midVal;
        int output = -1;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            midVal = list.get(mid);
            
            if (isCeil) {
                if (val <= midVal) {
                    right = mid - 1;
                    output = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (val >= midVal) {
                    left = mid + 1;
                    output = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return output;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */