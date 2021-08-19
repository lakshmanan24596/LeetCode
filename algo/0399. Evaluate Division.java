/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. 
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. 
You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/


/*
    1) Graph DFS
        given values are --> a/b and b/c
        observation:
            1) a/b * b/c = a/c  (main logic)
            2) if a/b = 2, then b/a = 1/2
            3) a/a = 1
            4) a/e = -1, because the value of e is not known to us
            
        to know query value of a/c,
            we can create a graph with a, b, c as nodes and values[] as edgeWeights
            so a/c = (edgeWeight of a/b) * (edgeWeight of b/c)
            basically we need to traverse the graph (brute force dfs) from numerator till we find denominator
            if no path is found then return -1, else return cummulative product of edgeWeight
    
        time = O(equations + (queries * equations))
        space = O(equations)
        
    2) DSU
        
*/

class Solution {
    Map<String, Map<String, Double>> graph;
    Set<String> visited;
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        createGraph(equations, values);
        double[] equationResult = new double[queries.size()];
        List<String> query;
        String numerator, denominator;
        
        for (int i = 0; i < queries.size(); i++) {                  // process each query
            query = queries.get(i);
            numerator = query.get(0);
            denominator = query.get(1);
            
            if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                 equationResult[i] = -1.0;
            } else {
                this.visited = new HashSet<String>();
                equationResult[i] = getDivisionResult(numerator, denominator);
            }
        }
        return equationResult;
    }
    
    public Double getDivisionResult(String curr, String denominator) {
        if (curr.equals(denominator)) {
            return 1.0;
        }
        visited.add(curr);
        Double divisionResult = -1.0;
        Map<String, Double> adjacents = graph.get(curr);
        String neigh;
        Double currWeight, neighWeight;
        
        for (Map.Entry<String, Double> adjEntry : adjacents.entrySet()) {
            neigh = adjEntry.getKey();
            currWeight = adjEntry.getValue();
            
            if (!visited.contains(neigh)) {
                neighWeight = getDivisionResult(neigh, denominator);    // dfs
                if (neighWeight != -1) {
                    return currWeight * neighWeight;                    // multiply the cummulative path values
                }
            }
        }
        visited.remove(curr);
        return divisionResult;
    }
    
    public void createGraph(List<List<String>> equations, double[] values) {
        this.graph = new HashMap<String, Map<String, Double>>();
        List<String> equation;
        String numerator, denominator;
        Double value;
        
        for (int i = 0; i < equations.size(); i++) {
            equation = equations.get(i);
            numerator = equation.get(0);
            denominator = equation.get(1);
            value = values[i];
            
            graph.putIfAbsent(numerator, new HashMap<String, Double>());
            graph.putIfAbsent(denominator, new HashMap<String, Double>());
            graph.get(numerator).put(denominator, value);       // numerator / denominator
            graph.get(denominator).put(numerator, 1 / value);   // denominator / numerator (reverse the value)
        }
    }
}