/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
LRUCache cache = new LRUCache( 2 /* capacity */ );
/*
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

class LRUCache 
{
    class Node                              // DLL
    {
        Node prev, next;
        int key, value;
        Node(int key, int value)
        {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    Node head, tail;    
    int totalCapacity, currCapacity;
    Map<Integer, Node> map;                 // main logic
    
    public LRUCache(int capacity) 
    {
        map = new HashMap<Integer, Node>(capacity);
        totalCapacity = capacity;
        currCapacity = 0;
        head = null;
        tail = null;        
    }
    
    public int get(int key)
    {
        if(!map.containsKey(key)) {
            return -1;
        }           
        deleteAndInsertFront(key);     
        return map.get(key).value;
    }
    
    public void put(int key, int value) 
    {
        if(totalCapacity <= 0) {
            return;
        }       
        if(map.containsKey(key)) {
            Node currNode = map.get(key);
            currNode.value = value;         // replace new value for same key
        	deleteAndInsertFront(key);
        	return;
        }
        
        Node currNode = new Node(key, value);               
        if(currCapacity == totalCapacity)   // full
        {    
            map.remove(tail.key);           // remove key from map
            currCapacity--;
            Node secondLast = tail.prev;
            if(secondLast != null) {          	
                deleteLast();           
                insertFront(currNode);
            }
            else {
                makeAsHead(currNode);       // when totalCapacity = 1
            }
        }            
        else                                // not full
        {
            if(currCapacity == 0) {
                makeAsHead(currNode);
            } else {
                insertFront(currNode);
            } 
        }       
        currCapacity++;
        map.put(key, currNode);
    }
    
    // Util methods:::::
    
    public void makeAsHead(Node currNode) {
        head = currNode;
        tail = currNode;
    }
    
    public void insertFront(Node currNode) {
        currNode.next = head;
        head.prev = currNode;
        head = currNode;
        head.prev = null;
    }
    
    public void deleteLast()  {
        tail = tail.prev;
        tail.next = null;
    }
    
    public void delete(Node currNode) {
    	Node nextN = currNode.next;
        Node prevN = currNode.prev;
        prevN.next = nextN;
        
        if(nextN != null) {
            nextN.prev = prevN; 
        } else { 
        	tail = prevN;		            // delete last
        }
    }
    
    public void deleteAndInsertFront(int key) {
    	Node currNode = map.get(key);
        if(currNode != head && currCapacity > 1) 
        {
        	delete(currNode);
            insertFront(currNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */