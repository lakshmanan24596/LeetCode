/*
Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.

Implement the ZigzagIterator class:
ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
boolean hasNext() returns true if the iterator still has elements, and false otherwise.
int next() returns the current element of the iterator and moves the iterator to the next element.

Example 1:
Input: v1 = [1,2], v2 = [3,4,5,6]
Output: [1,3,2,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].

Example 2:
Input: v1 = [1], v2 = []
Output: [1]

Example 3:
Input: v1 = [], v2 = [1]
Output: [1]
 
Constraints:
0 <= v1.length, v2.length <= 1000
1 <= v1.length + v2.length <= 2000
-231 <= v1[i], v2[i] <= 231 - 1

Follow up: What if you are given k vectors? How well can your code be extended to such cases?
Clarification for the follow-up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
Example:
Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
Output: [1,4,8,2,5,9,3,6,7]
*/


/*
    Follow-up: 
        k-list is given
        [1,2,3]
        [4,5,6,7]
        [8,9]
        Assume these 3 lists as a 2D matrix with rowIndex, colIndex
    
    1) Brute force
        logic: store rowIndex, colIndex in 2 variables
        time: row * col, where col = max size of given k list
        space: 1
        But this wont work if the given list are of varying size:
            ex: list1.size = 2, list2.size = 10, list3.size = 3
            time complexity of this algo = 10+10+10, but expected time = 2+10+3
    
    2) sort
        logic: sort the rows in increasing order based on list.size and then apply brute force
        time: (rows*log(rows)) + totalElements
        space: 1
    
    3) Queue
        logic: store rowIndex in a queue
        time: totalElements
        space: rows
*/

public class ZigzagIterator {
    Queue<Integer> queue = new LinkedList<Integer>();   // used to store rowIndex
    List<List<Integer>> list;
    int colIndex, tempQueueSize;
    
    public ZigzagIterator(List<Integer> list1, List<Integer> list2) {
        queue = new LinkedList<Integer>();
        list = new ArrayList<List<Integer>>();
        list.add(list1);
        list.add(list2);
        
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isEmpty()) {
                queue.add(i);
            }
        }
        colIndex = 0;
        tempQueueSize = queue.size();
    }

    public int next() {                                 // similar to merge K sorted list
        int rowIndex = queue.remove();
        int next = list.get(rowIndex).get(colIndex);    // main logic
        
        if (colIndex != list.get(rowIndex).size() - 1) {
            queue.add(rowIndex);
        }
        
        tempQueueSize--;
        if (tempQueueSize == 0) {                       // used to increment colIndex
            tempQueueSize = queue.size();
            colIndex++;
        }
        return next;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */