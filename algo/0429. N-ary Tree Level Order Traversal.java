/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/*
class Solution 
{
    public List<List<Integer>> levelOrder(Node root)    // Time: N
    {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if(root == null) {
            return output;
        }
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        List<Integer> currList;
            
        while(!queue.isEmpty())
        {
            currList = new ArrayList<Integer>();
            int size = queue.size();
            
            while(size-- > 0)
            {
                Node curr = queue.remove();
                currList.add(curr.val);
                
                List<Node> children = curr.children;
                for(Node child : children) {
                    queue.add(child);
                }
            }
            output.add(currList);
        }
        
        return output;
    }
}
*/

class Solution 
{
    List<List<Integer>> output;
    
    public List<List<Integer>> levelOrder(Node root)    // Time: N
    {
        output = new ArrayList<List<Integer>>();
        if(root == null) {
            return output;
        }
        
        levelOrder(root, 0);
        return output;
    }
    
    public void levelOrder(Node curr, int level)
    {
        if(output.size() <= level) {
            output.add(new ArrayList<Integer>());
        }
    
        output.get(level).add(curr.val);
        
        List<Node> children = curr.children;
        for(Node child : children) {
            levelOrder(child, level + 1);
        }
    }
}
