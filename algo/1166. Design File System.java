/*
You are asked to design a file system that allows you to create new paths and associate them with different values.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. 
For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:
bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.

Example 1:
Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]

Explanation: 
FileSystem fileSystem = new FileSystem();
fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1

Example 2:
Input: 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output: 
[null,true,true,2,false,-1]

Explanation: 
FileSystem fileSystem = new FileSystem();
fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.

Constraints:
The number of calls to the two functions is less than or equal to 104 in total.
2 <= path.length <= 100
1 <= value <= 109
*/



/*
    1) logic: hashmap
        time: pathLength, 1
        space: pathLength

        Advantage: get is O(1)
        Disadvantage: for common prefix, parent path is stored multiple times for each key, which increases the storage space. Ex: /leet, /leet/code --> here the parent path "/leet" is stored twice which is space consuming
        
    2) logic: trie
        time: pathLength, pathLength
        space: pathLength
        
        Advantage: It is space efficient then previous logic because common prefix are stored only once.
        Disadvantage: get is O(pathLength)
        
        Implementation: trie + hashmap
        https://leetcode.com/problems/design-file-system/discuss/464718/Java-TrieTree-Solution-Easy-to-Understand
*/

class FileSystem {
    Map<String, Integer> pathValueMap;
    
    public FileSystem() {
        pathValueMap = new HashMap<String, Integer>();
    }
    
    public boolean createPath(String path, int value) {
        if (path == null || path.length() <= 1 || pathValueMap.containsKey(path)) {
            return false;
        }
        
        int lastSlashIndex = path.lastIndexOf("/");
        if (lastSlashIndex == -1) {
            return false;
        }
        
        String parentPath = path.substring(0, lastSlashIndex);                      // O(path length)
        if (!pathValueMap.containsKey(parentPath) && !parentPath.isEmpty()) {
            return false;
        }
        
        pathValueMap.put(path, value);
        return true;
    }
    
    public int get(String path) {
        return pathValueMap.getOrDefault(path, -1);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */