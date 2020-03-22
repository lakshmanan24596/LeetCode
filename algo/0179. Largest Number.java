/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:
Input: [10,2]
Output: "210"

Example 2:
Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/

class Solution 
{
    public String largestNumber(int[] nums) 
    {
        // lexicographical sorting
        
        if(nums == null) return null;
        
        String[] arr = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            arr[i] = String.valueOf(nums[i]);
        
        Arrays.sort(arr, new Comparator<String>()           // in-built sorting using comparator 
        {
            public int compare(String a, String b)
            {
                return (b+a).compareTo(a+b);                // + operator is used to compare
            }
        });
        
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < arr.length; i++)
            output.append(arr[i]);
        
        if(output.charAt(0) == '0' && output.length() > 1)  // [0,0] use case
            return "0";   
        return output.toString();
    }
  
    
//     public void lexSort(String[] arr)
//     {
//         int len1, len2, len, k;
    
//         for(int i = 0; i < arr.length; i++)
//         {
//             for(int j = i + 1; j < arr.length; j++)
//             {
//                 len1 = arr[i].length();
//                 len2 = arr[j].length();
//                 len = (len1 > len2) ? len2 : len1;
                
//                 k = 0;
//                 for(; k < len; k++)
//                 {
//                     if(arr[i].charAt(k) < arr[j].charAt(k))
//                     {
//                         swap(arr, i, j);
//                         break;
//                     }
//                     else if(arr[i].charAt(k) > arr[j].charAt(k))
//                     {
//                     	    break;
//                     }
//                     // continue the loop only when both char are equal
//                 }
                
//                 if( k == len && arr[i].charAt(k-1) == arr[j].charAt(k-1) &&
//                     ((len1 < len2 && arr[j].charAt(k) != '0') ||                 // 3, 34 (swap) and 3, 30 (no swap)
//                     (len1 > len2 && arr[i].charAt(k) == '0')))                   // 30, 3 (swap)
//                 {
//                     swap(arr, i, j);
//                 }
//             }
//         }
//     }
//    
//     public void swap(String[] arr, int i, int j)
//     {
//         String temp = arr[i];
//         arr[i] = arr[j];
//         arr[j] = temp;
//     }
}