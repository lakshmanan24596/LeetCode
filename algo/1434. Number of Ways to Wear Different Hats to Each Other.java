/*
There are n people and 40 types of hats labeled from 1 to 40.
Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.
Return the number of ways that the n people wear different hats to each other.
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions. 
First person choose hat 3, Second person choose hat 4 and last one hat 5.

Example 2:
Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats
(3,5), (5,3), (1,3) and (1,5)

Example 3:
Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.

Example 4:
Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
Output: 111
 
Constraints:
n == hats.length
1 <= n <= 10
1 <= hats[i].length <= 40
1 <= hats[i][j] <= 40
hats[i] contains a list of unique integers.
*/


/*
    Bitmask:
        1) create bitValue ==> XOR
        2) check whether a bit is set or not ==> AND
        3) set a bit ==> OR
*/

/*
// brute force --> number of levels = n and each level work done = hat. So Time = O(hat ^ n)
// instead of hashset, bitmask is used here
// refer problem 318 for implementation of bitmask

class Solution 
{
    List<List<Integer>> hats;
    int numberOfPeople;
    
    public int numberWays(List<List<Integer>> hats) 
    {
        this.hats = hats;
        this.numberOfPeople = hats.size();
        return recur(0, 0);
    }
    
    public int recur(int level, int mask)
    {
        if(level == numberOfPeople) {
            return 1;
        }
        
        int output = 0;
        int bitVal;
        int nextMask;
        List<Integer> currPersonHatList = hats.get(level);
        
        for(int hat : currPersonHatList)
        {
            bitVal = 1 << hat-1;
            if((mask & bitVal) == 0)     // check if curr hat is not chosen by anyone else
            {
                nextMask = mask | bitVal;
                output += recur(level + 1, nextMask);
            }
        }
        return output;
    }
}
*/


/*
// DP - assign people with hats. Create a mask for hats. Since 40 hats can be there, space required for storing mask is 2^40 which exceeds memory

class Solution 
{
    List<List<Integer>> hats;
    int numberOfPeople;
    Integer[][] DP;
    final int mod = 1000000007;  // 10^9 + 7
    
    public int numberWays(List<List<Integer>> hats) 
    {
        this.hats = hats;
        this.numberOfPeople = hats.size();
        int totalHats = 40;
        this.DP = new Integer[numberOfPeople][(int)Math.pow(2, totalHats)];     // [10][2^40]
        return recur(0, 0);
    }
    
    public int recur(int level, int mask)   // level -> people and mask -> hat
    {
        if(level == numberOfPeople) {
            return 1;
        }
        if(DP[level][mask] != null) {
            return DP[level][mask];
        }
        
        long output = 0;
        int bitVal;
        int nextMask;
        List<Integer> currPersonHatList = hats.get(level);
        
        for(int currHat : currPersonHatList)
        {
            bitVal = 1 << (currHat - 1);
            if((mask & bitVal) == 0)     // check if currHat is not chosen by any person
            {
                nextMask = mask | bitVal;
                output += recur(level + 1, nextMask);
                output %= mod;
            }
        }
        return DP[level][mask] = output;
    }
}
*/


// DP - assign hats to people. Create a mask for people. Since 10 people can be there, space required for storing mask is 2^10 
class Solution
{
    List<List<Integer>> hatsToPeopleList;
    int n;
    int allMask;
    int totalHats;
    Integer[][] DP;
    final int mod = 1000000007;                             // 10^9 + 7
    
    public int numberWays(List<List<Integer>> hats) 
    {
        n = hats.size();
        allMask = (1 << n) - 1;
        totalHats = 40;
        DP = new Integer[totalHats][(int)Math.pow(2, n)];   // [40][2^10]
        hatsToPeopleList = convertList(hats);               // new list to assign hats to people
        return recur(0, 0);
    }
    
    public List<List<Integer>> convertList(List<List<Integer>> hats)
    {
        hatsToPeopleList = new ArrayList<List<Integer>>();
        for(int i = 0; i < totalHats; i++) {
            hatsToPeopleList.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < hats.size(); i++)
        {
            List<Integer> currPersonHats = hats.get(i);
            for(int currHat : currPersonHats) {
                hatsToPeopleList.get(currHat - 1).add(i+1);
            }
        }
        return hatsToPeopleList;
    }
    
    public int recur(int level, int mask)    // level -> hat and mask -> people
    {
        if(mask == allMask) {                // all persons wears a hat
            return 1;
        }
        if(level >= totalHats) {             // no more hats to process
            return 0;
        }
        if(DP[level][mask] != null) {
            return DP[level][mask];
        }
        
        long output = 0;
        int bitVal;
        int nextMask;
        List<Integer> currHatPersonsList = hatsToPeopleList.get(level);
        
        output += recur(level + 1, mask);     // no one wears this hat
        output %= mod;
        
        for(int person : currHatPersonsList)  // any person can wear this hat
        {
            bitVal = 1 << (person - 1);
            if((mask & bitVal) == 0)          // check if currPerson haven't chosen this hat
            {
                nextMask = mask | bitVal;
                output += recur(level + 1, nextMask);
                output %= mod;
            }
        }
        return DP[level][mask] = (int)output;
    }
}
