/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

class Solution 
{   
    public int kthSmallest(int[][] matrix, int k)
    {
        // Logic: binary search based on value (instead of index)       
        // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/301357/Java-0ms-(added-Python-and-C%2B%2B)%3A-Easy-to-understand-solutions-using-Heap-and-Binary-Search
        
        int n = matrix.length;
        int lowVal = matrix[0][0];
        int highVal = matrix[n - 1][n - 1];
        
        while(lowVal < highVal)
        {
            int midVal = lowVal + ((highVal - lowVal) / 2);
            int count = count(matrix, midVal);
            
            // if(count == k) {
            //     return lowVal;
            // } 
            if(count < k) {  
                lowVal = midVal + 1;  // search right
            } else {            
                highVal = midVal;     // search left
            }
        }
        return highVal;
    }
    
    /* 
    returns count of elements <= midVal in matrix --> main logic
    Time complexity = N. 
    This algo is similar to find a element in row-sorted and col-sorted matrix.
    There we start from top right. Here we start from bottom left
    */    
    public int count(int[][] matrix, int midVal)
    {
        int count = 0;
        int n = matrix.length;
        int i = n - 1, j = 0;
        
        while(i >= 0 && j < n)
        {
            if(matrix[i][j] <= midVal) {
                count += i + 1;
                j++;
            }
            else {
                i--;
            }
        }
        return count;
    }
}
    
    
/*
Heap Solution --> Time: O(heapSize + (k * log heapSize)), where heapSize = min(n, k)

class Solution 
{
    public class Node
    {
        int val, row, col;
        Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k)  
    {
        int n = matrix.length;
        int heapSize = Math.min(n, k);
        Node[] heap = new Node[heapSize];
        
        for(int i = 0; i < heapSize; i++)
        {
            heap[i] = new Node(matrix[0][i], 0, i);
            // no need to heapify because matrix is sorted
        }
        
        for(int i = 0; i < k - 1; i++)
        {
            Node root = heap[0];
            root.val = (root.row < n - 1) ? matrix[root.row + 1][root.col] : Integer.MAX_VALUE;                
            root.row = root.row + 1;
            // root.col wont change
            minHeapify(heap, 0, heapSize);
        }
        return heap[0].val;
    }
    
    public void minHeapify(Node[] heap, int i, int size)
    {
        int smallest = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        
        if(left < size && heap[left].val < heap[smallest].val) {
            smallest = left;
        }
        if(right < size && heap[right].val < heap[smallest].val) {
            smallest = right;
        }
        
        if(smallest != i) {
            swap(heap, i, smallest);
            minHeapify(heap, smallest, size);
        }
    }
    
    public void swap(Node[] arr, int i, int j) 
    {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}    
*/