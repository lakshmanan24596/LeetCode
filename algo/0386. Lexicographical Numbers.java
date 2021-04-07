/*
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

Example 2:
Input: n = 2
Output: [1,2]

Constraints:
1 <= n <= 5 * 104

Follow up: 
Could you optimize your solution to use O(n) runtime and O(1) space?
*/



/*
    logic: pre order dfs recursion
    time: n
    space: 1 
    stack space: log(n) base 10
*/

class Solution {
    List<Integer> outputList;
    int n;
    
    public List<Integer> lexicalOrder(int n) {
        this.n = n;
        this.outputList = new ArrayList<Integer>();
        
        for (int num = 1; num <= 9; num++) {
            recur(num);
        }
        return outputList;
    }
    
    public void recur(int num) {            // pre-order dfs
        if (num > n) {
            return;
        }
        outputList.add(num);
        
        for (int i = 0; i <= 9; i++) {
            recur((num * 10) + i);          // main logic
        }
    }
}