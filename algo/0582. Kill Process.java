/*
You have n processes forming a rooted tree structure. 
You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. 
Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. 
You may return the answer in any order.

Example 1:
Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
Output: [5,10]
Explanation: The processes colored in red are the processes that should be killed.

Example 2:
Input: pid = [1], ppid = [0], kill = 1
Output: [1]

Constraints:
n == pid.length
n == ppid.length
1 <= n <= 5 * 104
1 <= pid[i] <= 5 * 104
0 <= ppid[i] <= 5 * 104
Only one process has no parent.
All the values of pid are unique.
kill is guaranteed to be in pid.
*/



/*
    HashMap + DFS
    time: n
    space: n
*/

class Solution {
    Map<Integer, List<Integer>> processVsParent;
    List<Integer> killedProcesses;
    
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        this.processVsParent = new HashMap<Integer, List<Integer>>();
        this.killedProcesses = new ArrayList<Integer>();
        
        createParentMap(pid, ppid);
        killProcess(kill);
        return killedProcesses;
    }
    
    public void createParentMap(List<Integer> pid, List<Integer> ppid) {
        processVsParent.put(0, new ArrayList<Integer>());
        for (int i = 0; i < pid.size(); i++) {
            processVsParent.put(pid.get(i), new ArrayList<Integer>());
        }
        
        for (int i = 0; i < pid.size(); i++) {
            processVsParent.get(ppid.get(i)).add(pid.get(i));
        }
    }
    public void killProcess(int kill) {
        killedProcesses.add(kill);
        List<Integer> children = processVsParent.get(kill);
        
        for (int child : children) {
            killProcess(child);
        }
    }
}