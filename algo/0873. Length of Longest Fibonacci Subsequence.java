/*
A sequence X1, X2, ..., Xn is Fibonacci-like if:
n >= 3
Xi + Xi+1 = Xi+2 for all i + 2 <= n
Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest Fibonacci-like subsequence of arr. 
If one does not exist, return 0.
A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr, without changing the order of the remaining elements. 
For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].

Example 1:
Input: arr = [1,2,3,4,5,6,7,8]
Output: 5
Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].

Example 2:
Input: arr = [1,3,7,11,12,14,18]
Output: 3
Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].

Constraints:
3 <= arr.length <= 1000
1 <= arr[i] < arr[i + 1] <= 109
*/


/*
// recursion, Time: 2^n
class Solution 
{
    int[] arr;
    public int lenLongestFibSubseq(int[] arr) 
    {
        this.arr = arr;
        int output = recur(0, 0, 0);
        return (output < 3) ? 0 : output;
    }
    
    public int recur(int level, int first, int second)
    {
        if(level == arr.length) {
            return 0;
        }

        int dontPick = recur(level + 1, first, second);
       
        int pick = 0;
        if(first == 0) {
            pick = 1 + recur(level + 1, arr[level], second);
        }
        else if(second == 0) {
            pick = 1 + recur(level + 1, first, arr[level]);
        }
        else if(arr[level] == first + second) {
            pick = 1 + recur(level + 1, second, arr[level]);
        }
        
        return Math.max(pick, dontPick);
    }
}
*/


/*
    HashSet
    Time : n*n*log(m), where m is largest value in arr
    Space: n
*/
/*
class Solution 
{
    public int lenLongestFibSubseq(int[] arr) 
    {
        Set<Integer> set = new HashSet<Integer>();
        for(int arrElement : arr) {
            set.add(arrElement);
        }
        
        int output = 0, currOutput;
        int first, second, third;
        for(int i = 0; i < arr.length - 2; i++)             // n
        {
            for(int j = i + 1; j < arr.length - 1; j++)     // n
            {
                first = arr[i];
                second = arr[j];
                third = first + second;
                currOutput = 2;
                
                while(set.contains(third)) // log m (there are at most 43 terms in fibonacci series with max value <= 10 ^ 9)
                {
                    first = second;
                    second = third;
                    third = first + second;
                    currOutput++;
                }
                output = Math.max(output, currOutput);
            }
        }
        return (output < 3) ? 0 : output;
    }
}
*/


/*
    2 pointer technique
    time: n^2, space: n^2
    
    similar to Two Sum problem
    if given arr is sorted then 2-pointer technique, else use hashmap
*/
class Solution 
{
    public int lenLongestFibSubseq(int[] arr) 
    {
        int[][] DP = new int[arr.length][arr.length];   // output that ends with second, third is stored
        int left, right, sum, output = 0;
        
        for(int i = 2; i < arr.length; i++)             // (first, second, third) = (left, right, i)
        {
            left = 0;
            right = i - 1;
            
            while(left < right)                         // given array is sorted, so 2-pointer technique works
            {
                sum = arr[left] + arr[right];
                if(sum < arr[i]) {
                    left++;
                }
                else if(sum > arr[i]) {
                    right--;
                }
                else {
                    DP[right][i] = DP[left][right] + 1; // main logic to use the DP array efficiently
                    output = Math.max(output, DP[right][i]);
                    left++;
                    right--;
                }
            }
        }
        return (output == 0) ? 0 : output + 2;
    }
}
