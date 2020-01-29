class Solution 
{
    public int reverse(int n) 
    {
        int output = 0;
        while(n!=0)
        {
            int digit = n % 10;
            if (output > Integer.MAX_VALUE/10 || (output == Integer.MAX_VALUE/10 && digit > 7)) return 0;   // corner case
	        if (output < Integer.MIN_VALUE/10 || (output == Integer.MIN_VALUE/10 && digit < -8)) return 0;  // corner case
            output = digit + (output * 10);
            n = n / 10;
        }
        return output;
    }
}