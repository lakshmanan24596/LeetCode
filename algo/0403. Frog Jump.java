/*
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. 
Initially, the frog is on the first stone and assume the first jump must be 1 unit.
If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:
The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.

Example 1:
[0,1,3,5,6,8,12,17]
There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit, third stone at the 3rd unit, and so on... The last stone at the 17th unit.
Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
[0,1,2,3,4,8,9,11]
Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
*/

class Solution 
{
    int[] stones;
    HashSet<Integer> stonesSet = new HashSet();
    HashSet<String> DP = new HashSet<String>();             // put false data in DP.. States in DP are --> prevJump, currPosition     
    int nextPosition;
    String dpVal;
    
    public boolean canCross(int[] stones) 
    {
        int prevJump = 1;
        int currPosition = 1;
        this.stones = stones;        
        
        if(stones[0] != 0 || stones[1] != 1)                // because initially only 1 jump can be made
        {
            return false;
        } 
        for (int i = 3; i < stones.length; i++)             // to deduce faster, if output may be false 
        {
            if (stones[i] > stones[i - 1] * 2) 
            {
                return false;
            }
        }
        
        for(int stonePosition : stones)
        {
            stonesSet.add(stonePosition);
        }     
        return recur(prevJump, currPosition);
    }
    
    // memorization
    public boolean recur(int prevJump, int currPosition)
    {
        if(currPosition == stones[stones.length - 1])
        {
            return true;
        }
        
        dpVal = prevJump + "," + currPosition;              // these are states of DP
        if(DP.contains(dpVal))
        {
            return false;
        }
        
        for(int k = prevJump + 1; k >= prevJump - 1; k--)   // decrement, bcos (prevJump + 1) has more possibility to become true
        {
            nextPosition =  currPosition + k;               // here k is currJump
            if(k != 0 && stonesSet.contains(nextPosition))  // k == 0 means, jump to same position.. so avoid it
            {
                if(recur(k, nextPosition))
                {
                    return true;
                }
            }
        }
        
        DP.add(dpVal);
        return false;
    }
}

//class Data
//{   
//    int prevJump;
//    int currPosition;
//    Data(int prevJump, int currPosition)
//    {
//        this.prevJump = prevJump;
//        this.currPosition = currPosition;
//    }
//    
//    @Override
//    public boolean equals(Object obj) 
//    {
//    	  Data data = (Data)obj;
//        return (this.prevJump == data.prevJump && this.currPosition == data.currPosition);
//    }   
//
//    @Override
//    public int hashCode() 
//    {
//        return this.prevJump + this.currPosition;
//    }
//}