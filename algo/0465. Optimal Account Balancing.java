/*
You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
Return the minimum number of transactions required to settle the debt.

Example 1:
Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:
Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.

Constraints:
1 <= transactions.length <= 8
transactions[i].length == 3
0 <= fromi, toi <= 20
fromi != toi
1 <= amounti <= 100
*/



/*
    https://leetcode.com/problems/optimal-account-balancing/discuss/95355/Concise-9ms-DFS-solution-(detailed-explanation)
    
    Logic: recursion + backtracking
    Implementation:
        1) pre-process all users cummulative balance
        2) recursively eliminate one user in each recursive call
            a) start is the user to eliminate
            b) start can either pay/get the money from any of the remaining all users
               recursively try above step and finding min transaction
            c) How do we decide who can balance the account of start?
               first try start pay/get from i
               backtrack the process and check start pay/get from i+1, i+2,...till end
               
        either start need to pay, i need to get
        or start need to get and i need to pay
        so, balance.get(start) * balance.get(i) < 0 works --> one +ve, another -ve
        
    time: O(users!), which is exponential, NP complete problem.
    space: O(users)
*/

class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> userBalance = new HashMap<Integer, Integer>();
        for (int[] transaction : transactions) {
            userBalance.put(transaction[0], userBalance.getOrDefault(transaction[0], 0) - transaction[2]);
            userBalance.put(transaction[1], userBalance.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        return minTransfersUtil(0, new ArrayList<Integer>(userBalance.values()));
    }
    
    public int minTransfersUtil(int start, List<Integer> balance) {
        if (start == balance.size()) {
            return 0;
        }
        if (balance.get(start) == 0) {
            return minTransfersUtil(start + 1, balance);
        }
        int minTransaction = Integer.MAX_VALUE;
        int currTransaction;
        
        for (int i = start + 1; i < balance.size(); i++) {                      // try all remaining users
            if (balance.get(start) * balance.get(i) < 0) {                      // one +ve, another -ve
                
                balance.set(i, balance.get(i) + balance.get(start));            // main logic: set(i)
                currTransaction = 1 + minTransfersUtil(start + 1, balance);
                minTransaction = Math.min(minTransaction, currTransaction);
                balance.set(i, balance.get(i) - balance.get(start));            // backtrack
                
                if (balance.get(i) + balance.get(start) == 0) {             
                    break;  // balance got settled with between start and i, so no need to check with other i
                } 
            }
        }
        return minTransaction;
    }
}