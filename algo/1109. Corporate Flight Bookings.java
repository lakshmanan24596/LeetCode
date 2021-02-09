/*
There are n flights, and they are labeled from 1 to n.
We have a list of flight bookings.  
The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
Return an array answer of length n, representing the number of seats booked on each flight in order of their label.

Example 1:
Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]

Constraints:
1 <= bookings.length <= 20000
1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
1 <= bookings[i][2] <= 10000
*/


/*
    Logic: instead of iterating from start to end for each booking, we just keep track of start and end in an array
    https://leetcode.com/problems/corporate-flight-bookings/discuss/328871/C%2B%2BJava-with-picture-O(n)
    
    Time: n + bookings
    Space: n
*/

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int start, end, seats;
        int[] booking;
        int[] output = new int[n];
        
        for (int i = 0; i < bookings.length; i++) {
            booking = bookings[i];
            start = booking[0] - 1;
            end = booking[1] - 1;
            seats = booking[2];
            
            output[start] += seats;         // main logic
            if (end != n - 1) {
                output[end + 1] -= seats;   // main logic
            }
        }
        
        for (int i = 1; i < n; i++) {
            output[i] += output[i - 1];
        }
        return output;
    }
}