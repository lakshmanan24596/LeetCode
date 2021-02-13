/*
You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. 
Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
Return the minimum number of boats to carry every given person.

Example 1:
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)

Constraints:
1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
*/


/*
    Logic: greedy, sort, 2 pointer technique
    time: n*logn
    space: 1
*/
/*
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int start = 0;
        int end = people.length - 1;
        int minBoats = 0;
        Arrays.sort(people);
        
        while (start <= end) {
            if (people[start] + people[end] <= limit) {
                start++;
                end--;
            } else {
                end--;
            }
            minBoats++;
        }
        return minBoats;
    }
}
*/


/*
    Logic: exactly same as above
    Use "bucket sort" with size as limit (because, people[i] <= limit)
    
    Time: n
    Space: limit
*/
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int start = 1;
        int end = limit;
        int minBoats = 0;
        int[] bucket = new int[limit + 1];
        
        for (int peo : people) {
            bucket[peo]++;                      // bucket sort
        }
        while (start <= end) {
            if (bucket[start] <= 0) {
                start++;
            }
            else if (bucket[end] <= 0) {
                end--;
            }
            else {
                if (start + end <= limit) {     // same logic as above solution
                    bucket[start]--;
                    bucket[end]--;
                } else {
                    bucket[end]--;
                }
                minBoats++;
            }
        }
        return minBoats;
    }
}
