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

class Solution 
{
    public int[][] reconstructQueue(int[][] people) 
    {
        // sorting + greedy = O(n^2)    
        
        int h = 0; // height of a person
        int k = 1; // number of people in front of this person who have a height greater than or equal to h
        int n = people.length;
        
        Arrays.sort(people, new Comparator<int[]>() 
        { 
            public int compare(int[] entry1, int[] entry2) // descending order (taller height first)
            { 
                if (entry1[h] < entry2[h]) {
                    return 1; 
                }
                if(entry1[h] == entry2[h]) {
                    return (entry1[k] < entry2[k]) ? -1 : 1;
                }
                else {
                    return -1;
                }
            } 
        }); 
        
        /*
        k is only determined by people with equal or larger height, so makes sense to insert in non-increasing order of height. 
        Because when we insert some person with height h and count k, we know that we have found its correct position relative to people with equal and larger height. 
        And when we later insert other people with equal or smaller height, we know that it will not affect this relative position. 
        So the answer is right after we insert all people.
        */
        
        //LinkedList<int[]> list = new LinkedList<int[]>();  // linkedlist is theoratically faster than arraylist for insert in random middle index.
        ArrayList<int[]> list = new ArrayList<int[]>(); 
        for(int i = 0; i < n; i++)
        {
            list.add(people[i][k], people[i]);    // main logic: process taller person first
        }
        for(int i = 0; i < n; i++)
        {
            people[i] = list.get(i);  // list to array conversion
        }
        return people;
    }
}
