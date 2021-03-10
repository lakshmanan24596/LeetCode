/*
There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively. 
Return the maximum performance of a team composed of at most k engineers, since the answer can be a huge number, return this modulo 10^9 + 7.
The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers. 

Example 1:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
Output: 60
Explanation: 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.

Example 2:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
Output: 68
Explanation:
This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.

Example 3:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
Output: 72

Constraints:
1 <= n <= 10^5
speed.length == n
efficiency.length == n
1 <= speed[i] <= 10^5
1 <= efficiency[i] <= 10^8
1 <= k <= n
*/



/*
// WRONG SOLUTION: ex: n=3, speed=8,7, efficiency=2,1, k=1 --> output=20 but expected=56

class Solution {
    int mod = 1_000_000_007;
    int[] speed, efficiency;
    int n, k;
    
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        this.n = n;
        this.k = k;
        this.speed = speed;
        this.efficiency = efficiency;
        
        int[] performance = findMaxPerformance(0, 0);
        return (performance[0] * performance[1]);
    }
    
    public int[] findMaxPerformance(int i, int engineers) {         // we can use DP with states: n, k
        if (engineers == k || i == n) {
            return new int[] {0, Integer.MAX_VALUE};
        }
        
        int[] pick = findMaxPerformance(i + 1, engineers + 1);      // pick
        pick[0] += speed[i];
        pick[1] = Math.min(pick[1], efficiency[i]);
        long pickPerf = (pick[0] * pick[1]) % mod;
            
        int[] dontPick = findMaxPerformance(i + 1, engineers);      // dont pick
        long dontPickPerf = (dontPick[0] * dontPick[1]) % mod;
        
        return (pickPerf > dontPickPerf) ? pick : dontPick;
    }
}
*/



/*
    Logic: greedy
    sort and heap
    
    descending sort the engineers based on efficiency --> because we need "max efficiency"
    create a minHeap of size k --> because we need atmost k engineers with "max speed"
    
    time: n*logn + nlogk
    space: n
*/
class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        long mod = 1_000_000_007;
        int[][] engineer = new int[n][2];
        PriorityQueue<Long> speedQueue = new PriorityQueue<Long>();         // min heap for speed of size k
        long currSpeed, currPerf;
        long totalSpeed = 0, maxPerf = 0;
        
        for (int i = 0; i < n; i++) {
            engineer[i] = new int[] {speed[i], efficiency[i]};
        }
        Arrays.sort(engineer, (a, b) -> (b[1] - a[1]));                     // descending order of efficiency
        
        for (int i = 0; i < k; i++) { 
            currSpeed = engineer[i][0];
            totalSpeed += currSpeed;
            speedQueue.add(currSpeed);
            
            currPerf = totalSpeed * engineer[i][1];
            maxPerf = Math.max(maxPerf, currPerf);
        }
        for (int i = k; i < n; i++) {
            currSpeed = engineer[i][0];
            if (currSpeed > speedQueue.peek()) {
                totalSpeed -= speedQueue.remove();                          // remove engineer with lowest speed
                totalSpeed += currSpeed;                                    // replace that engineer with curr engineer
                speedQueue.add(currSpeed);
                
                currPerf = totalSpeed * engineer[i][1];
                maxPerf = Math.max(maxPerf, currPerf);
            }
        }
        return (int) (maxPerf % mod);
    }
}