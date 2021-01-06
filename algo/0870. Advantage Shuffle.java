/*
    Brute: n!
    DP with bitmask: 2^n
    
    Logic: Greedy
    Time: n*logn, Space: n
    Algo:
        Sort A first. 
        Then for each element of B, do an upper bound of that element in A.
        If it exists use that. Otherwise, use the smallest element in A instead.
    
    Implementation:
        Duplicates are allowed. So we cannot use treeSet ceiling() and pollFirst()
        So we can use TreeMap ceilingEntry() and pollFirstEntry()
*/
class Solution 
{
    public int[] advantageCount(int[] A, int[] B) 
    {
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();    // key,value = value,freq
        for(int a : A) {
            treeMap.put(a, treeMap.getOrDefault(a, 0) + 1);
        }
        
        for(int i = 0; i < B.length; i++)
        {
            Map.Entry entry = treeMap.ceilingEntry(B[i] + 1); // Greedy: For each element of B, do an upper bound of that element in A.
            if(entry == null) {
                entry = treeMap.pollFirstEntry();   // If it exists use that. Otherwise, use the smallest element in A instead.
            }
            
            int key = (int)entry.getKey();
            int freq = (int)entry.getValue();
            A[i] = key;
            if(freq == 1) {
                treeMap.remove(key);
            } else {
                treeMap.put(key, freq - 1);
            }
        }
        return A;
    }
}
