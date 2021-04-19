/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below. 
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].  
It is guaranteed the answer exists.

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
|x| + |y| <= 300
*/


/*
// TLE

class Solution {
    public int minKnightMoves(int x, int y) {
        Node startNode = new Node(0, 0);
        Queue<Node> knightPos = new LinkedList<Node>();
        knightPos.add(startNode);
        Set<Node> visitedPos = new HashSet<Node>();
        visitedPos.add(startNode);
        int[][] dir = new int[][] {{-1, 2}, {1, -2}, {-1, -2}, {1, 2}, {-2, 1}, {2, -1}, {-2, -1}, {2, 1}};
        Node currPos, nextPos;
        int nextX, nextY;
        int minMoves = 0;
        
        while (!knightPos.isEmpty()) {
            int posCount = knightPos.size();
            while (posCount-- > 0) {
                currPos = knightPos.remove();
                if (currPos.x == x && currPos.y == y) {
                    return minMoves;
                }
                for (int i = 0; i < dir.length; i++) {
                    nextX = currPos.x + dir[i][0];
                    nextY = currPos.y + dir[i][1];
                    nextPos = new Node(nextX, nextY);
                    if (!visitedPos.contains(nextPos)) {
                        visitedPos.add(nextPos);
                        knightPos.add(nextPos);
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
    
    class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return ((node.x == this.x) && (node.y == this.y));
        } 
    }
}
*/


/*
    Optimizations done to above code:
        1) x = |x| and y = |y|
        2) no need (-1, -2) and (-2, -1) directions
        3) use arr[300] instead of hashset due to given constraint
        4) pruning: (Main optimization)
            -1 <= nextX <= x+2
            -1 <= nextY <= y+2 
*/
class Solution {
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Node startNode = new Node(0, 0);
        Queue<Node> knightPos = new LinkedList<Node>();
        knightPos.add(startNode);
        Set<Node> visitedPos = new HashSet<Node>();
        visitedPos.add(startNode);
        int[][] dir = new int[][] {{-1, 2}, {1, -2}, {1, 2}, {-2, 1}, {2, -1}, {2, 1}};
        Node currPos, nextPos;
        int nextX, nextY;
        int minMoves = 0;
        
        while (!knightPos.isEmpty()) {
            int posCount = knightPos.size();
            while (posCount-- > 0) {
                currPos = knightPos.remove();
                if (currPos.x == x && currPos.y == y) {
                    return minMoves;
                }
                for (int i = 0; i < dir.length; i++) {
                    nextX = currPos.x + dir[i][0];
                    nextY = currPos.y + dir[i][1];
                    nextPos = new Node(nextX, nextY);
                    // main logic: pruning
                    if ((-1 <= nextX && nextX <= x + 2) && (-1 <= nextY && nextY <= y + 2) && !visitedPos.contains(nextPos)) {
                        visitedPos.add(nextPos);
                        knightPos.add(nextPos);
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
    
    class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return ((node.x == this.x) && (node.y == this.y));
        } 
    }
}



/*
// DFS memo - optimal solution

class Solution {
    public Map<String,Integer> cache = new HashMap<>();
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        
        // Could use a StringBuilder here for better performance
        String key = x + "," + y;
        
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
       
        if(x+y == 0 || x+y == 2) {
            cache.put(key, x+y);
            return x+y;
        }
        
        int answer = Math.min(minKnightMoves(x-2, y-1), minKnightMoves(x-1, y-2)) + 1;  // main logic
        cache.putIfAbsent(key, answer);
        
        return answer;
    }
}
*/