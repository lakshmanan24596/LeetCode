/*
Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  
The width of such a ramp is j - i.
Find the maximum width of a ramp in A.  If one doesn't exist, return 0.

Example 1:
Input: [6,0,8,2,1,5]
Output: 4
Explanation: 
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.

Example 2:
Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: 
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.

Note:
2 <= A.length <= 50000
0 <= A[i] <= 50000
*/


/*
    Time: brute: n^2 time, 1 space
    Binary search: n*logn time, n sapce --> (we can use binary search or treemap or sorting or descending stack)
    Monotonic stack: n time, n space --> (we can use descending stack or min-max array)
    
    Binary search logic:
        ex1: arr    = [6,0,8,2,1,5]
             maxArr = [8,8,8,5,5,5] (iterate from last)
        first val is 6. Now we need to find the j index which has value greater than 6 between i+1 and n-1
        since the maxArr is sorted, we can use binary search
        
    Monotonic stack logic:
        n time, n space
        2 pass algo with monotonically decreasing stack
        pass one: create the stack
        pass two: iterate from last and check peek element in stack

    Min-max array logic:
        create max array by iterating from last     (similar to n*logn binary search approach)
        create min array by iterating from front
        use 2 pointer i, j and check minA[i] <= maxA[j]
*/

/*
// Binary search: n*logn time, n sapce
class Solution
{
    public int maxWidthRamp(int[] arr) 
    {
        int n = arr.length;
        int[] maxArr = new int[n];
        maxArr[n-1] = arr[n-1];
        int max = maxArr[n-1];
        
        for(int i = n-2; i >= 0; i--) 
        {
            max = Math.max(max, arr[i]);
            maxArr[i] = max;
        }
        
        int output = 0, currOutput;
        for(int i = 0; i < n-1; i++)
        {
            int j = binarySearch(arr[i], maxArr, i+1, n-1);     // main logic
            if(arr[i] <= arr[j]) 
            {
                currOutput = j - i;
                output = Math.max(output, currOutput);
            }
        }
        return output;
    }
    
    public int binarySearch(int val, int[] maxArr, int left, int right)
    {
        int mid;
        while(left < right)
        {
            mid = (int)Math.ceil((left + right) / (double)2);
            if(maxArr[mid] >= val) {
                left = mid;             // right
            } else {
                right = mid - 1;        // left
            }
        }
        return left;
    }
}
*/

/*
// n*logn time, n space using TreeMap floorkey
class Solution 
{
    public int maxWidthRamp(int[] A) 
    {
        TreeMap<Integer, Integer> map = new TreeMap<>(); 
        int output = 0;
        
        for(int i = 0; i < A.length; ++i)
        {
            if(map.floorKey(A[i]) == null) {
                map.put(A[i], i);
            }  
            else {
                output = Math.max(output, i - map.get(map.floorKey(A[i])));
            }  
        }
        return output;
    }
}
*/

/*
// 2n time, n space, monotonic stack
class Solution
{
    public int maxWidthRamp(int[] arr) 
    {
        int n = arr.length;
        Stack<Integer> stack = new Stack<Integer>();   // monotonically decreasing stack
        for(int i = 0; i < n; i++) 
        {
            if(stack.isEmpty() || arr[i] < arr[stack.peek()]) {
                stack.push(i);
            }
        }
        
        int output = 0, currOutput;
        for(int i = n - 1; i >= output; i--)           // i >= output because we need max width ramp
        {
            while(!stack.isEmpty() && arr[i] >= arr[stack.peek()]) 
            {
                currOutput = i - stack.pop();          // main logic: we should pop from the stack
                output = Math.max(output, currOutput);
            }
        }
        return output;
    }
}
*/

// 2n time, 2n space
class Solution 
{
    public int maxWidthRamp(int[] A) 
    {
        int n = A.length;
        int[] minA = new int[n];
        int[] maxA = new int[n];
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        
        for(int i = n - 1; i >= 0; i--) {       // create max array by iterating from last (same as previous solution)
            maxNum = Math.max(maxNum, A[i]);
            maxA[i] = maxNum;
        }
        for(int i = 0; i < n; i++) {            // create min array by iterating from front
            minNum = Math.min(minNum, A[i]);
            minA[i] = minNum;
        }

        int j = 0;
        int i = 0;
        int output = 0;
        while (j < n) 
        {
            if (minA[i] <= maxA[j])             // main logic
            {
                output = Math.max(output, j - i);
                j++;
            } 
            else {
                i++;
            }
        }
        return output;
    }
}
