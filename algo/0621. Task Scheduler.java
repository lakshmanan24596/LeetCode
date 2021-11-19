/*
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. 
Tasks could be done in any order. Each task is done in one unit of time. 
For each unit of time, the CPU could complete either one task or just be idle.
However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:
Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.

Example 3:
Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 
Constraints:
1 <= task.length <= 104
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].
*/



/*
    logic: Greedy + maxHeap --> complete the most occuring task first
    similar ques: 
        https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
        https://leetcode.com/problems/reorganize-string/
*/
/*
class Solution 
{
    public int leastInterval(char[] tasks, int coolDownTime) 
    {
        int taskCount = tasks.length;
        int outputArrSize = taskCount + ((taskCount - 1) * coolDownTime);   // output arr size in worst case
        Character[] outputArr = new Character[outputArrSize];
        int firstFreeIndex = 0;
        PriorityQueue<Node> pq = formMaxHeap(tasks);        
        
        int[] taskLastIndex = new int[26]; // tracks last index of each task
        for(int i = 0; i < 26; i++) {
            taskLastIndex[i] = -1;
        }
        
        while(!pq.isEmpty())
        {
            Node currNode = pq.poll();
            char currTask = currNode.ch;
            int lastIndex = taskLastIndex[currTask - 'A'];
            
            int outputIndex = (lastIndex == -1) ? firstFreeIndex : lastIndex + coolDownTime + 1;    // main logic
            while(outputArr[outputIndex] != null) {
                outputIndex++;
            }
            
            outputArr[outputIndex] = currTask;
            taskLastIndex[currTask - 'A'] = outputIndex;
            
            if(lastIndex == -1) {
                firstFreeIndex = outputIndex + 1;
            }
            
            if(--currNode.count > 0) {
                pq.add(currNode);
            }
        }
            
        for(int i = outputArrSize - 1; i >= 0; i--)
        {
            if(outputArr[i] != null) {  // index of last completed task is the output
                return i+1;
            }
        }
        return 0;  // invalid
    }
    
    public PriorityQueue<Node> formMaxHeap(char[] tasks)
    {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < tasks.length; i++)
        {
            char currTask = tasks[i];
            map.put(currTask, map.getOrDefault(currTask, 0) + 1);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return b.count - a.count;   // max heap (descending order)
            }
        });
        for(Map.Entry<Character, Integer> entry : map.entrySet()) 
        {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        return pq;
    }
    
    class Node
    {
        char ch;
        int count;
        Node(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}
*/
    

/*
    refer approach 1 video in:
    https://leetcode.com/problems/task-scheduler/solution/
    
    time: n
    space: n
    logic: math, greedy
*/
class Solution 
{
    public int leastInterval(char[] tasks, int coolingPeriod)  // Time: N and Space: 26
    {    
        int[] frequencies = new int[26];
        for(int i = 0; i<tasks.length; i++){
            frequencies[tasks[i]-'A']++; 
        }
        
        int maxFrequency = 0; 
        for(int currFrequency: frequencies) {  
            maxFrequency = Math.max(currFrequency, maxFrequency);
        }
        
        int numOfMaxFrequency = 0;
        for(int currFrequency : frequencies) {     
            if(currFrequency == maxFrequency) {
                numOfMaxFrequency++;
            }
        }
        
        int interval = ((maxFrequency - 1) * (coolingPeriod + 1)) + numOfMaxFrequency;    
        return Math.max(tasks.length, interval);
    }
}