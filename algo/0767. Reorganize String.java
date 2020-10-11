/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""

Note:
S will consist of lowercase letters and have length in range [1, 500].
*/

class Solution 
{
    /*
        Greedy + maxHeap: Time = N * log N and Space = N
        Without priorityQueue: Time = N, Space = 26 --> https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
    */
    
    public String reorganizeString(String S)    
    {
        if(S == null || S.length() <= 1) {
            return S;
        }
        
        int[] countArr = new int[26];
        int exceedLimit = (S.length() + 1) / 2;
        for(int i = 0; i < S.length(); i++) 
        {
            countArr[S.charAt(i) - 'a']++;
            if(countArr[S.charAt(i) - 'a'] > exceedLimit) {
                return "";
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return b.count - a.count;   // max heap (descending order)
            }
        });
        
        for(int i = 0; i < 26; i++) 
        {
            if(countArr[i] != 0) {
                pq.add(new Node((char)(i + 'a'), countArr[i]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty())
        {
            Node first = pq.poll();
            sb.append(first.ch);
            
            Node second = null;
            if(!pq.isEmpty()) 
            {
                second = pq.poll();
                sb.append(second.ch);
            }
            
            if(--first.count > 0) {
                pq.add(first);
            }
            if(second != null && --second.count > 0) {
                pq.add(second);
            }
        }
        return sb.toString();
    }
    
    class Node
    {
        char ch;
        int count;
        Node(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}