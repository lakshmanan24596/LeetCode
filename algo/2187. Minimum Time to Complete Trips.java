/*
You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. 
Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. 
Return the minimum time required for all buses to complete at least totalTrips trips.


Example 1:
Input: time = [1,2,3], totalTrips = 5
Output: 3
Explanation:
- At time t = 1, the number of trips completed by each bus are [1,0,0]. 
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0]. 
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1]. 
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.

Example 2:
Input: time = [2], totalTrips = 1
Output: 2
Explanation:
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.

Constraints:
1 <= time.length <= 105
1 <= time[i], totalTrips <= 107
*/


/*
    1) Binary search on answer range
    time: n * log(totalTrips * maxValue)
    space: 1
    
    2) Math
    time: n
    space: 1
    logic: formula, output * (1/t1 + 1/t2 + .... + 1/tn) = totalTrips
           so,      output = totalTrips / (1/t1 + 1/t2 + .... + 1/tn)
                    output = 5 / (1/1 + 1/2 + 1/3)
                    output = 5 / 1.83
                    output = 2.73 
                    output = 3 after taking ceil
    But this wont work because of the decimal places and ceil
*/

class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        long max = 0;
        for (int t : time) {
            max = Math.max(max, t);
        }
        
        long left = 1;
        long right = max * totalTrips;
        long mid;
        long output = right;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (isPossibleToCompleteTrip(time, totalTrips, mid)) {
                output = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return output;
    }
    
    public boolean isPossibleToCompleteTrip(int[] time, int totalTrips, long currTime) {
        long trips = 0;
        for (int i = 0; i < time.length; i++) {
            trips += currTime / time[i];    // main logic
            if (trips >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}
