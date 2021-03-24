/*
Implement a SnapshotArray that supports the following interface:
SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id

Example 1:
Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 
Constraints:
1 <= length <= 50000
At most 50000 calls will be made to set, snap, and get.
0 <= index < length
0 <= snap_id < (the total number of times we call snap())
0 <= val <= 10^9
*/



/*
    Snap is like a version history for that index
    1) brute:
        space: snap * n     (snap the entire array)
        time:
            constructor: n
            set: 1
            get: 1
            snap: n
    
    2) above approach will be space consuming
        so instead of tracking the entire array during snap, we just track the set()
        for each set(), we track the snapId, index, value
        during get(), we contruct data from all the set()
        
        space: s (no of set())
        time:
            constructor: n
            set: 1
            get: s
            snap: 1
            
    3) binary search (or treeMap floorEntry()) the snap id
        instead of tracking the entire array during snap, we just track the set() for each index independently.
        while tracking, store data in this way --> (index: (snapId : value))
        get query is based on index, snapId --> so the key is index and inner key in snapId
        main logic: array of treemap
        
        now, for a single index there can be many snaps
        if we store the (snapId, value) in sorted order, we can do binary search on the snapId
        https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
        
        space: s (no of set())
        time: 
            constructor: n
            set: log s
            get: log s
            snap: 1
*/

class SnapshotArray {
    TreeMap[] snapHistory;                                                  // main logic: array of treemap
    int currSnapId;
    
    public SnapshotArray(int length) {
        currSnapId = 0;
        snapHistory = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            snapHistory[i] = new TreeMap<Integer, Integer>();
            snapHistory[i].put(currSnapId, 0);
        }
    }
    
    public void set(int index, int val) {
        TreeMap<Integer, Integer> currIndexSnaps = snapHistory[index];
        currIndexSnaps.put(currSnapId, val);
    }
    
    public int snap() {
        currSnapId++;
        return currSnapId - 1;
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> currIndexSnaps = snapHistory[index];
        return currIndexSnaps.floorEntry(snap_id).getValue();               // main logic
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */