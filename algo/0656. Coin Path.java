/*
You are given an integer array coins (1-indexed) of length n and an integer maxJump. 
You can jump to any index i of the array coins if coins[i] != -1 and you have to pay coins[i] when you visit index i. 
In addition to that, if you are currently at index i, you can only jump to any index i + k where i + k <= n and k is a value in the range [1, maxJump].

You are initially positioned at index 1 (coins[1] is not -1). You want to find the path that reaches index n with the minimum cost.
Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. 
If there are multiple paths with the same cost, return the lexicographically smallest such path. 
If it is not possible to reach index n, return an empty array.

A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of length y, 
if and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no such j exists, then x < y.

Example 1:
Input: coins = [1,2,4,-1,2], maxJump = 2
Output: [1,3,5]

Example 2:
Input: coins = [1,2,4,-1,2], maxJump = 1
Output: []

Constraints:
1 <= coins.length <= 1000
-1 <= coins[i] <= 100
coins[1] != -1
1 <= maxJump <= 100
*/



/*
    this problem is similar to jumpGame
    but additionally, block (-1) is present

    DP memo
    time: n * maxJump
    space: n
    
    Implementation:
        1) at each node, return minDist, list --> this is costly because of creating list each time in recursion
        2) at each node, return minDist alone and track output in path[]
        
    We can also try with Dikjtra's
    Similar to problem 1548. The Most Similar Path in a Graph.java
    use minHeap as we need minCost
*/


/*
class Solution {
    int[] coins;
    int maxJump;
    PathInfo[] DP;
    
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        this.coins = coins;
        this.maxJump = maxJump;
        this.DP = new PathInfo[coins.length];
        
        int lastIndexCost = coins[coins.length - 1];
        List<Integer> lastIndexPath = new ArrayList<Integer>();
        lastIndexPath.add(coins.length);
        DP[coins.length - 1] = new PathInfo(lastIndexCost, lastIndexPath);  // base case
        return dfs(0).path;
    }
    
    public PathInfo dfs(int coinIndex) {
        if (DP[coinIndex] != null) {
            return DP[coinIndex];
        }
        int nextIndex;
        PathInfo currOutput;
        PathInfo output = new PathInfo(Integer.MAX_VALUE, new ArrayList<Integer>());

        for (int i = 1; i <= maxJump; i++) {
            nextIndex = coinIndex + i;
            if (nextIndex >= coins.length) {
                break;
            }
            if (coins[nextIndex] != -1) {
                currOutput = dfs(nextIndex);
                if (currOutput.cost < output.cost) {
                    output = currOutput;
                }
            }
        }
        if (output.cost == Integer.MAX_VALUE) {
            return output;
        }
        int minCost = output.cost + coins[coinIndex];
        List<Integer> outputPath = new ArrayList<Integer>();
        outputPath.add(coinIndex + 1);
        outputPath.addAll(output.path);
        return DP[coinIndex] = new PathInfo(minCost, outputPath);
    }
}

class PathInfo {
    int cost;
    List<Integer> path;
    
    PathInfo(int cost, List<Integer> path) {
        this.cost = cost;
        this.path = path;
    }
}
*/


class Solution {
    int[] coins, path;
    int maxJump;
    Long[] DP;
    int n;
    
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        List<Integer> output = new ArrayList<Integer>();
        this.n = coins.length;
        if (coins[n - 1] == -1) {
            return output; 
        }
        this.coins = coins;
        this.maxJump = maxJump;
        this.path = new int[n];
        this.DP = new Long[n];
        DP[n - 1] = (long) coins[n - 1];                    // base case
        Arrays.fill(path, -1);
        
        long minCost = dfs(0);                              // dfs
        if (minCost != Long.MAX_VALUE) {
            int index = 0;
            while (index != -1) {
                output.add(index + 1);
                index = path[index];                        // traverse path
            }
        }
        return output;
    }
    
    public long dfs(int coinIndex) {
        if (DP[coinIndex] != null) {
            return DP[coinIndex];
        }
        int nextIndex;
        long currCost, minCost = Long.MAX_VALUE;

        for (int i = 1; i <= maxJump; i++) {
            nextIndex = coinIndex + i;
            if (nextIndex >= coins.length) {
                break;
            }
            if (coins[nextIndex] != -1) {
                currCost = dfs(nextIndex);                  // dfs
                if (currCost < minCost) {
                    minCost = currCost;
                    path[coinIndex] = nextIndex;            // track path
                }
            }
        }
        if (minCost != Long.MAX_VALUE) {
            minCost += coins[coinIndex];
        }
        return DP[coinIndex] = minCost;
    }
}