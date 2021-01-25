/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.

Example 3:
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 
Constraints:
1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
*/


/*
    Refer: https://leetcode.com/problems/maximum-length-of-pair-chain/
    above problem is solved greedily by sorting with respect to startTime. (we can also sort with endTime)
    
    Current problem involves 2 constraints:
        1) curr.end <= next.start
        2) max profit
        Both the constraints jointly cannot be achived in greedy and so it needs DP

    Steps to solve:
        1) sort the input based on start time
        2) we can find the nextIndex using binary search by applying curr.start <= next.end
        3) DP[curr] stores the max profit starting at curr index (MAIN LOGIC)
    
    First 2 steps are similar to maximum-length-of-pair-chain
    Additionaly, we use DP to get max profit
    Techniques involed: sort, binarySearch, DP
*/

/*
class Solution {
    Node[] arr;
    int[] DP;
    int n;
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        n = startTime.length;
        DP = new int[n];
        arr = new Node[n];
        for(int i = 0; i < n; i++) {
            arr[i] = new Node(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(arr, (a, b) -> a.startTime - b.startTime);
        
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, recur(i));
        }
        return max;
    }
    
    public int recur(int curr) {   // main logic: DP state --> start with curr index
        if(DP[curr] > 0) {
            return DP[curr];
        }
        
        int max = 0;
        for(int next = binarySearch(curr + 1, n - 1, arr[curr].endTime); next < n; next++) {
            max = Math.max(max, recur(next)); 
        }
        return DP[curr] = arr[curr].profit + max;
    }
    
    public int binarySearch(int left, int right, int val) {
        if(val > arr[right].startTime) {
            return n;
        }
        int mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(val <= arr[mid].startTime) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    class Node {
        int startTime, endTime, profit;
        Node(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
}
*/


/*
    Logic: Exactly same as above solution
    How to remove the loop inside recur() in previous solution?
    Each DP index should store the output till now and return DP[0] finally
    Time: n * logn, Space: n
*/
class Solution {
    Node[] arr;
    int[] DP;
    int n;
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        n = startTime.length;
        DP = new int[n];
        arr = new Node[n];
        for(int i = 0; i < n; i++) {
            arr[i] = new Node(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(arr, (a, b) -> a.startTime - b.startTime);
        
        DP[n - 1] = arr[n - 1].profit;
        for(int i = n - 2; i >= 0; i--) {
            recur(i);
        }
        return DP[0];
    }
    
    public int recur(int curr) {   // main logic: DP state --> start with curr index
        if(curr >= n) {
            return 0;
        }
        if(DP[curr] > 0) {
            return DP[curr];
        }

        int next = binarySearch(curr + 1, n - 1, arr[curr].endTime);
        int currOutput = arr[curr].profit + recur(next);
        return DP[curr] = Math.max(currOutput, DP[curr + 1]);   // difference compared to above solved solution
    }
    
    public int binarySearch(int left, int right, int val) {
        if(val > arr[right].startTime) {
            return n;
        }
        int mid;
        while(left < right) {
            mid = (left + right) / 2;
            if(val <= arr[mid].startTime) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    class Node {
        int startTime, endTime, profit;
        Node(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
}