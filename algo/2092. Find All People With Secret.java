/*
You are given an integer n indicating there are n people numbered from 0 to n - 1. 
You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. 
A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. 
This secret is then shared every time a meeting takes place with a person that has the secret. 
More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. 
That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. 
You may return the answer in any order.


Example 1:
Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.

Example 2:
Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.

Example 3:
Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings. 


Constraints:
2 <= n <= 105
1 <= meetings.length <= 105
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 105
1 <= firstPerson <= n - 1
*/



/*
    Logic: DSU (with the global root as 0)
    Time: mlogm + m + n, where m = meetings and n = no of persons
    space: n
    https://leetcode.com/problems/find-all-people-with-secret/discuss/1599815/C%2B%2B-Union-Find
    
    refer example-3 
    step 1: sort meetings based on meeting time
    step 2: all the meetings happening at the same time need to be a graph
            the persons who are NOT connected with 0, needs to be reset because they dont share the secret in that meeting
    step 3: the persons who are connected with 0 will be the output
*/

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> (a[2] - b[2]));
        List<Integer> output = new ArrayList<Integer>();
        List<Integer> currSetPersons;
        int currMeetingTime;
        int xParent, yParent;
        DSU dsu = new DSU(n);
        dsu.union(0, firstPerson);
        
        for (int i = 0; i < meetings.length; ) {
            currMeetingTime = meetings[i][2];
            currSetPersons = new ArrayList<Integer>();
            
            for (; i < meetings.length && meetings[i][2] == currMeetingTime; i++) {
                xParent = dsu.find(meetings[i][0]);
                yParent = dsu.find(meetings[i][1]);
                if (xParent != yParent) {
                    dsu.union(xParent, yParent);
                }
                currSetPersons.add(meetings[i][0]);
                currSetPersons.add(meetings[i][1]);
            }
            for (int currSetPerson : currSetPersons) {
                if (dsu.find(currSetPerson) != dsu.find(0)) {       // main logic
                    dsu.reset(currSetPerson);                       // main logic
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == dsu.find(0)) {                       // main logic
                output.add(i);
            }
        }
        return output;
    }
}

class DSU {
    int[] parent;
    int[] rank;
    
    DSU(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    
    public void union(int u, int v) {
        if (rank[u] > rank[v]) {
            parent[v] = u;
            rank[u] += rank[v];
        } else {
            parent[u] = v;
            rank[v] += rank[u];
        }
    }
    
    public void reset(int i) {
        parent[i] = i;                                              // main logic
    }
}