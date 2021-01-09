/*
// Time: n*logn + n*logn ==> n*logn
class Solution 
{
    public boolean isNStraightHand(int[] arr, int W)
    {
        if(arr.length == 0 || W == 0 || W > arr.length || arr.length % W != 0) {
            return false;
        }
        if(W == 1) {
            return true;
        }
        
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>() ;
        for(int arrElement : arr) {             // n * log(n)
            pQueue.add(arrElement);
        }
        
        while(!pQueue.isEmpty())                // n * log(n)
        {
            int start = pQueue.remove();
            for(int i = 1; i < W; i++)
            {
                if(!pQueue.remove(start + i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
*/

// logic: same as above logic but instead of pQueue, we will use linked list
// time: n*logn + n ==> n*logn
class Solution 
{
    public boolean isNStraightHand(int[] arr, int W)
    {
        if(arr.length == 0 || W == 0 || W > arr.length || arr.length % W != 0) {
            return false;
        }
        if(W == 1) {
            return true;
        }
        
        Arrays.sort(arr);                                   // n*logn
        LinkedList<Integer> list = new LinkedList<>();
        for(int arrElement : arr) {                         // n
            list.add(arrElement);
        }
        
        Iterator<Integer> iter = list.iterator();
        int prev = -1, size = 0;
        
        while(iter.hasNext())                               // n
        {
            int curr = iter.next();
            if(size == 0 || curr == prev+1)
            {
                iter.remove();
                size++;
                prev = curr;
            }
            if(size == W)
            {
                iter = list.iterator();     // iter is reset to start to linked list. This may increase the time from n to n^2 in worst case
                size = 0;
                prev = -1;
            }
        }
        return list.size() == 0 && size == 0;
    }
}
