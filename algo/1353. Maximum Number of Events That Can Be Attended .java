/*
Given an array of events where events[i] = [startDayi, endDayi]. 
Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. 
Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.

Example 1:
Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

Example 2:
Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4

Example 3:
Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
Output: 4

Example 4:
Input: events = [[1,100000]]
Output: 1

Example 5:
Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
Output: 7

Constraints:
1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
*/



/*
    Logic:
        sort + pQueue
        time: n*logn
        space: n
        similar to Meeting rooms 2 problem
        
    Corner cases:
        [[6,9],[6,10],[7,7]] ==> output = 3
        [[1,10],[2,2],[2,2],[2,2]] ==> output = 2 
        [[1,3],[2,3],[3,3],[3,4],[3,4]] ==> output = 4
        
    logic: "assign a particular day to a event"
    https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/510263/JavaC%2B%2BPython-Priority-Queue
    
    Consider [[6,9],[6,10],[7,7]] ==> output = 3
        1) initially day 6 can be assigned to either (6,9) or (6,10)
            assign day 6 to (6,9) and increment currDay as 7
        2) day 7 can be assinged to either (6,10) or (7,7)
            assign day 7 to (7,7) and increment currDay as 8
        3) day 8 can be assigned to (6, 10)
        
        
    Segment tree logic:
        https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/718239/Thinking-Process%3A-Greedy-Segment-Tree
*/

class Solution {
    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (e1, e2) -> ((e1[0] == e2[0]) ? e1[1] - e2[1] : e1[0] - e2[0]));    // main logic
        PriorityQueue<Integer> remainingEvents = new PriorityQueue<Integer>();
        int currDay = 0;
        int maxEvents = 0;
        int i = 0;
        
        while (i < n || !remainingEvents.isEmpty()) {
            if (remainingEvents.isEmpty()) {
                currDay = events[i][0];
            }
            while (i < n && events[i][0] == currDay) {      // main logic: same start time
                remainingEvents.add(events[i][1]);
                i++;
            }
            remainingEvents.remove();                       // out of multiple same start time, pick min end time
            currDay++;
            maxEvents++;
            
            while (!remainingEvents.isEmpty() && remainingEvents.peek() < currDay) {
                remainingEvents.remove();
            }
        }
        return maxEvents;
    }
}
