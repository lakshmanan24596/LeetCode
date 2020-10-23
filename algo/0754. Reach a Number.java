/*
You are standing at position 0 on an infinite number line. There is a goal at position target.
On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.

Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.

Note:
target will be a non-zero integer in the range [-10^9, 10^9].
*/

class Solution 
{
    public int reachNumber(int target) 
    {
        // https://www.geeksforgeeks.org/find-minimum-moves-reach-target-infinite-line/ 
        
        int step = 0, sum = 0;
        if(target < 0) {
            target *= -1;
        }
        
        while(sum < target) {
            step++;
            sum += step;    // sum will be 1, 3, 6, 10,...
        }
        
        while((sum - target) % 2 != 0) {   // main logic
            step++;
            sum += step;
        }
        
        return step;
    }
}


/*
Brute force recursive

class Solution 
{
    int target;
    public int reachNumber(int target) 
    {
        this.target = target;
        return reachNumber(0, 0);   
    }
    
    public int reachNumber(int source, int step)
    {
        if(source == target || source == target * -1) {     // because -10 and +10 will have same output (symmentric)
            return step;
        }
        if(Math.abs(source) > Math.abs(target)) {
            return Integer.MAX_VALUE;
        }
        
        step++;
        return Math.min(reachNumber(source - step, step),
                       reachNumber(source + step, step));
    }
}
*/