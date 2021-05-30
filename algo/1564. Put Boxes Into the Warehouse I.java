/*
You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit width and the heights of n rooms in a warehouse respectively. 
The warehouse's rooms are labelled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.

Boxes are put into the warehouse by the following rules:
Boxes cannot be stacked.
You can rearrange the insertion order of the boxes.
Boxes can only be pushed into the warehouse from left to right only.
If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.

Return the maximum number of boxes you can put into the warehouse.

Example 1:
Input: boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
Output: 3
Explanation: 
We can first put the box of height 1 in room 4. Then we can put the box of height 3 in either of the 3 rooms 1, 2, or 3. Lastly, we can put one box of height 4 in room 0.
There is no way we can fit all 4 boxes in the warehouse.

Example 2:
Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
Output: 3
Explanation: 
Notice that it's not possible to put the box of height 4 into the warehouse since it cannot pass the first room of height 3.
Also, for the last two rooms, 2 and 3, only boxes of height 1 can fit.
We can fit 3 boxes maximum as shown above. The yellow box can also be put in room 2 instead.
Swapping the orange and green boxes is also valid, or swapping one of them with the red box.

Example 3:
Input: boxes = [1,2,3], warehouse = [1,2,3,4]
Output: 1
Explanation: Since the first room in the warehouse is of height 1, we can only put boxes of height 1.

Example 4:
Input: boxes = [4,5,6], warehouse = [3,3,3,3,3]
Output: 0

Constraints:
n == warehouse.length
1 <= boxes.length, warehouse.length <= 10^5
1 <= boxes[i], warehouse[i] <= 10^9
*/



/*
    1) brute force   : (blogb) + (b*w)
    2) binary search : (blogb) + (w) + (blogw)
    3) 2-pointer     : (blogb) + (w) + (b+w)        // Add smallest boxes from right to left of warehouse
    4) 2-pointer     : (blogb) + (b+w)              // Add largest boxes from left to right of warehouse
    
    Approach 2, 
        warehouse[i] is set as minVal and boxes are also sorted
        both arrays are in sorted order now
        for each box, do a binary search in warehouse array
    Approach 3:
        boxes sorted in asc order and warehouse sorted in desc order
        since "BOTH" the arrays are sorted, we can clearly use 2-pointer
    Approach 4:
        in prev appraoch, we have changed the original given wareHouse[]
        if we are not allowed to do so, then space = O(warehouse)
        in this approach, space = O(1) and also original array is not changed
        logic: 
            refer: https://leetcode.com/problems/put-boxes-into-the-warehouse-i/solution/
            this is "not intuitive" approach because we first fill maxSize box in left side of wareHouse
            this implementation is opposite to approach 3 
*/


/*
// approach 2: binary search
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        for (int i = 1; i < warehouse.length; i++) {
            warehouse[i] = Math.min(warehouse[i], warehouse[i - 1]);
        }
        int maxBoxes = 0;
        int left, right, mid;
        int prevEnd = warehouse.length;
        
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] > warehouse[0] || prevEnd == 0) {
                break;
            }
            maxBoxes++;
            left = 0;
            right = prevEnd - 1;
            while (left <= right) {
                mid = left + ((right - left) / 2);
                if (warehouse[mid] >= boxes[i]) {                       // main logic
                    left = mid + 1;
                    prevEnd = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return maxBoxes;
    }
}
*/



// approach 3: 2-pointer
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        for (int i = 1; i < warehouse.length; i++) {
            warehouse[i] = Math.min(warehouse[i], warehouse[i - 1]);
        }
        int wareHousePointer = warehouse.length - 1;
        int boxPointer = 0;
        int maxBoxes = 0;
        
        while (boxPointer < boxes.length && wareHousePointer >= 0) {
            if (warehouse[wareHousePointer] >= boxes[boxPointer]) {     // main logic
                boxPointer++;
                wareHousePointer--;
                maxBoxes++;
            } else {
                wareHousePointer--;
            }
        }
        return maxBoxes;
    }
}



/*
// approach 4: 2-pointer
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        int wareHousePointer = 0;
        int boxPointer = boxes.length - 1;
        int maxBoxes = 0;
        int roomH = Integer.MAX_VALUE;
        
        while (boxPointer >= 0 && wareHousePointer < warehouse.length) { 
            roomH = Math.min(roomH, warehouse[wareHousePointer]);
            if (roomH >= boxes[boxPointer]) {     // main logic
                boxPointer--;
                wareHousePointer++;
                maxBoxes++;
            } else {
                boxPointer--;
            }
        }
        return maxBoxes;
    }
}
*/