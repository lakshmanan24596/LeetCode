// Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

// Note:

// The solution set must not contain duplicate triplets.

// Example:

// Given array nums = [-1, 0, 1, 2, -1, -4],

// A solution set is:
// [
//   [-1, 0, 1],
//   [-1, -1, 2]
// ]


class Solution 
{
    public List<List<Integer>> threeSum(int[] arr) 
    {
        HashSet<Node> set = new HashSet<Node>();
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        List<Integer> currOutput = null;
        
        Arrays.sort(arr);
        int length = arr.length;
        int i,j,k;
        Node key;
        
        for(i=0; i<length-2; i++)
        {
            j = i+1;
            k = length - 1;
            
            while(j < k)    // two pointer technique
            {    
                int sum = arr[i] + arr[j] + arr[k];              
                if(sum == 0)
                {
                    key = new Node(arr[i], arr[j], arr[k]);
                    if(!set.contains(key))  // unique triplets check
                    {       
                        currOutput = new ArrayList<Integer>();
                        currOutput.add(arr[i]);
                        currOutput.add(arr[j]);
                        currOutput.add(arr[k]);
                        output.add(currOutput);
                        set.add(key);
                    }
                    j++;
                    k--;
                }
                
                else if(sum < 0)            
                    j++;

                else         
                    k--;
            }
        }
        
        return output;
    }
}

class Node {
    int x, y, z;
    
    Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }    
    
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }
    
    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return this.x == node.x && this.y == node.y && this.z == node.z;
    }
}