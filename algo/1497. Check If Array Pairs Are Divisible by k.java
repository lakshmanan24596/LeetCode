/*
Given an array of integers arr of even length n and an integer k.
We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
Return True If you can find a way to do that or False otherwise.

Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).

Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.

Example 4:
Input: arr = [-10,10], k = 2
Output: true

Example 5:
Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
Output: true
 
Constraints:
arr.length == n
1 <= n <= 10^5
n is even.
-10^9 <= arr[i] <= 10^9
1 <= k <= 10^5
*/

/*
class Solution 
{
    int[] arr;
    boolean[] visited;
    int n;
    int k;
    int pairCount;
    ArrayList<Integer>[] moduloArr;
    
    public boolean canArrange(int[] arr, int k) 
    {
        this.arr = arr;
        this.n = arr.length;
        this.k = k;
        this.pairCount = 0;
        this.visited = new boolean[n];
        
        moduloArr = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            moduloArr[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if((arr[i] + arr[j]) % k == 0) {
                    moduloArr[i].add(j);
                }
            }
        }
        return backTracking();
    }
    
    public boolean backTracking()   // T(n) = n^2 * T(n-2), which is exponential
    {
        if(pairCount == n/2) {
            return true;
        }
        
        List<Integer> divisibleIndexList;
        for(int i = 0; i < n - 1; i++)
        {
            if(visited[i]) {
                continue;
            }
            
            divisibleIndexList = moduloArr[i];
            for(int j : divisibleIndexList)
            {
                if(visited[j]) {
                    continue;
                }
                
                visited[i] = true;
                visited[j] = true;
                pairCount++;

                if(backTracking()) {
                    return true;
                }

                visited[i] = false;
                visited[j] = false;
                pairCount--;
            }
        }
        return false;
    }
}
*/

class Solution
{
    public boolean canArrange(int[] arr, int k) 
    {
        int n = arr.length;
        int[] remainderFreq = new int[k]; 
        int remainder;
        
        for(int arrVal : arr) 
        {
            remainder = arrVal % k;
            if(remainder < 0) {
        		      remainder += k;     // handle negative number
        	   }
            remainderFreq[remainder]++;
        }
        
        if(remainderFreq[0] % 2 != 0) {    // remainderFreq[0] should be even
            return false;
        }
        for(int i = 1; i <= k/2; i++)   // If k is larger, we can use hashmap and iterate the map, instead of iterating k/2 times
        {
            if(remainderFreq[i] != remainderFreq[k-i]) {    // main logic: for every element with remainder of i, there should be a element with remainder k-i.
                return false;
            }
        }
        return true;
    }
}
