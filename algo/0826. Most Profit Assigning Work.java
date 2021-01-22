/*
We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 
Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i]. 
Every worker can be assigned at most one job, but one job can be completed multiple times.
For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
What is the most profit we can make?

Example 1:
Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100 
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.

Notes:
1 <= difficulty.length = profit.length <= 10000
1 <= worker.length <= 10000
difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
*/


/*
    Binary search technique:
        1) sort the difficulty
        2) for each worker ability, binary search the difficulty
    Time: O(DlogD + WlogD)
    This can be done also using treemap floorKey() instead of binary search
*/
/*
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int difficultySize = difficulty.length;
        int workerSize = worker.length;
        int[][] diff = new int[difficultySize][2]; 
        
        for (int i = 0; i < difficultySize; i++) {
            diff[i][0] = difficulty[i];
            diff[i][1] = profit[i];
        }
        Arrays.sort(diff, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            } 
        });
        
        int[] maxProfit = new int[difficultySize];
        maxProfit[0] = diff[0][1];
        for(int i = 1; i < difficultySize; i++) {
            maxProfit[i] = Math.max(maxProfit[i-1], diff[i][1]);    // profit till curr index
        }
        
        int output = 0, index;
        int leastDifficulty = diff[0][0];
        for(int ability : worker) {
            if(ability >= leastDifficulty) {
                index = binarySearch(diff, ability);    // main logic: for each worker ability, binary search the difficulty
                output += maxProfit[index];
            }
        }
        return output;
    }
    
    public int binarySearch(int[][] diff, int ability) {    // This can be done also using treemap floorKey()
        int left = 0, right = diff.length - 1, mid;
        while(left < right) {
            mid = (int) Math.ceil((left + right) / 2.0);
            if(ability < diff[mid][0]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;    // returns smallest index which is less than or equal to the given ability.
    }
}
*/


/*
    Logic is exactly same as above solution
    Implemented using treeMap
    Time O(DlogD + WlogD)
*/
/*
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        TreeMap<Integer, Integer> map = new TreeMap<>();    
        map.put(0, 0);
        int currProfit;
        for (int i = 0; i < difficulty.length; i++) {
            currProfit = Math.max(profit[i], map.getOrDefault(difficulty[i], 0));   // bcos there can be duplicate difficulty also
            map.put(difficulty[i], currProfit);             // difficulty, profit
        }
        
        int maxProfit = 0;
        for (Integer currDifficulty : map.keySet()) {
            currProfit = map.get(currDifficulty);
            maxProfit = Math.max(maxProfit, currProfit); 
            map.put(currDifficulty, maxProfit);             // difficulty, maxProfit
        }
        
        int output = 0;
        for (int ability : worker) {
            output += map.floorEntry(ability).getValue();   // main logic
        }
        return output;
    }
}
*/


/*
    2 pointer technique
        Sort difficulty
        Sort worker
        One pointer for worker array and another pointer for difficulty array
    Time: O(DlogD + WlogW + W+D)
*/
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int difficultySize = difficulty.length;
        int workerSize = worker.length;
        int[][] diff = new int[difficultySize][2]; 
        
        for (int i = 0; i < difficultySize; i++) {
            diff[i][0] = difficulty[i];
            diff[i][1] = profit[i];
        }
        Arrays.sort(diff, new Comparator<int[]>() {     // DlogD
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            } 
        });
        Arrays.sort(worker);                            // WlogW
        
        int i = 0, maxProfit = 0, output = 0;
        for (int ability : worker) {                    // W + D
            while(i < difficultySize && ability >= diff[i][0]) {
                maxProfit = Math.max(maxProfit, diff[i][1]);
                i++;
            }
            output += maxProfit;
        }
        return output;
    }
}