/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. 
The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
The lock initially starts at '0000', a string representing the state of the 4 wheels.
You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.

Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1

Note:
The length of deadends will be in the range [1, 500].
target will not be in the list deadends.
Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
*/

class Solution 
{
    int start = 0;
    int directions = 8;
    Integer target;
    boolean[] visited = new boolean[10000];                // boolean array or use HashSet
    Queue<Integer> queue = new LinkedList<Integer>();   
    
    // Time --> Math.min(10000, Math.pow(8, output))
    
    public int openLock(String[] deadends, String target) 
    {
        this.target = Integer.parseInt(target);
        for(String curr : deadends)
            visited[Integer.valueOf(curr)] = true;         // copy deadends to visited array
        
        if(visited[start])                                 // if start is present in deadends
            return -1;
        return bfs();
    }
    
    public int bfs()
    {
        int output = 0;     
        int curr = start;
        int next; 
        int queueSize;
        visited[curr] = true;
        queue.add(curr);
        
        while(!queue.isEmpty())
        {
            output++;                                       // number of levels in bfs state space tree is the output
            queueSize = queue.size();
            for(int i = 0; i < queueSize; i++)
            {
                curr = queue.remove();
                for(int j = 1; j <= directions; j++)        // rotate in all 8 possible ways
                {
                    next = rotateLock(curr, j);             
                    if(next == target)
                    {
                        return output;
                    }
                    if(!visited[next])
                    {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }
        return -1;                                          // cannot reach target in any of the possible ways
    }
    
    public int rotateLock(int curr, int index)
    {
        //int size = (int)(Math.log(curr)/Math.log(10)) + 1;
        switch(index)
        {
            // rotate forward
            case 1: return ((curr % 10) == 9)           ? curr - 9      : curr + 1;
            case 2: return (((curr / 10) % 10) == 9)    ? curr - 90     : curr + 10;
            case 3: return (((curr / 100) % 10) == 9)   ? curr - 900    : curr + 100;
            case 4: return (((curr / 1000) % 10) == 9)  ? curr - 9000   : curr + 1000;
            
            // rotate backward
            case 5: return (curr % 10 == 0)             ? curr + 9      : curr - 1;
            case 6: return (((curr / 10) % 10) == 0)    ? curr + 90     : curr - 10;
            case 7: return (((curr / 100) % 10) == 0)   ? curr + 900    : curr - 100;
            case 8: return (((curr / 1000) % 10) == 0)  ? curr + 9000   : curr - 1000;
        } 
        
        return -1;  // wont occur
    }
}