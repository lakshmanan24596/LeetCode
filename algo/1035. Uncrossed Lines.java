/*
We write the integers of A and B (in the order they are given) on two separate horizontal lines.
Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
Return the maximum number of connecting lines we can draw in this way.

Example 1:
Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.

Example 2:
Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3

Example 3:
Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2

Note:
1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
*/


/*
    DP problem
    states of DP --> startIndex of A array and startIndex of B array
    
    Ex-2 in ques:
        we can match the initial number 2 (in A array) with any of the future 2 (in B array) or no match also possible
        to search in B array, we use a hashmap for B array alone
    
    Time: m * n
    Space: m * n
*/
class Solution {
    int[] A, B;
    Map<Integer, List<Integer>> bMap;
    Integer[][] DP;
    
    public int maxUncrossedLines(int[] A, int[] B) {
        this.A = A;
        this.B = B;
        DP = new Integer[A.length][B.length];
        bMap = new HashMap<Integer, List<Integer>>();
        List<Integer> bIndexList;
        
        for (int i = 0; i < B.length; i++) {
            bIndexList = bMap.get(B[i]);
            if (bIndexList == null) {
                bIndexList = new ArrayList<Integer>();
                bMap.put(B[i], bIndexList);
            }
            bIndexList.add(i);
        }
        return maxUncrossedLines(0, 0);
    }
    
    public int maxUncrossedLines(int i, int j) {
        if (i >= A.length || j >= B.length) {
            return 0;
        }
        if (DP[i][j] != null) {
            return DP[i][j];
        }
        if (A[i] == B[j]) {
            return DP[i][j] = 1 + maxUncrossedLines(i + 1, j + 1);
        }
        
        int maxLines = Integer.MIN_VALUE;
        int currLines;
        List<Integer> bIndexList = bMap.get(A[i]);
        int newJ;
        
        if (bIndexList != null) {
            for (int index = bIndexList.size() - 1; index >= 0; index--) {
                newJ = bIndexList.get(index);
                if (newJ >= j) {
                    currLines = 1 + maxUncrossedLines(i + 1, newJ + 1);     // match with any j where A[i] == B[j]
                    maxLines = Math.max(maxLines, currLines);
                } else {
                    break;
                }
            }
        }
        currLines = maxUncrossedLines(i + 1, j);                            // no match
        maxLines = Math.max(maxLines, currLines);
        return DP[i][j] = maxLines;
    }
}

    
/*
    DP tabulation (Same as LCS)
    We can also apply space optimization to this solution
*/
/*
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int [m+1][n+1];
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(A[i-1] == B[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);    // main logic
                }
            }
        }           
        return dp[m][n];
    }
}
*/
