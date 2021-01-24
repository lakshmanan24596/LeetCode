/*
Today, the bookstore owner has a store open for customers.length minutes.  
Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
On some minutes, the bookstore owner is grumpy.  
If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  W
hen the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
Return the maximum number of customers that can be satisfied throughout the day.

Example 1:
Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Note:
1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
*/

/*
    Time: n, Space: 1
    Logic: sliding window
*/
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satis = 0;
        int extraSatis = 0, extraSatisMax = 0;      // sliding windown sum and maxSum
        int i;
        
        for (i = 0; i < X; i++) {                   // create a sliding window of size X
            if (grumpy[i] == 0) {
                satis += customers[i];
            } else {
                extraSatis += customers[i];
            }
        }
        extraSatisMax = Math.max(extraSatisMax, extraSatis);
        
        for(; i < customers.length; i++) {          // i-X is start and i is end
            if (grumpy[i] == 0) {
                satis += customers[i];
            } else {
                extraSatis += customers[i];         // add in sliding window
            }
            if (grumpy[i - X] == 1) {
                extraSatis -= customers[i -X];      // remove in sliding window
            }
            extraSatisMax = Math.max(extraSatisMax, extraSatis);
        }
        return satis + extraSatisMax;
    }
}