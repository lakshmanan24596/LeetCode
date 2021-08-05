/*
Suppose you have a random list of people standing in a queue. 
Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.
 
Example
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/


/*
    sorting + greedy = O(n*logn)
    https://leetcode.com/problems/queue-reconstruction-by-height/solution/
    logic: process taller person first
*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int h = 0; // height of a person
        int k = 1; // number of people in front of this person, who have height >= h
        int n = people.length;
        
        Arrays.sort(people, new Comparator<int[]>() { 
            public int compare(int[] entry1, int[] entry2) {    // descending order (taller height first) 
                if (entry1[h] < entry2[h]) {
                    return 1; 
                } else if(entry1[h] == entry2[h]) {
                    return (entry1[k] < entry2[k]) ? -1 : 1;
                } else {
                    return -1;
                }
            } 
        }); 
        
        LinkedList<int[]> list = new LinkedList<int[]>();
        for(int i = 0; i < n; i++) {
            list.add(people[i][k], people[i]);      // main logic: process taller person first and insert in correct position
        }
        for(int i = 0; i < n; i++) {
            people[i] = list.get(i);                // list to array conversion
        }
        return people;
    }
}