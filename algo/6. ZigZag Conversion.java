// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"

// Write the code that will take a string and make this conversion given a number of rows:
// string convert(string s, int numRows);

// Example 1:
// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"

// Example 2:
// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
// P     I    N
// A   L S  I G
// Y A   H R
// P     I

class Solution 
{
    public String convert(String s, int numRows) 
    {
        if(numRows == 1)
            return s;
        
        int length = s.length();
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        
        int noOfRows = Math.min(length, numRows);
        for(int i=0; i<noOfRows; i++)
            list.add(new StringBuilder());
        
        
        int row = 0;
        boolean flipDir = false;
        StringBuilder sb;
        
        for(int i=0; i<length; i++)
        {
            sb = list.get(row);
            sb.append(s.charAt(i));
            if(row == 0 || row == noOfRows-1)
                flipDir = !flipDir;
            row += (flipDir) ? 1 : -1;              // main logic
        }
        
        StringBuilder output = new StringBuilder();
        for(int i=0; i<noOfRows; i++)
            output = output.append(list.get(i));

        return output.toString();
    }
}
