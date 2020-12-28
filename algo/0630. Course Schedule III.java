/*
There are n different online courses numbered from 1 to n. 
Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. 
You will start at the 1st day.
Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

Example:
Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3

Explanation: 
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 
Note:
The integer 1 <= d, t, n <= 10,000.
You can't take two courses simultaneously.
*/



/*
    Time: n * d, Space: n * d, where d is maxDay
    Memorization similar to 0/1 knapsack
*/
/*
class Solution 
{
    Integer[][] DP;
    int[][] courses;
    
    public int scheduleCourse(int[][] courses) 
    {
        this.courses = courses;
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] course1, int[] course2) {
                return course1[1] - course2[1];                             // sort based on day
            }
        });
        
        int maxDay = 0;
        for(int i = 0; i < courses.length; i++) {
            maxDay = Math.max(maxDay, courses[i][1]);
        }
        DP = new Integer[courses.length][maxDay+1];
        return recur(0, 0);
    }
    
    public int recur(int level, int time)
    {
        if(level == courses.length) {
            return 0;
        }
        if(DP[level][time] != null) {
            return DP[level][time];
        }
        
        int courseNotTaken = recur(level + 1, time);
        int courseTaken = 0;
        if(time + courses[level][0] <= courses[level][1]) {                 // main logic
            courseTaken = 1 + recur(level + 1, time + courses[level][0]);
        }
        
        return DP[level][time] = Math.max(courseNotTaken, courseTaken);
    }    
}
*/


/*
    Logic: greedy
    Time: n^2, Space: n
    From amongst the taken courses, look for a course having a larger duration than the current course.
    Then replace max duration course and curr course.
    But why are we doing this replacement? The answer to this question is as follows. 
    By replacing the j course, with the i course of a relatively smaller duration, we can increase the time available for upcoming courses to be taken. 
    An extra duration_j - duration_i time can be made available by doing so.
*/
/*
class Solution
{
    public int scheduleCourse(int[][] courses) 
    {
        int n = courses.length;
        int output = 0;
        boolean[] isCourseTaken = new boolean[n];
        int currTime = 0, savedTimeForFutureCourses = 0;
        
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] course1, int[] course2) {
                return course1[1] - course2[1];                             // sort based on day
            }
        });
        
        for(int i = 0; i < n; i++)
        {
            if(currTime + courses[i][0] <= courses[i][1])
            {
                output++;
                isCourseTaken[i] = true;
                currTime += courses[i][0];
            }
            else    // If it exceeds deadline, I can swap current course with already chosen courses that has biggest duration.
            {
                int maxIndex = i;
                for(int j = 0; j < i; j++)
                {
                    if(isCourseTaken[j] && courses[j][0] > courses[maxIndex][0]) {
                        maxIndex = j;
                    }
                }
                savedTimeForFutureCourses = courses[maxIndex][0] - courses[i][0];
                if(savedTimeForFutureCourses > 0) 
                {
                    currTime -= savedTimeForFutureCourses;  // main logic: we need to reduce currTime for future courses
                    isCourseTaken[i] = true;                // replace i course and maxIndex course
                    isCourseTaken[maxIndex] = false;
                }
            }
        }
        return output;
    }
}
*/
    

/*
    Logic: greedy
    Time: n*log(n) and Space: n
    Same as above code. For finding max value, we use maxHeap which reduces the time from n^2 to n*log(n)
*/
class Solution
{
    public int scheduleCourse(int[][] courses) 
    {
        int n = courses.length;
        int currTime = 0, savedTimeForFutureCourses = 0;
        
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] course1, int[] course2) {
                return course1[1] - course2[1];                             // sort based on day
            }
        });
        
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return b - a;                                               // maxHeap based on time duration
            } 
        });
        
        for(int[] course: courses)
        {
            if(currTime + course[0] <= course[1])
            {
                pQueue.add(course[0]);
                currTime += course[0];
            }
            else    // If it exceeds deadline, I can swap current course with already chosen courses that has biggest duration.
            {
                if(!pQueue.isEmpty())
                {
                    int maxValue = pQueue.peek();
                    savedTimeForFutureCourses = maxValue - course[0];
                    if(savedTimeForFutureCourses > 0) 
                    {
                        currTime -= savedTimeForFutureCourses;  // main logic: we need to reduce currTime for future courses
                        pQueue.remove(maxValue);                // replace i course and maxValue course
                        pQueue.add(course[0]);
                    }
                }
            }
        }
        return pQueue.size();
    }
}
