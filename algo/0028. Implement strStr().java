class Solution 
{
//     NAIVE
//     public int strStr(String text, String patt) 
//     { 
//         int textLen = text.length(), pattLen = patt.length();
//         if(pattLen == 0) return 0;
//         if(textLen < pattLen) return -1;
//         int j;
        
//         for(int i = 0; i <= textLen - pattLen; i++)
//         {          
//             for(j = 0; j < pattLen && text.charAt(i + j) == patt.charAt(j); j++);
            
//             if(j == pattLen)
//                 return i;
//         }
//         return -1;
//     }
 
//  KMP    
    public int strStr(String text, String patt) 
    {        
        int textLen = text.length(), pattLen = patt.length();
        if(pattLen == 0) return 0;
        if(textLen < pattLen) return -1;
        
        int[] LPS = preProcess(patt, pattLen);        
        int i=0, j=0;
        
        while(i<textLen)
        {
            if(text.charAt(i) == patt.charAt(j))
            {
                i++;
                j++;                
                if(j == pattLen)
                {
                    return i-j;
                    //j = LPS[j-1];
                }
            }                        
            else
            {
                if(j == 0)
                    i++;                   
                else
                    j = LPS[j-1];   // set back j to correct position
            }
        }
        
        return -1;
    }
    
    public int[] preProcess(String patt, int pattLen)
    {
        int[] LPS = new int[pattLen];
        int j = 0, i = 1;
        // LPS[0] = 0; 
        
        while(i<pattLen)
        {
            if(patt.charAt(i) == patt.charAt(j))
            {
                j++;
                LPS[i] = j;
                i++;
            }
            else
            {
                if(j == 0)
                    LPS[i++] = 0;
                else
                   j = LPS[j-1];    // set back j to correct position
            }
        }
        
        return LPS;
    }
}