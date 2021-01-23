/*
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
If there isn't any rectangle, return 0.

Example 1:
Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:
Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

Note:
1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
*/


/*
    1) brute force: n^4, 1
    2) hashmap: n^2, n^2
        Logic:
        This is similar to 4-sum problem
        Pick 2 points in loop and another 2 points in hashtable
        "Those 2 points should be a diagnol"
*/
/*
    Implementation:
    1) override equals and hashcode method --> https://leetcode.com/problems/minimum-area-rectangle/discuss/192759/Simple-AF-JAVA-solution-with-explanation-O(n2)
    2) convert 2D to 1D formula --> ((i * n) + j), where n = 40000 --> https://leetcode.com/problems/minimum-area-rectangle/solution/
    3) hashMap with key, value --> https://leetcode.com/problems/minimum-area-rectangle/discuss/192025/Java-N2-Hashmap
    4) String concat --> x + "#" + y
*/

class Solution {
    public int minAreaRect(int[][] points) {                // Time: n^2, Space: n^2
        Set<Node> pointSet = new HashSet<Node>();
        for (int[] point : points) {
            pointSet.add(new Node(point[0], point[1]));     // initially add all points in the set
        }
        
        int minArea = Integer.MAX_VALUE, currArea;
        int x1, y1, x2, y2;
        Node p3, p4;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                x1 = points[i][0];
                y1 = points[i][1];
                x2 = points[j][0];
                y2 = points[j][1];
                if (x1 != x2 && y1 != y2) {     // condition for diagonal
                    p3 = new Node(x1, y2);
                    p4 = new Node(x2, y1);
                    if (pointSet.contains(p3) && pointSet.contains(p4)) {   // condition for rectangle
                        currArea = Math.abs(x1 - x2) * Math.abs(y1 - y2);   // formula to calculate rectangle area
                        minArea = Math.min(minArea, currArea);
                    }
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
    
    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        // https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/
        @Override
        public int hashCode() {                 // First, find out the right bucket using hashCode().
            return Objects.hash(x, y);
        }
        @Override
        public boolean equals(Object obj) {     // Secondly, search the bucket for the right element using equals()    
            Node node = (Node)obj;
            return (node.x == this.x) && (node.y == this.y);
        }
    }
}