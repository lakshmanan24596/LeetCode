/*
You are given an integer array stations that represents the positions of the gas stations on the x-axis. 
You are also given an integer k.

You should add k new gas stations. 
You can add the stations anywhere on the x-axis, and not necessarily on an integer position.

Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.

Example 1:
Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
Output: 0.50000

Example 2:
Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
Output: 14.00000

Constraints:
10 <= stations.length <= 2000
0 <= stations[i] <= 108
stations is sorted in a strictly increasing order.
1 <= k <= 106
*/



/*
    Example: stations = [1, 100], k = 2
         
    approach 1) max heap for delta     
         place 1st station at 50 --> now, [1, 50, 100] and k = 1
         place 2nd station at 25 or 75 --> now [1, 25, 50, 100] and k = 0
         output = 50, which is wrong output

    approach 2) max heap for delta/count
         the correct output is 33.33
         because [1, 33.33, 66.66, 100]
         this is the reason, (delta / count) is used which is = 100/3 = 33.33
     
    Implementation:
        1) logic: maxHeap
            time: nlogn + klogn
            space: n

        2) binary search on answer range
            time: nlogw, where w = 1e14 (which is 1e8 to 1e-6)
            space: 1
            
        note: In binary search, distance could be less than 1.
              So dont do left = mid + 1 or right = mid - 1
              Instead, set left = mid or right = mid
*/


/*
// maxHeap
class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        Double[] peekElem;
        PriorityQueue<Double[]> maxHeap = new PriorityQueue<Double[]>(
            (a, b) -> Double.compare(b[0]/b[1], a[0]/a[1]));                    // main logic: maxHeap for (delta / count)
        
        for (int i = 1; i < n; i++) {
            maxHeap.add(new Double[] {(double)(stations[i] - stations[i - 1]), 1.0});
        }
        while (k-- > 0) {
            peekElem = maxHeap.remove();
            peekElem[1]++;                                                      // add new station
            maxHeap.add(peekElem);
        }
        peekElem = maxHeap.remove();
        return peekElem[0] / peekElem[1];
    }
}
*/


// binary search on answer range
class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0, right = 1e8, mid, output = right; // right can also be stations[last] - stations[last - 1]
        
        while (right - left >= 1e-6) {
            mid = left + ((right - left) / 2); 
            if (isPossible(stations, k, mid)) {
                right = mid;
                output = mid;
            } else {
                left = mid;
            }
        }
        return output;
    }
    
    public boolean isPossible(int[] stations, int k, double answer) {
        int newStations = 0;
        for (int i = 1; i < stations.length; i++) {
            newStations += (int) ((stations[i] - stations[i - 1]) / answer);    // main logic: delta / count
            if (newStations > k) {
                return false;
            }
        }
        return true;
    }
}
