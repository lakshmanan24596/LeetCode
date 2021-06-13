/*
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
Now we want to hire exactly K workers to form a paid group.  
When hiring a group of K workers, we must pay them according to the following rules:
Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

Example 1:
Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.

Example 2:
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 

Note:
1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
*/


/*
    exactly similar to --> https://leetcode.com/problems/maximum-performance-of-a-team/
    
    1) brute force --> 2^n * n
    2) greedy + maxHeap --> (n * (n * logk)) --> solution 1 in solutions tab
    3) greedy + sort + maxHeap --> (n * logn) + (n * logk)
    
    logic: 
        cost should be minimum
        At least one worker will be paid their minimum wage expectation
        so calculate "factor" for that one worker 

    factor = wage / quality
        for example 1: factor = 70/10 = 7
        for example 2: factor = 4/3
        
    multiply this factor with the selected K workers quality
        example 1: (7*10) + (7*5) = 105
        example 2: (4/3 * 3) + (4/3 * 10) + (4/3 * 10) = 30.66
    
    Difference between 2 problem:
        problem maximum-performance-of-a-team --> max efficiency, max speed
        problem minimum-cost-to-hire-k-workers --> min factor, min quanlity
*/

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int noOfWorkers = quality.length;
        double[][] workers = new double[noOfWorkers][2];
        double factor;
        // max heap for quality to find k minimum quality
        PriorityQueue<Double> qualityQueue = new PriorityQueue<Double>((a, b) -> (a > b ? -1 : 1)); 
        double minCost = Double.MAX_VALUE; 
        double currCost;
        int totalQuality = 0;
        
        for (int i = 0; i < noOfWorkers; i++) {
            factor = ((double) wage[i]) / quality[i];               // main logic
            workers[i] = new double[] {((double) quality[i]), factor};
        }
        Arrays.sort(workers, (a, b) -> (a[1] > b[1] ? 1 : -1));     // ascending order sort based on factor
        
        for (int i = 0; i < noOfWorkers; i++) {
            qualityQueue.add(workers[i][0]);
            totalQuality += workers[i][0];
                
            if (qualityQueue.size() > K) {
                totalQuality -= qualityQueue.remove();
            }
            if (qualityQueue.size() == K) {
                factor = workers[i][1];
                currCost = totalQuality * factor;                   // main logic
                minCost = Math.min(minCost, currCost);
            }
        }
        return minCost;
    }
}