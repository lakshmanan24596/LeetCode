/*
Given a non-empty list of words, return the k most frequent elements.
Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
*/


/*
    1) hashmap --> logic: create a map with string, freq. 
                   Sort the map by values. 
                   Time: n*logn
                   
    2) hashmap + minheap --> logic: create a map with string, freq.
                             store map entry in minheap with size k
                             same as 347. Top K Frequent Elements
                             Time: n*logk 
                             
    PriorityQueue<Map.Entry<String,Integer>> pqueue = new PriorityQueue<Map.Entry<String,Integer>>(new Comparator<Map.Entry<String,Integer>>() {
        public int compare(Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2) {
            return (entry1.getValue() - entry2.getValue());   // min heap
        }
    });
    
    PriorityQueue<String> heap = new PriorityQueue<String>(
        (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2) 
    );
*/

/*
    1) hashmap --> logic: create a map with string, freq. 
                   Sort the map by values. 
                   Time: n*logn
                   
    2) hashmap + minheap --> logic: create a map with string, freq.
                             store map entry in minheap with size k
                             same as 347. Top K Frequent Elements
                             Time: n*logk 
                             
    PriorityQueue<Map.Entry<String,Integer>> pqueue = new PriorityQueue<Map.Entry<String,Integer>>(new Comparator<Map.Entry<String,Integer>>() {
        public int compare(Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2) {
            return (entry1.getValue() - entry2.getValue());   // min heap
        }
    });
    
    PriorityQueue<String> heap = new PriorityQueue<String>(
        (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2) 
    );
*/

class Solution 
{
   public List<String> topKFrequent(String[] words, int k)
   {
       Map<String, Integer> map = new HashMap<String, Integer>();
       for(String word: words) {
           map.put(word, map.getOrDefault(word, 0) + 1);
       }
       
       PriorityQueue<String> pQueue = new PriorityQueue<String>(
           (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2) 
       );
       
       for (String word: map.keySet()) 
       {
           if(pQueue.size() < k) {     // first K elements
               pQueue.add(word);
           }
           else    // remaining elements
           {
               if(map.get(word) >= map.get(pQueue.peek())) {     // main logic
                   pQueue.add(word);
                   pQueue.remove();
               }
           }
       }

       List<String> output = new ArrayList();
       while (!pQueue.isEmpty()) {
           output.add(pQueue.remove());
       }
       Collections.reverse(output);
       return output;
   }
}


/*
class Solution 
{
    Map.Entry<String,Integer>[] heap;
    Map<String, Integer> map;
    
    public List<String> topKFrequent(String[] words, int k)  // logic : hashMap + minHeap --> Time: O(n * log k)
    {
        map = new HashMap<String, Integer>();
        heap = new Map.Entry[k];
        int heapCurrSize = 0;
        
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        for(Map.Entry<String, Integer> entry : map.entrySet())
        {
            if(heapCurrSize < k)                              // first K elements
            {
              heap[heapCurrSize] = entry;
              int i = heapCurrSize;
              while(i != 0 && heap[i].getValue() < heap[getParent(i)].getValue()) {
                swap(i, getParent(i));
                i = getParent(i);
              }
              heapCurrSize++;
            }
            else                                      // remaining elements
            {
              if(entry.getValue() > heap[0].getValue()) {     // main logic
                heap[0] = entry;
                minHeapify(0, heapCurrSize);                // time: log k
              }
            }
        }
        
        List<String> output = new ArrayList<String>();
        for(int i = heapCurrSize - 1; i >= 0; i--) {
            output.add(heap[i].getKey());
        }
        return output;
    }
    
    public void minHeapify(int index, int size) 
    {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest = index;
        
        if(left < size && heap[left].getValue() < heap[smallest].getValue()) {
            smallest = left;
        }
        if(right < size && heap[right].getValue() < heap[smallest].getValue()) {
            smallest = right;
        }
        
        if(index != smallest) {
            swap(smallest, index);
            minHeapify(smallest, size);
        }
    }
    
    public void swap(int a, int b)
    {
        Map.Entry<String, Integer> temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    
    public int getParent(int i) {
      return (i - 1) / 2;
    }
    public int getLeft(int i) {
      return (2 * i) + 1;
    }
    public int getRight(int i) {
      return (2 * i) + 2;
    }
}
*/