/*
Suppose you have N integers from 1 to N. 
We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2

Explanation: 
The first beautiful arrangement is [1, 2]:
    Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
    Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
The second beautiful arrangement is [2, 1]:
    Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
    Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
*/


/*
reference problems:
    find all permutations: https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
    DP + bitmask: https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/
    
Solutions:
    1) traverse 1 to n with visited array
    2) traverse level to n without visited array
    3) leetcode fastest solution
    4) solution (2) with DP + bitmasking
*/


/*
class Solution 
{
    int N;
    boolean[] visited;
    public int countArrangement(int N) 
    {
        this.N = N;
        this.visited = new boolean[N];
        return recur(1);
    }
    
    public int recur(int level)
    {
        if(level == N + 1) {
            return 1;
        }
        
        int output = 0;
        for(int i = 1; i <= N; i++)     // iterate from 1 to n and have visited array
        {
            if(!visited[i-1] && (i % level == 0 || level % i == 0))
            {
                visited[i-1] = true;
                output += recur(level + 1);
                visited[i-1] = false;
            }
        }
        return output;
    }
}
*/


/*
class Solution 
{
    int N;
    int[] nums;
    
    public int countArrangement(int N) 
    {
        this.N = N;
        this.nums = new int[N];
        for(int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }
        return recur(1);
    }
    
    public int recur(int level)
    {
        if(level == N + 1) {
            return 1;
        }
        
        int output = 0;
        for(int i = level; i <= N; i++)     // iterate from level to n (without visited array)
        {
            if(nums[i-1] % level == 0 || level % nums[i-1] == 0)
            {
                swap(level, i);
                output += recur(level + 1);
                swap(level, i);
            }
        }
        return output;
    }
    
    public void swap(int a, int b)
    {
        int temp = nums[a-1];
        nums[a-1] = nums[b-1];
        nums[b-1] = temp;
    }
}
*/


/*
// Leetcode fastest solution
class Solution {
    private int count = 0;
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private void helper(int[] nums, int start) {
        if (start == 0) {
            count++;
            return;
        }
        for (int i = start; i > 0; i--) {
            swap(nums, start, i);
            if (nums[start] % start == 0 || start % nums[start] == 0) {
                helper(nums, start-1);
            }
            swap(nums,i, start);
        }
    }
    public int countArrangement(int N) {
        if (N == 0) return 0;
        int[] nums = new int[N+1];
        for (int i = 0; i <= N; i++) 
            nums[i] = i;
        helper(nums, N);
        return count;
    }
}
*/


class Solution 
{
    int N;
    int[] nums;
    int allMask;
    Integer[][] DP;
    
    public int countArrangement(int N) 
    {
        this.N = N;
        this.nums = new int[N];
        for(int i = 1; i <= N; i++) {
            nums[i-1] = i;
        }
        allMask = (1 << N) - 1;
        DP = new Integer[N][1 << N];
        return recur(1, 0);
    }
    
    public int recur(int level, int mask)
    {
        if(mask == allMask) {   // or level == N + 1
            return 1;
        }
        if(DP[level-1][mask] != null) {
            return DP[level-1][mask];
        }
        
        int bitVal, nextMask;
        int output = 0;
        for(int i = level; i <= N; i++)     // iterate from level to n (without visited array)
        {
            if(nums[i-1] % level == 0 || level % nums[i-1] == 0)    // if((mask & bitVal) == 0)  condition not needed here
            {
                bitVal = 1 << (nums[i-1] - 1);
                nextMask = bitVal | mask;
                
                swap(level, i);
                output += recur(level + 1, nextMask);
                swap(level, i);
            }
        }
        return DP[level-1][mask] = output;
    }
    
    public void swap(int a, int b)
    {
        int temp = nums[a-1];
        nums[a-1] = nums[b-1];
        nums[b-1] = temp;
    }
}
