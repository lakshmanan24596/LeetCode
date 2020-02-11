class Solution 
{
    public int romanToInt(String s) 
    {
        if(s.equals("")) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        formIntputMap(map);
        
        int len = s.length();      
            currVal, nextVal;
            output = 0;
        
        for(int i=0; i<len; i++)
        {
            currVal = map.get(s.charAt(i));
            if(i == len-1)
            {
                output += currVal;            
            }
            else
            {
                nextVal = map.get(s.charAt(i+1));            
                output += (currVal >= nextVal) ? (currVal) : (-currVal);    // compare curr and next val
            } 
        }
        
        return output;
    }
    
    public void formIntputMap(HashMap<Character, Integer> map)
    {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }
}