/*
There are N dominoes in a line, and we place each domino vertically upright.
In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left.
Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
Return a string representing the final state. 

Example 1:
Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."

Example 2:
Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Note:
0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'
*/


/*
    'R......R' => 'RRRRRRR'
    'L......L' => 'LLLLLLLL'
    'R......L' => 'RRRRLLLL' or 'RRR.LLL'
    'L......R' => 'L.....R'

    Another approach:
    calculate using force: https://leetcode.com/problems/push-dominoes/solution/
*/
class Solution 
{
    char[] arr;
    public String pushDominoes(String dominoes)                // Time: 2n, Space: n
    {
        int prev = -1, curr = 0;                               // 2 pointer, sliding window
        arr = dominoes.toCharArray();
        
        while(curr < arr.length)                               // find first 'L' or 'R'
        {
            if(prev != -1) {
                break;
            }
            if(arr[curr] == 'L') {
                setSameDir(prev + 1, curr - 1, 'L');
                prev = curr;
            }
            else if(arr[curr] == 'R') {
                prev = curr;
            }
            curr++;
        }
        if(prev == -1) {
            return dominoes;
        }
        
        while(curr < arr.length)
        {
            if(arr[prev] == arr[curr]) {
                setSameDir(prev + 1, curr - 1, arr[curr]);     // same dir
            }
            else if(arr[prev] == 'R' && arr[curr] == 'L') {    // opposite dir
                setOpposite(prev + 1, curr - 1);
            }
            
            if(arr[curr] != '.') {
                prev = curr;
            }
            curr++;
        }
        if(arr[prev] == 'R') {                                  // last prev is 'R' case
            setSameDir(prev + 1, curr - 1, 'R');
        }
        return new String(arr);
    }
    
    public void setSameDir(int start, int end, char dir)
    {
        for(int i = start; i <= end; i++) {
            arr[i] = dir;
        }
    }
    public void setOpposite(int start, int end)
    {
        while(start < end) {
            arr[start++] = 'R';
            arr[end--] = 'L';
        }
    }
}
