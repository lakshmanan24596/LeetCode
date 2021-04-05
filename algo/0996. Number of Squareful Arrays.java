/*
Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
Return the number of permutations of A that are squareful.  
Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

Example 1:
Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.

Example 2:
Input: [2,2,2]
Output: 1

Note:
1 <= A.length <= 12
0 <= A[i] <= 1e9
*/



/*
    1) brute force
        time: n! * n * logn
    
    2) DP + bitmask + graph adjList + perfectSquare
        time: pre process: O(n^2 * logn), main process: O((2^n * n) * n)
        space: O(2^n * n)
*/

class Solution {
    List<Integer>[] adjList;
    Integer[][] DP;
    int n;
    
    public int numSquarefulPerms(int[] arr) {
        this.n = arr.length;
        this.adjList = new ArrayList[n];
        DP = new Integer[n][1 << n];
        
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPerfectSquare(arr[i] + arr[j])) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }
        int numSquares = 0;
        for (int i = 0; i < n; i++) {
            numSquares += findNumSquarefulPerms(i, 1 << i);
        }
        numSquares = eliminateDuplicatePermutation(arr, numSquares);
        return numSquares;
    }
    
    public int findNumSquarefulPerms(int currIndex, int visitedMask) {
        if (visitedMask == ((1 << n) - 1)) {
            return 1;
        }
        if (DP[currIndex][visitedMask] != null) {
            return DP[currIndex][visitedMask];
        }
        int numSquares = 0;
        List<Integer> neighbours = adjList[currIndex];
        
        for (int neigh : neighbours) {
            if ((visitedMask & (1 << neigh)) == 0) {
                numSquares += findNumSquarefulPerms(neigh, visitedMask | (1 << neigh));     // main logic
            }
        }
        return DP[currIndex][visitedMask] = numSquares;
    }
    
    public boolean isPerfectSquare(int num) {
        double sqrt = Math.sqrt(num);
        return Math.floor(sqrt) == sqrt;
    }
    
    public int eliminateDuplicatePermutation(int[] arr, int numSquares) {   // corner case (example 2)
        for (int i = 0; i < n; i++) {
            int k = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    k++;
                }
            }
            numSquares /= k;
        }
        return numSquares;
    }
}