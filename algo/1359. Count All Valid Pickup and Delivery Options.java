/*
Given n orders, each order consist in pickup and delivery services. 
Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i). 
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.

Example 2:
Input: n = 2
Output: 6
Explanation: All possible orders: 
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.

Example 3:
Input: n = 3
Output: 90

Constraints:
1 <= n <= 500
*/


/*
    DP, Math
    time: n
    space: 1  (tabulation space optimized)
    
    Explanation:
    https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/discuss/516951/C%2B%2BJava-Simple-Math-Formula-with-Explanation
    
    SpaceNum calculation:
    _P1_D1_             --> 3 space left
    _P1_D1_P2_D2_       --> 5 space left
    _P1_D1_P2_D2_P3_D3_ --> 7 space left
*/

class Solution {
    public int countOrders(int n) {
        long output = 1, currOutput;
        int mod = 1_000_000_007;
        int spaceNum;
        
        for (int i = 2; i <= n; i++) {
            spaceNum = ((2*i) + 1) - 2;                     // spaces available to add current P, D.
            currOutput = (spaceNum * (spaceNum + 1)) / 2;   // sum of natural numbers
            output *= currOutput;                           // DP: use prev result        
            output %= mod;
        }
        return (int) output;
    }
}