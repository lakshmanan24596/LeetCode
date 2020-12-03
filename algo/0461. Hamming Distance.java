/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:
Input: x = 1, y = 4
Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
*/

class Solution 
{
    public int hammingDistance(int x, int y) 
    {
        // we need to find number of position at which the corresponding bits are "different". So use XOR.
        // 1 line solution: return Integer.bitCount(x ^ y);
        
        int xorValue = x ^ y;
        int hammDist = 0;
        
        while(xorValue > 0) 
        {
            hammDist += xorValue & 1;   // find number of set bits in an integer
            xorValue = xorValue >> 1;
        }
        return hammDist;
    }
}
