/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

class Solution 
{
    public List<List<Integer>> generate(int numRows) 
    {
        if(numRows < 0) 
            return null;
            
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if(numRows == 0)
            return output;
        
        List<Integer> currOutput = new ArrayList<Integer>();       
        currOutput.add(1);
        output.add(currOutput);
        
        for(int i = 1; i < numRows; i++)
        {
            currOutput = new ArrayList<Integer>();
            currOutput.add(1);                                                  // add 1 in front
          
            List<Integer> prevOutput = output.get(i-1);
            for(int j = 1; j < i; j++)
            {
                currOutput.add(prevOutput.get(j-1) + prevOutput.get(j));        // middle elements
            }
            
            currOutput.add(1);                                                  // add 1 in last
            output.add(currOutput);
        }
        
        return output;
    }
}