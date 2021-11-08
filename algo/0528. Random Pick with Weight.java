/*
    Logic: prefix sum + binary search
    https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
    
    1) linear search:
        time: 1
        space: sum
    2) binary search:
        time: logn
        space: n, which is space optimized
*/

class Solution {
    int[] w;
    Random random = new Random();
    
    public Solution(int[] w) {
        this.w = w;
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i - 1];                                       // prefix sum 
        }
    }
    
    public int pickIndex() {
        int randomVal = random.nextInt(w[w.length - 1]) + 1;        // nextInt gives 0 to n-1 and we need 1 to n
        return ceilBinarySearch(randomVal);
    }
    
    public int ceilBinarySearch(int val) {
        int left = 0; 
        int right = w.length - 1;
        int mid;
        int outputIndex = 0;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            
            if (w[mid] == val) {
                return mid;
            } else if (val < w[mid]) {    
                outputIndex = mid;                                  // main logic: ceil search
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return outputIndex;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */