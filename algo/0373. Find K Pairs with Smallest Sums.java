class Solution 
{
    /*
    # # # # # ? . .
    # # # ? . . . .
    # ? . . . . . .   "#" means pair already in the output
    # ? . . . . . .   "?" means pair currently in the heap
    # ? . . . . . .  
    ? . . . . . . .
    . . . . . . . .
    */
        
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) 
    {
        List<List<Integer>> outputList = new ArrayList<List<Integer>>();
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k < 1) {
            return outputList;
        }
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return (nums1[pair1[0]] + nums2[pair1[1]]) - (nums1[pair2[0]] + nums2[pair2[1]]);   // min heap
            }
        });
        
        // boolean isSwapDone = false;
        // if(nums1.length < nums2.length) {   // keep nums2 as smaller
        //     int[] temp = nums1;
        //     nums1 = nums2;
        //     nums2 = temp;
        // }
        
        int size = Math.min(nums2.length, k);
        for(int i = 0; i < size; i++) {     // process first row in matrix (Store (1,2), (1,4), (1,6) in heap for given ex1)
            pqueue.add(new int[]{0, i});    // store index
        }
        
        List<Integer> currList;
        int[] pair;
        size = Math.min(nums1.length * nums2.length, k);
        for(int i = 0; i < size; i++)       // Time = K log N and Space = N
        {
            pair = pqueue.remove();
            currList = new ArrayList<Integer>();
            currList.add(nums1[pair[0]]);
            currList.add(nums2[pair[1]]);
            outputList.add(currList);
            
            if(pair[0] + 1 < nums1.length) {    // if not out of matrix, then add it
                pqueue.add(new int[] {pair[0] + 1, pair[1]});
            }
        }
        return outputList;
    }
}
