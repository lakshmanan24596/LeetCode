/*
There is a school that has classes of students and each class will be having a final exam. 
You are given a 2D integer array classes, where classes[i] = [passi, totali]. 
You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. 
There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. 
You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. 
The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. 
Answers within 10-5 of the actual answer will be accepted.


Example 1:
Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

Example 2:
Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485

Constraints:
1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
*/



/*
    Logic: greedy, maxHeap
    
    A student can be added in any class
    We need to find the class, which gives max profit
    
    time: nlogn + klogn
    space: n
    where n = no of classes, k = extraStudents
    
    Extra handling:
        if initial percent is 100%, then no need to add it into queue
        instead of calculating profit in runtime, we can pre-calculate and store it in heap as instance variable
*/

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int noOfClasses = classes.length;
        double maxSumRatio = 0;
        PriorityQueue<int[]> classPassRatio = new PriorityQueue<int[]>((a, b)-> ((getProfit(b) - getProfit(a)) > 0 ? 1 : -1));
        
        for (int[] clas : classes) {
            classPassRatio.add(clas);
        }
        while (extraStudents-- > 0) {
            int[] clas = classPassRatio.remove();
            clas[0] += 1;                                       // add extraStudent
            clas[1] += 1;
            classPassRatio.add(clas);
        }
        for (int i = 0; i < noOfClasses; i++) {
            int[] clas = classPassRatio.remove();
            maxSumRatio += (double) clas[0] / clas[1];
        }
        return maxSumRatio / noOfClasses;
    }
    
    public double getProfit(int[] clas) {
        double pass = (double) clas[0]; 
        double total = (double) clas[1];
        return ((pass + 1) / (total + 1)) - (pass / total);     // main logic
    }
}