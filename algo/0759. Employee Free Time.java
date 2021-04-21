/*
We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. 
For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]

Constraints:
1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
*/



/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/


/*
    Logic: greedy, sorting
    time: c*logc, where c = number of intervals across all employees
    space: c
*/
/*
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> freeTime = new ArrayList<Interval>();
        List<Interval> workTime = new ArrayList<Interval>();
        
        for (List<Interval> currEmpSchedule : schedule) {
            for (Interval currInterval : currEmpSchedule) {
                workTime.add(currInterval);
            }
        }
        Collections.sort(workTime, (a, b) -> (a.start - b.start));
        
        int prevEnd = workTime.get(0).end;
        int currStart, currEnd;
        
        for (int i = 1; i < workTime.size(); i++) {
            currStart = workTime.get(i).start;
            currEnd = workTime.get(i).end;
            
            if (currStart > prevEnd) {                                  // main logic
                freeTime.add(new Interval(prevEnd, currStart));         // main logic
            }
            prevEnd = Math.max(prevEnd, currEnd);
        }
        return freeTime;
    }
}
*/


/*
    Logic: greedy, minHeap
    time: c*logn
    space: n
    
    where c = number of intervals across all employees
          n = number of employees

    Logic:
        In previous approach we sorted intervals of all employees
        But the ques is, each employee interval is already sorted, we dont need to do it again
        So we can make use of priorityQueue and add 1st interval alone of all employees
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> freeTime = new ArrayList<Interval>();
        PriorityQueue<Job> workTime = new PriorityQueue<Job>((a, b) -> 
                                                            (schedule.get(a.empIndex).get(a.intervalIndex).start - 
                                                             schedule.get(b.empIndex).get(b.intervalIndex).start));
        
        for (int i = 0; i < schedule.size(); i++) {
            workTime.add(new Job(i, 0));                                        // add first workTime of all employees
        }
        
        int prevEnd = Integer.MIN_VALUE;
        Job currJob;
        Interval currInterval;
        
        while (!workTime.isEmpty()) {
            currJob = workTime.remove();
            currInterval = schedule.get(currJob.empIndex).get(currJob.intervalIndex);
            
            if (prevEnd != Integer.MIN_VALUE && currInterval.start > prevEnd) {     // main logic
                freeTime.add(new Interval(prevEnd, currInterval.start));            // main logic
            }
            prevEnd = Math.max(prevEnd, currInterval.end);
            
            if (currJob.intervalIndex + 1 < schedule.get(currJob.empIndex).size()) {
                workTime.add(new Job(currJob.empIndex, currJob.intervalIndex + 1)); // add next workTime of same employee
            }
        }
        return freeTime;
    }
    
    class Job {
        int empIndex;
        int intervalIndex;
        
        Job(int empIndex, int intervalIndex) {
            this.empIndex = empIndex;
            this.intervalIndex = intervalIndex;
        }
    }
}