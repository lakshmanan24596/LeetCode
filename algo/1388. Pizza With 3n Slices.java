/*
There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:

You will pick any pizza slice.
Your friend Alice will pick next slice in anti clockwise direction of your pick. 
Your friend Bob will pick next slice in clockwise direction of your pick.

Repeat until there are no more slices of pizzas.
Sizes of Pizza slices is represented by circular array slices in clockwise direction.
Return the maximum possible sum of slice sizes which you can have.

Example 1:
Input: slices = [1,2,3,4,5,6]
Output: 10
Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.

Example 2:
Input: slices = [8,9,8,6,1,1]
Output: 16
Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.

Example 3:
Input: slices = [4,1,2,5,8,3,1,9,7]
Output: 21

Example 4:
Input: slices = [3,1,2]
Output: 3

Constraints:
1 <= slices.length <= 500
slices.length % 3 == 0
1 <= slices[i] <= 1000
*/



/*
    Logic:
        the problem is equivalent to: 
            given an integer array with size n, select n/3 integers with maximum sum
            any selected integers should not be adjacent to each other --> so recur (i + 2)
            both first and last cannot be chosen at the same time      --> same as house robber 2
*/
    

/*    
    Try all possibility
    DP memo:
        time: n * n/3 * n
        space: n * n/3
*/
/*
class Solution {
    int[] slices;
    int n, k;
    int[][] DP;
    
    public int maxSizeSlices(int[] slices) {
        this.slices = slices;
        this.n = slices.length;
        this.k = n / 3;
        
        this.DP = new int[n][k];
        int excludeLast = findMaxSizeSlices(0, n - 1, 0);
        DP = new int[n][k];
        int excludeFirst = findMaxSizeSlices(1, n, 0);
        return Math.max(excludeLast, excludeFirst);                                         // main logic (House robber 2) 
    }
    
    public int findMaxSizeSlices(int startIndex, int endIndex, int currK) {
        if (currK == k || startIndex >= endIndex) {
            return 0;
        }
        if (DP[startIndex][currK] > 0) {
            return DP[startIndex][currK];
        }
        int maxSizeSlices = 0;
        int currSizeSlice;
        
        for (int i = startIndex; i < endIndex; i++) {
            currSizeSlice = slices[i] + findMaxSizeSlices(i + 2, endIndex, currK + 1);      // main logic
            maxSizeSlices = Math.max(maxSizeSlices, currSizeSlice);
        }
        return DP[startIndex][currK] = maxSizeSlices;
    }
}


/*
    Instead of trying all possibility, we either pick or dont pick the currIndex
    DP memo:
        time: n * n/3
        space: n * n/3
*/
class Solution {
    int[] slices;
    int n, k;
    int[][] DP;
    
    public int maxSizeSlices(int[] slices) {
        this.slices = slices;
        this.n = slices.length;
        this.k = n / 3;
        
        this.DP = new int[n][k];
        int excludeLast = findMaxSizeSlices(0, n - 1, 0);
        DP = new int[n][k];
        int excludeFirst = findMaxSizeSlices(1, n, 0);
        return Math.max(excludeLast, excludeFirst);                                // main logic (House robber 2) 
    }
    
    public int findMaxSizeSlices(int i, int endIndex, int currK) {
        if (currK == k || i >= endIndex) {
            return 0;
        }
        if (DP[i][currK] > 0) {
            return DP[i][currK];
        }
        
        int pick = slices[i] + findMaxSizeSlices(i + 2, endIndex, currK + 1);
        int dontPick = findMaxSizeSlices(i + 1, endIndex, currK);                                  
        return DP[i][currK] = Math.max(pick, dontPick);                            // main logic (House robber 2) 
    }
}