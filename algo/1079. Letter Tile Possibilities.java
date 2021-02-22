/*
You have n  tiles, where each tile has one letter tiles[i] printed on it.
Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

Example 1:
Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:
Input: tiles = "AAABBC"
Output: 188

Example 3:
Input: tiles = "V"
Output: 1

Constraints:
1 <= tiles.length <= 7
tiles consists of uppercase English letters.
*/


/*
    Logic: backtracking
    time: output, which is exponential
    space: n
    
    Logic and state space tree:
    https://leetcode.com/problems/letter-tile-possibilities/discuss/308284/Concise-java-solution
*/
/*
class Solution {
    char[] arr;
    int n;
    int[] count;
    
    public int numTilePossibilities(String tiles) {
        count = new int[26];
        arr = tiles.toCharArray();
        n = arr.length;
        
        for (int i = 0; i < n; i++) {
            count[arr[i] - 'A']++;
        }
        return dfs();
    }
    
    public int dfs() {
        int output = 0;
        
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                count[i]--;
                output += 1 + dfs();
                count[i]++;
            }
        }
        return output;
    }
}
*/



/*
    Backtracking + DP memo:
    There are some duplicate computations, for example, count-{'a':1,'b':1,'c':25}, the suffix computation for "ab"+rest and "ba"+rest are the same, so memoization can speed it up:
*/
class Solution {
    char[] arr;
    int n;
    Map<Integer, Integer> DP;
    
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        arr = tiles.toCharArray();
        n = arr.length;
        DP = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < n; i++) {
            count[arr[i] - 'A']++;
        }
        return dfs(count);
    }
    
    public int dfs(int[] count) {
        int key = Arrays.hashCode(count);       // main logic
        if (DP.containsKey(key)) {
            return DP.get(key);
        }
        int output = 0;
        
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                count[i]--;
                output += 1 + dfs(count);
                count[i]++;
            }
        }
        DP.put(key, output);
        return output;
    }
}