/*
    follow up ques:
        1) if input is sorted, then no need extra space and we can use 2 pointer technique
        2) if nums1 size is smaller, then use hashmap for smaller size array
        3) If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of nums2 that fit into the memory, and record the intersections.
        4) If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
*/

class Solution 
{
    public int[] intersect(int[] nums1, int[] nums2) // Time: n1 + n2, Space: min(n1, n2)
    {
        if(nums1.length > nums2.length) {   // to reduce space, nums1 should be smaller size. so swap it.
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        List<Integer> output = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int n1 : nums1) {
            map.put(n1, map.getOrDefault(n1, 0) + 1);
        }
        
        for(int n2 : nums2) 
        {
            if(map.containsKey(n2)) 
            {
                if(map.get(n2) == 1) {
                    map.remove(n2);
                } else {
                    map.put(n2, map.get(n2) - 1);
                }
                output.add(n2);
            }  
        }
        
        int[] outputArr = new int[output.size()];
        int i = 0;
        for(int val : output) {
            outputArr[i++] = val;
        }
        return outputArr;
    }
}
