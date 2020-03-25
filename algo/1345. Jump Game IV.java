/*
Given an array of integers arr, you are initially positioned at the first index of the array.
In one step you can jump from index i to index:
    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.
Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:
Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.

Example 3:
Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Example 4:
Input: arr = [6,1,9]
Output: 2

Example 5:
Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3
 
Constraints:
1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8
*/

class Solution 
{
    public int minJumps(int[] arr) 
    {
        if(arr.length == 1)
            return 0;
        
        HashMap<Integer, List<Integer>> map = fillMap(arr);
        return bfs(arr, map);
    }
    
    // we should find minValue.. so do BFS
    // not possible with DFS.. in dfs we need DP to store minVal
 
    public int bfs(int[] arr, HashMap<Integer, List<Integer>> map)
    {
        int curr = 0;                                      // starting position is 0
        int output = 0;
        int next;
        List<Integer> list;
        
        boolean[] visited = new boolean[arr.length];
        visited[curr] = true;        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(curr);
        
        while(!queue.isEmpty())
        {
            output++;
            for(int size = queue.size(); size >= 1; size--) // because queue size will change when we add elements inside the loop
            {
                curr = queue.remove();
                
                // i - 1 
                next = curr - 1;
                if(next >= 0)
                {
                    addToQueue(next, visited, queue);
                }
                
                // i + 1 
                next = curr + 1;
                if(next < arr.length)
                {
                    if(next == arr.length - 1)              // if we reach last index, instead of adding it into queue, return it
                    {
                        return output;
                    }
                    addToQueue(next, visited, queue);
                }
                
                // j
                list = map.get(arr[curr]);
                for(int nextVal : list)
                {
                    if(nextVal == arr.length - 1)           // if target is reached, no need to process remaining elements
                    {
                        return output;
                    }
                    addToQueue(nextVal, visited, queue);
                }
                list.clear();                               // main logic to reduce time.. clear list to avoid duplicate processing
            }
        }
        return 0;
    }
    
    public void addToQueue(int next, boolean[] visited, Queue<Integer> queue)
    {
        if(!visited[next])
        {
             visited[next] = true;
             queue.add(next);
        }
    }
    
    public HashMap<Integer, List<Integer>> fillMap(int[] arr)
    {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();    // key = arrVal, value = list of index
        List<Integer> currList;
        
        for(int i = arr.length - 1; i >= 0; i--)            // iterate from last.. so that we can reach the end soon
        {
            currList = map.get(arr[i]);
            if(currList == null)
            {
                currList = new ArrayList<Integer>();
                map.put(arr[i], currList);
            }
            currList.add(i);
        }
        return map;
    }
}