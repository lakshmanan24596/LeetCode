/*
Count the number of prime numbers less than a non-negative number, n.

Example:
Input: 10
Output: 4

Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

class Solution 
{
    public int countPrimes(int n) 
    {
        // brute --> O(n^2)
        // Sieve of Eratosthenes --> O(n log log n) 
        
        boolean[] prime = new boolean[n]; // 0 to N-1 (because ques is --> less than N)
        for(int i = 2; i < n; i++) {
            prime[i] = true;
        }
        
        double sqrtN = Math.sqrt(n);
        for(int i = 2; i < sqrtN; i++) { // main logic: i < sqrt(n)
            if(prime[i]) {
                for(int j = i * i; j < n; j += i) {
                    prime[j] = false;
                }
            }
        }
        
        int output = 0;
        for(int i = 2; i < n; i++) {
            if(prime[i]) {
                output++;
            }
        }
        return output;
    }
}
