/*
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.

Constraints:
n == ratings.length
1 <= n <= 2 * 104
1 <= ratings[i] <= 2 * 104
*/



/*
    brute force, greedy
    time: n^2
    space: n
*/
/*
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        int output = 0;
        
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                // negative case: less rating have more candies. so we need to change it
                for (int j = i; j > 0 && ratings[j] < ratings[j - 1] && candies[j] >= candies[j - 1]; j--) {
                    candies[j-1] = candies[j] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            output += candies[i];
        }
        return output;
    }
}
*/


/*
    greedy    
    process leftToRight and rightToLeft
    time: 3n
    space: 2n
*/
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        int output = 0;
        
        for (int i = 1; i < n; i++) {
            left[i] = (ratings[i] > ratings[i-1]) ? left[i-1] + 1 : 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = (ratings[i] > ratings[i+1]) ? right[i+1] + 1 : 1;
        }
        for (int i = 0; i < n; i++) {
            output += Math.max(left[i], right[i]);
        }
        return output;
    }
}


/*
    time: n
    space: 1
    using slope --> https://leetcode.com/problems/candy/solution/
*/
