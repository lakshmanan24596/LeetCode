/*
Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
Return the minimum size of the set so that at least half of the integers of the array are removed.

Example 1:
Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.

Example 2:
Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.

Example 3:
Input: arr = [1,9]
Output: 1

Example 4:
Input: arr = [1000,1000,3,7]
Output: 1

Example 5:
Input: arr = [1,2,3,4,5,6,7,8,9,10]
Output: 5
 
Constraints:
1 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5
*/


/*
class Solution 
{
    // Time: n + nlogn + n ==> nlogn
    // Space: n
    public int minSetSize(int[] arr)    
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1); 
        }
        
        List<Integer> freqList = new ArrayList<Integer>(map.values());
        freqList.sort(new Comparator<Integer>() {       // greedy (process max freq element first)
        	public int compare(Integer a, Integer b) {
        		return b - a;
        	}
        });
        
        int totalFreq = 0, setSize = 0, halfSize = arr.length / 2;
        for(int val : freqList) 
        {
            totalFreq += val;
            setSize++;
            if(totalFreq >= halfSize) {
                break;
            }
        }
        return setSize;
    }
}
*/


/*
    Greedy logic is same as above solution. Instead of sorting freqValues we can use extra array to store the freq.
    Time is reduced from nlogn to n.
*/
class Solution 
{
    // Time: n + n + n ==> n
    // Space: 2n
    public int minSetSize(int[] arr)
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1); 
        }
        
        int[] freqArr = new int[arr.length + 1];
        for(int value : map.values()) {
            freqArr[value]++;   // use array index as freqValue
        }
        
        int totalFreq = 0, setSize = 0, halfSize = arr.length / 2;
        for(int i = freqArr.length-1; i >= 0; i--)  // iterate from last because we need to process max freq first (greedy)
        {
            int freq = freqArr[i];
            while(freq-- > 0) {
                totalFreq += i;  // array index is used as freqValue
                setSize++;
                if(totalFreq >= halfSize) {
                    return setSize;
                }
            }
        }
        return setSize;
    }
}