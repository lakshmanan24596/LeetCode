/*
N couples sit in 2N seats arranged in a row and want to hold hands. 
We want to know the minimum number of swaps so that every couple is sitting side by side. 
A swap consists of choosing any two people, then they stand up and switch seats.
The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

Example 1:
Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.

Example 2:
Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.

Note:
len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.
*/


/*
    Logic: Greedy
    
    1) n^2 time:
    
        ex: pairNum = 2,0,2,1,0,3,1,3  (where pairNum = row[i] / 2)
              swap1 = 2,2,0,1,0,3,1,3
              swap2 = 2,2,0,0,1,3,1,3
              swap3 = 2,2,0,0,1,1,3,3
        output = 3
        
        greedily swap from the starting
        for each number, we need to search the same number in right side
        for searching, we can use hashmap to reduce time from n^2 to n
        since we are swaping, we need to alter each time in hashmap
        
        
    2) n time using DSU
        
        index 0,1 = pos 0
        index 2,3 = pos 1
        and so on..
        
        ex: pairNum = 0,1,1,0,2,3,3,2
        here in DSU we will do union pos (0,1) and union pos (2,3)
        here, no of connected components = 2
        in each component, output += rank of the component - 1
        output = 1 + 1 ==> 2
*/

class Solution {
    public int minSwapsCouples(int[] row) {
        int size = row.length;
        int halfSize = size / 2;
        DSU dsu = new DSU(halfSize);
        int output = 0;
        int[] prevPosArr = new int[halfSize];       // instead of hashmap, we can use array because values are 0 to n-1
        Arrays.fill(prevPosArr, -1);
        
        for (int i = 0; i < size; i++) {
            int pairNum = row[i] / 2;
            int currPos = i / 2;
            int prevPos = prevPosArr[pairNum];
            
            if (prevPos == -1) {
                prevPosArr[pairNum] = currPos;
            } else {
                int setA = dsu.find(prevPos);
                int setB = dsu.find(currPos);
                if (setA != setB) {
                    dsu.union(setA, setB);          // merge currPos and prevPos
                }
            }
        }
        for (int i = 0; i < halfSize; i++) {
            if (dsu.find(i) == i) {
                output += dsu.rank[i] - 1;          // main logic
            }
        }
        return output;
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }
        
        public int find(int curr) {
            if (parent[curr] == curr) {
                return curr;
            }
            return parent[curr] = find(parent[curr]);
        }
        
        public void union(int a, int b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
                rank[a] += rank[b];
            } else {
                parent[a] = b;
                rank[b] += rank[a];
            }
        }
    }
}