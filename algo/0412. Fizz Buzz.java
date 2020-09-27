/*
Write a program that outputs the string representation of numbers from 1 to n.
But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:
n = 15,
Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/

class Solution 
{
    public List<String> fizzBuzz(int n) 
    {
        List<String> output = new ArrayList<String>();
        boolean three = false, five = false;
        
        for(int i = 1; i <= n; i++) 
        {
            three = i % 3 == 0;
            five = i % 5 == 0;
            
            if(three && five) {
                output.add("FizzBuzz");
            } else if(three) {
                output.add("Fizz");
            } else if(five) {
                output.add("Buzz");
            } else {
                output.add(String.valueOf(i));
            }
        }
        return output;
    }
}