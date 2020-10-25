/*
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
You have to form a team of 3 soldiers amongst them under the following rules:
Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

Example 1:
Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 

Example 2:
Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.

Example 3:
Input: rating = [1,2,3,4]
Output: 4
 
Constraints:
n == rating.length
1 <= n <= 200
1 <= rating[i] <= 10^5
*/

class Solution 
{
    /*
        input    = 9, 3, 4, 8, 5, 6
    
        leftMin  = -, 0, 1, 2, 2, -     // ascending (rating[i] < rating[j] < rating[k])
        rightMax = -, 4, 3, 0, 1, -
        
        leftMax  = -, 1, 1, 1, 2, -     // descending (rating[i] > rating[j] > rating[k])
        rightMin = -, 0, 0, 2, 0, -
        
        output = (leftMin * rightMax) + (leftMax * rightMin)
               = 3 + 2 + 2
               = 7
    */
    
    public int numTeams(int[] arr)  // Time: O(n^2) and Space: 1
    {
        int count = 0;
        int len = arr.length;
        int leftMin, rightMax, leftMax, rightMin;
        
        for (int j = 1; j < len-1; j++)     // skip first and last index
        {
            leftMin = 0;
            leftMax = 0;
            for (int i = 0; i < j; i++)     // ascending
            {
                if (arr[i] < arr[j]) {
                    leftMin++;
                } else if (arr[i] > arr[j]) {
                    leftMax++;
                }
            }
            
            rightMax = 0;
            rightMin = 0;
            for (int k = j + 1; k < len; k++)   // descending
            {
                if (arr[j] < arr[k]) {
                    rightMax++;
                } else if (arr[j] > arr[k]) {
                    rightMin++;
                }
            }
            count += (leftMin * rightMax) + (leftMax * rightMin);
        }
        return count;
    }
    
    /*
        Time: O(n*logn) and Space: O(n)
        Using BBST (AVL) tree
            Solution: 1) Note 3 - find number of elements < given element using AVL tree with leftSize property (AVL insertion Note 2)
                      2) https://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
    */
}
