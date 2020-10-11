/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.

Example:
// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
*/

class RandomizedCollection 
{

    Map<Integer, Set<Integer>> map;  // Pair(value, set of index)
    List<Integer> list;
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() 
    {
        map = new HashMap<Integer, Set<Integer>>();
        list = new ArrayList<Integer>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) 
    {
        list.add(val);
        if(map.containsKey(val)) 
        {
            Set<Integer> mapSet = map.get(val);
            mapSet.add(list.size() - 1);
            return false;
        }
        else 
        {
            Set<Integer> mapSet = new HashSet<Integer>();
            mapSet.add(list.size() - 1);
            map.put(val, mapSet);
            return true;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) 
    {
        if(!map.containsKey(val)) {
            return false;
        }
        
        int lastIndexValue = list.get(list.size()-1); 
        Set<Integer> mapSet = map.get(val);
        int index = mapSet.iterator().next();
        
        if(index != list.size() - 1 && lastIndexValue != val)
        {
            list.set(index, lastIndexValue);    // main logic: swap (curr, last) and then remove last which is O(1)
            
            Set<Integer> lastIndexMapSet = map.get(lastIndexValue);  // index replacement should be updated in map<set> also
            lastIndexMapSet.remove(list.size()-1);
            lastIndexMapSet.add(index);
        }
        
        list.remove(list.size() - 1);
        mapSet.remove(lastIndexValue != val ? index : list.size());
        if(mapSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() 
    {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */