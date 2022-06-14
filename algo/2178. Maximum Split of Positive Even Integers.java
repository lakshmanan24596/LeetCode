/*
You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.

For example, given finalSum = 12, the following splits are valid (unique positive even integers summing up to finalSum): (12), (2 + 10), (2 + 4 + 6), and (4 + 8). 
Among them, (2 + 4 + 6) contains the maximum number of integers. Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.

Return a list of integers that represent a valid split containing a maximum number of integers. 
If no valid split exists for finalSum, return an empty list. 
You may return the integers in any order.


Example 1:
Input: finalSum = 12
Output: [2,4,6]
Explanation: The following are valid splits: (12), (2 + 10), (2 + 4 + 6), and (4 + 8).
(2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return [2,4,6].
Note that [2,6,4], [6,2,4], etc. are also accepted.

Example 2:
Input: finalSum = 7
Output: []
Explanation: There are no valid splits for the given finalSum.
Thus, we return an empty array.

Example 3:
Input: finalSum = 28
Output: [6,8,2,12]
Explanation: The following are valid splits: (2 + 26), (6 + 8 + 2 + 12), and (4 + 24). 
(6 + 8 + 2 + 12) has the maximum number of integers, which is 4. Thus, we return [6,8,2,12].
Note that [10,2,4,12], [6,2,4,16], etc. are also accepted.

Constraints:
1 <= finalSum <= 1010
*/


/*
    logic: greedy
    time: output
    space: output
    
    FinalSum = 28
    Output = 2, 4, 6, 8
    Now if we add 10 to the output, it exceeds final sum. So remove 8 and then add 16
    New output = 2, 4, 6, 16
*/

class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> output = new ArrayList<Long>();
        if (finalSum <= 0 || finalSum % 2 != 0) {
            return output;
        }
        long currSum = 0;
        
        for (long i = 2; currSum + i <= finalSum; i += 2) {     // greedily add 2 to each iteration
            currSum += i;
            output.add(i);   
        }
        
        if (currSum != finalSum) {
            long lastElem = output.remove(output.size() - 1);
            output.add(finalSum - (currSum - lastElem));
        }
        return output;
    }
}
