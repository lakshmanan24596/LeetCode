/*
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

Note:
The number of given pairs will be in the range [1, 1000].
*/

class Solution
{
    /*
        Logic: Greedy
        Time: O(n*logn) and Space: O(1)
    */
    public int findLongestChain(int[][] pairs) 
    {
        int longestChainLength = 0;
        if(pairs == null || pairs.length == 0) {
            return longestChainLength;
        }
        
        Arrays.sort(pairs, new Comparator<int[]>() {
            public int compare(int a[], int b[]) {
                return a[1] - b[1]; // main logic
            }
        });
        
        int min = Integer.MIN_VALUE;
        for(int[] currPair: pairs) 
        {
            if(min < currPair[0])   // main logic: similar to b < c in DP algo
            {
                longestChainLength++;
                min = currPair[1];
            }
        }
        return longestChainLength;
    }
}

/*
logic: similar to Longest Increasing Subsequence
Time: O(n^2) and Space: O(n) and logic: DP

class Solution 
{
    public int findLongestChain(int[][] pairs) 
    {
        int size = pairs.length;
        int[] DP = new int[size];
        int b, c;
        
        Arrays.sort(pairs, new Comparator<int[]>() {
            public int compare(int a[], int b[]) {
                return (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0];
            }
        });
          
        // for(int i = 0; i < size; i++)
        // {
        //     DP[i] = 1;
        //     for(int j = 0; j < i; j++)
        //     {
        //         b = pairs[j][1];
        //         c = pairs[i][0];
        //         if(b < c && DP[j] + 1 > DP[i]) {
        //             DP[i] = DP[j] + 1;
        //         }
        //     }
        // }
        
        for(int i = 0; i < size; i++)
        {
            DP[i] = 1;
            for(int j = i-1; j >= 0; j--)   // traverse backward
            {
                b = pairs[j][1];
                c = pairs[i][0];
                if(b < c && DP[j] + 1 > DP[i]) {
                    DP[i] = DP[j] + 1;
                    break;                  // main logic to break inner loop
                }
            }
        }
        return DP[size-1];
    }
}
*/