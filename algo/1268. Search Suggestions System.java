/*
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. 
If there are more than three products with a common prefix return the three lexicographically minimums products.
Return list of lists of the suggested products after each character of searchWord is typed. 

Example 1:
Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]

Example 2:
Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

Example 3:
Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]

Example 4:
Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]

Constraints:
1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
*/


/*
    1) Binary search
        Sort the product and we can do binary search
        
    2) Trie
        Time: 
            insert = products = O(products * products.length)
            dfs = searchWord = O(len(searchWordPrefix)) + 3
        Space: 
            trie = 26 * 26
            outputList = O(len(searchWord))
            
    3) Sliding window:
        Trie will be good if we have to query many times with different search word
        So sort products and maintain a window and shrink the window as we see chars in search word
*/

class Solution {
    int alphaSize = 26;
    int reqOutput = 3;
    int a = 97; // ascii value
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode head = new TrieNode();
        TrieNode curr;
        int index;
        List<List<String>> output = new ArrayList<List<String>>();
        List<String> currOutput;
        StringBuilder sb = new StringBuilder();
        
        for (String product : products) {   // create trie
            insert(head, product);
        }
        
        curr = head;
        for (char ch : searchWord.toCharArray()) { // iterate searchWord
            currOutput = new ArrayList<String>();
            index = ch - 'a';
            if(curr.children[index] == null) {
                break;
            }
            curr = curr.children[index];
            sb = sb.append(ch);
            dfs(curr, sb, currOutput);  // main logic: do dfs for each letter of search word
            output.add(currOutput);
        }
        for(int i = output.size(); i < searchWord.length(); i++) {
            output.add(new ArrayList<String>());
        }
        return output;
    }
    
    public void insert(TrieNode curr, String product) {
        int index; 
        for (char ch : product.toCharArray()) {
            index = ch - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isLeaf = true;
    }
    
    public void dfs(TrieNode curr, StringBuilder sb, List<String> currOutput) {
        if (curr.isLeaf) {
            currOutput.add(sb.toString());
        }
        for (int i = 0; i < alphaSize && currOutput.size() < reqOutput; i++) {   // additional check to limit to 3 outputs
            if (curr.children[i] != null) {
                sb = sb.append((char)(i + a));
                dfs(curr.children[i], sb, currOutput);
                sb = sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    class TrieNode {
        TrieNode[] children;
        boolean isLeaf = false;
        TrieNode() {
            children = new TrieNode[alphaSize];
        }
    }
}


/*
    Logic:
        Trie will be good if we have to query many times with different search word
        So sort products and maintain a window and shrink the window as we see chars in search word
    Time: 
        n * logn for sorting products
        searchWord + products for processing
    Execution time:
        89 ms for trie
        6 ms for sorting + sliding window
*/
/*
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new LinkedList<>();
        List<String> eachRes;
        char c;
        Arrays.sort(products);
        int start = 0, end = products.length - 1;
    
        for (int i = 0; i < searchWord.length(); i ++) {
            c = searchWord.charAt(i);
            eachRes = new LinkedList<>();
            
            while (start <= end && (i >= products[start].length() || products[start].charAt(i) != c)) {
                start++;
            }
            while (start <= end && (i >= products[end].length() || products[end].charAt(i) != c)) {
                end--;
            }
            for (int j = start; j < start + 3 && j <= end; j++) {
                eachRes.add(products[j]);
            }
            res.add(eachRes);
        }
        return res;
    }
}
*/