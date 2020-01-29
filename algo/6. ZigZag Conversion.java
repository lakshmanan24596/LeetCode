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
        {
            list.add(new StringBuilder());
        }
        
        int row = 0;
        boolean flipDir = false;
        for(int i=0; i<length; i++)
        {
            StringBuilder sb = list.get(row);
            sb.append(s.charAt(i));
            if(row == 0 || row == noOfRows-1)
                flipDir = !flipDir;
            row += (flipDir) ? 1 : -1;              // main logic
        }
        
        StringBuilder output = new StringBuilder();
        for(int i=0; i<noOfRows; i++)
        {
            output = output.append(list.get(i));
        }
        return output.toString();
    }
}