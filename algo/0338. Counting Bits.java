/*
Time = O(n), Space = O(n)
Logic: answer for 16 is, 
       binary of 16 is "10000" --> which is ("1" + "0000"), where "0000" is already solved --> So answer = 1 + binary of 0
       binary of 17 is "10001" --> which is ("1" + "0001"), where "0001" is already solved --> So answer = 1 + binary of 1
       
       Base case is, output[0] = 0 and output[1] = 1
       Main case is,
            output[2] = 1 + output[0], output[3] = 1 + output[1]
            output[4] = 1 + output[0], output[5] = 1 + output[1], output[6] = 1 + output[2], output[7] = 1 + output[3]
            ....
*/

class Solution
{
    public int[] countBits(int num) 
    {
        int[] output = new int[num+1];
        output[0] = 0;
        if(num == 0) {
            return output;
        }
        output[1] = 1;
        
        int val = 2;    // val will be 2, 4, 8, 16,...
        for(int i = 2; i <= num; i++)
        {
            if(i == val * 2) {
                val *= 2;
            }
            output[i] = 1 + output[i-val];  // main logic
        }
        return output;
    }
}


/*
Time = O(n*sizeof(integer)), Space = 1

class Solution 
{
    public int[] countBits(int num) 
    {
        int[] output = new int[num+1];
        for(int i = 0; i <= num; i++) {
            output[i] = numberOfSetBits(i);
        }
        return output;
    }
    
    public int numberOfSetBits(int n)
    {
        int count = 0;
        while(n > 0) 
        {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }
}
*/