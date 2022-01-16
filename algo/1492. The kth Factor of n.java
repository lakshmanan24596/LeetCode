/*
You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.
Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.

Example 1:
Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.

Example 2:
Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2nd factor is 7.

Example 3:
Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.

Constraints:
1 <= k <= n <= 1000
*/



/*
    logic: brute force
    time: n
    space: 1
*/
/*
class Solution {
    public int kthFactor(int n, int k) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                k--;
                if (k == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
}
*/


/*
    logic: The divisors are paired
            i.e if i is a divisor of N, then N/i is also a divisor of N
            ex: if 2 is a divisor of 12, then 6 is also a divisor of 12
            That means it's enough to iterate up to sqrt(n) 
    time: sqrt(n)
    space: sqrt(n)
*/
class Solution {
    public int kthFactor(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        List<Integer> divisors = new ArrayList();
        
        for (int i = 1; i <= sqrtN; i++) {
            if (n % i == 0) {           // i is a divisor of N
                k--;
                if (k == 0) {
                    return i;
                }
                divisors.add(n / i);    // if i is a divisor of N, then N/i is also a divisor of N
            }
        }
        if (sqrtN * sqrtN == n) {       // corner case for perfect square: example 3
            k++;
        }
        if (k > divisors.size()) {      // when k is larger: example 3
            return -1;
        }
        return divisors.get(divisors.size() - k);
    }
}