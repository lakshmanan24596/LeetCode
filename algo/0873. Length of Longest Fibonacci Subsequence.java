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
