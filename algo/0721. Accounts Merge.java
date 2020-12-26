/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]

Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Note:
The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
*/



/*
Logic:
If an email id is already present in some other name, then we need to merge both the names.
In the given ex: (1,3), (2), (4) are the names grouped after the merge is completed.
Assume these names as vertex in graph.
Now the problem reduced to "connected components in a graph".

Connected components in a graph can be found using normal DFS or also using DSU.

Comparison between DFS and DSU:

The task that DSU achieves in this code can be done using DFS as well. 
You should to code the same using DFS too.
Though, keep in mind that DFS is not a replacement for DSU. DFS works well in cases when all edges are present in the graph from the beginning. 
But, in problems where edges are added during the execution and we need to run connectivity queries in between such additions, DSU is the better option. 

An example of this type of situation is the Kruskal's algorithm to find the Minimum Spanning Tree (MST).
In Kruskal's algorithm, before adding an edge to the MST we need to check if the addition of the edge creates a cycle or not. We can use DSU here. If the parents of the two nodes that the edge connects are same, then we know that addition of the edge will create a cycle.
*/

// DSU
class Solution 
{
    public class Node                       // each user is a node 
    {
        Node parent;
        int rank;
        String name;
        List<String> emails;
        
        public Node(String name) 
        {
            this.name = name;
            this.emails = new ArrayList<>();
            this.parent = this;
            this.rank = 1;
        }
    }
    
    public Node findParent(Node node)       // path compression
    {
        if(node.parent != node) {
            node.parent = findParent(node.parent);
        }
        return node.parent;
    }
    
    public void union(Node n1, Node n2)     // union by rank
    {
        if(n1.rank >= n2.rank) {
            n1.rank += n2.rank;
            n2.parent = n1;
        }
        else {
            n2.rank += n1.rank;
            n1.parent = n2;
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) 
    {
        Map<String, Node> emailToNodeMap = new HashMap<>();
        List<Node> allNodeList = new ArrayList<>();
        String name, email;
        
        for(List<String> account : accounts) 
        {
            name = account.get(0);
            Node node = new Node(name);
            allNodeList.add(node);
            
            for(int i = 1; i < account.size(); i++) 
            {
                email = account.get(i);
                if(!emailToNodeMap.containsKey(email))     // new email
                {
                    emailToNodeMap.put(email, node);
                    node.emails.add(email);
                }
                else                                        // existing email, so do find-union
                {
                    Node currentNodeParent = findParent(node);
                    Node existingNodeParent = findParent(emailToNodeMap.get(email));
                    union(currentNodeParent, existingNodeParent);
                }
            }
        }
        
        for(Node node : allNodeList) 
        {
            if(node.parent != node) 
            {
                Node parentNode = findParent(node);
                parentNode.emails.addAll(node.emails);      // merge accounts
            }
        }
        
        List<List<String>> outputList = new ArrayList<>();
        List<String> currList;
        for(Node node : allNodeList)                        // form output
        {
            if(node.parent == node) 
            {
                currList = new ArrayList<>();
                currList.add(node.name);
                Collections.sort(node.emails);              // sort the emails
                currList.addAll(node.emails);
                outputList.add(currList);
            }
        }
        return outputList;
    }
}
