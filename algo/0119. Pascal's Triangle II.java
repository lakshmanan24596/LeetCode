/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
Note that the row index starts from 0.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 3
Output: [1,3,3,1]
*/

class Solution 
{
    public List<Integer> getRow(int rowIndex) 
    {
        if(rowIndex < 0)
            return null;
                   
        // initial --> 1-3-3-1
        // add 1 last --> 1-3-3-1-1
        // final --> 1-4-6-4-1 where (j = 3,2,1)
        
        List<Integer> output = new ArrayList<Integer>();
        
        for(int i = 0; i <= rowIndex; i++)
        {
            output.add(1);
            // iterate from last because we add curr with "previous element (j-1)" which should not be changed before itself
            for(int j = output.size()-2; j >= 1; j--)
            {
                output.set(j, output.get(j) + output.get(j-1));
            }
        }
        return output;
    }
}

// class Solution {
//     public List<Integer> getRow(int k) {
//         if (k < 0) {
//             return new ArrayList();
//         }
//         List<Integer> row = new ArrayList();
//         row.add(1);
//         long C = 1; // use a `long` to prevent integer overflow
//         for (int i = 1; i <= k; i++) {
//             C = C * (k + 1 - i) / i;
//             row.add((int) C);
//         }
//         return row;
//     }
    
// }