/*
Implement the MapSum class:
MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. 
If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)

Constraints:
1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
*/

class MapSum 
{
    Trie head;
    Map<String, Integer> map;
    
    class Trie
    {
        int count;
        Trie[] children;
        Trie(int count) {
            this.count = count;
            this.children = new Trie[26];
        }
    }
    
    public MapSum() {
        head = new Trie(0);
        map = new HashMap<String, Integer>();               // used to handle duplicate keys
    }
    
    public void insert(String key, int val)                 // O(key len)
    {
        char[] keyArr = key.toCharArray();
        Trie currNode = head;
        int index;
        
        int deltaValue = val - map.getOrDefault(key, 0);    // main logic to handle duplicate keys
        map.put(key, val);
        
        for(int i = 0; i < keyArr.length; i++) 
        {
            index = keyArr[i] - 'a';
            if(currNode.children[index] == null) {
                currNode.children[index] = new Trie(0);
            }
            currNode = currNode.children[index];
            currNode.count += deltaValue;                   // main logic: all nodes will hold the count to make getSum faster
        }
    }
    
    public int sum(String prefix)                           // O(key len)
    {
        char[] prefixArr = prefix.toCharArray();
        Trie currNode = head;
        int index;
        
        for(int i = 0; i < prefixArr.length; i++) 
        {
            index = prefixArr[i] - 'a';
            currNode = currNode.children[index];
            if(currNode == null) {
                return 0;
            }
        }
        return currNode.count;                              // no need to do DFS from here, because all nodes hold the count 
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */