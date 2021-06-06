/*
You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. A block can only be built by exactly one worker.
A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.

The time cost of spliting one worker into two workers is given as an integer split. 
Note that if two workers split at the same time, they split in parallel so the cost would be split.

Output the minimum time needed to build all blocks.
Initially, there is only one worker.

Example 1:
Input: blocks = [1], split = 1
Output: 1
Explanation: We use 1 worker to build 1 block in 1 time unit.

Example 2:
Input: blocks = [1,2], split = 5
Output: 7
Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.

Example 3:
Input: blocks = [1,2,3], split = 1
Output: 4
Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
Then, use the two unassigned workers to build the first two blocks.
The cost is 1 + max(3, 1 + max(1, 2)) = 4.

Constraints:
1 <= blocks.length <= 1000
1 <= blocks[i] <= 10^5
1 <= split <= 100
*/



/*
    1) DP:
        time: O((n*n)*n)
        space: O(n*n)
        logic:
            ex: blocks = 2000, 1000, 20, 10 and splitCost = 100
                first do split, now 2 workers are there
                now, path1: assign worker1 = 2000,       workers2 = 1000, 20, 10
                     path2: assign worker1 = 2000, 1000, workers2 = 20, 10
                     similarly path3,...
                     output = min of all possible paths
                ie; we can split at any of the possible positions
                which will take O(n) time for each node
        
    2) DP using 0/1 knapsack technique
        time: O((n*n)*2)
        space: O(n*n)
        logic:
            instead of spliting at each index, we can basically do 2 operations:
            operations are: split and noSplit

    3) Greedy, pQueue, huffman encoding
        time: n*logn
        space: n
        logic:
            ex: blocks = 2000, 1000, 20, 10 and splitCost = 100
            queue = 10, 20, 1000, 2000     (initial queue)
                  = 120, 1000, 2000        (newBlock = max(10, 20) + 100 ==> 120)
                  = 1100, 2000             (newBlock = max(120, 1000) + 100 ==> 1100)
                  = 2100                   (newBlock = max(1100, 2000) + 100 ==> 2100)
                  
        huffman encoding: https://www.youtube.com/watch?v=co4_ahEDCho
*/


/*
// DP memo, time: n^3, space: n^2
class Solution {
    int[] blocks;
    int split, n;
    Integer[][] DP;
    
    public int minBuildTime(int[] blocks, int split) {
        Arrays.sort(blocks);                                            // sort the input
        this.blocks = blocks;
        this.split = split;
        this.n = blocks.length;
        this.DP = new Integer[n][n];
        return dfs(0, n - 1);
    }
    
    public int dfs(int start, int end) {
        if (start == end) {
            return blocks[start];
        }
        if (DP[start][end] != null) {
            return DP[start][end];
        }
        int currTime;
        int minTime = Integer.MAX_VALUE;
        
        for (int mid = start; mid < end; mid++) {                       // try to partition at all possible position
            currTime = Math.max(dfs(start, mid), dfs(mid + 1, end));
            minTime = Math.min(minTime, currTime);
        }
        minTime += split;
        return DP[start][end] = minTime;
    }
}
*/


/*
// DP using 0/1 knapsack technique, time: n^2, space: n*2
class Solution {
    int[] blocks;
    int splitCost, n;
    Integer[][] DP;
    
    public int minBuildTime(int[] blocks, int split) {
        Arrays.sort(blocks);                                        // sort the input
        this.blocks = blocks;
        this.splitCost = split;
        this.n = blocks.length;
        this.DP = new Integer[n][n];
        return dfs(n-1, 1);
    }
    
    public int dfs(int i, int workers) {
        if (i < 0) {                                                // all blocks are builded successfully
            return 0;
        }
        if (workers == 0) {                                         // not possible to proceed furthur
            return Integer.MAX_VALUE;
        }
        if (workers >= i + 1) {                                     // we have n workers for n blocks, so no need split
            return blocks[i];
        }
        if (DP[i][workers] != null) {
            return DP[i][workers];
        }
        
        int split   = splitCost + dfs(i, workers * 2);              // A worker can either split into two workers
        int noSplit = Math.max(blocks[i], dfs(i - 1, workers - 1)); // build a block then go home
        return DP[i][workers] = Math.min(split, noSplit);
    }
}
*/


// Greedy, pQueue, huffman encoding, time: n*logn, space: n
class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> blockQueue = new PriorityQueue<Integer>();
        int first, second, newBlock;
        
        for (int block : blocks) {
            blockQueue.add(block);
        }
        while (blockQueue.size() > 1) {
            first = blockQueue.remove();
            second = blockQueue.remove();
            newBlock = Math.max(first, second) + split;             // main logic
            blockQueue.add(newBlock);
        }
        return blockQueue.remove();
    }
}
