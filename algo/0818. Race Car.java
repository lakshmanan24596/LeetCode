/*
Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.

Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.

Note:
1 <= target <= 10000.
*/



/*
    We dont know about the base case (neg end and pos end) in this problem
    So plain dfs cannot be done.
    
    BFS is very straightforward with two important pruning
        when current position is negative, there is no need to iterate it
        when current position is bigger than 2 times of target, there is no need to iterate it
        
        
    https://leetcode.com/problems/race-car/discuss/123920/UpdatJava-Accepted-straightforward-Solution-with-BFS-pruning-and-my-explanation-about-DP-Solution
    https://leetcode.com/problems/race-car/discuss/124326/Summary-of-the-BFS-and-DP-solutions-with-intuitive-explanation
*/


// not an optimal solution
class Solution {
    public int racecar(int target) {
        Node car = new Node(0, 1);
        Queue<Node> carQueue = new LinkedList<Node>();
        carQueue.add(car);
        Set<Node> visited = new HashSet<Node>();
        visited.add(car);
        int instructions = 0;
        int carQueueSize;
        
        while (!carQueue.isEmpty()) {
            carQueueSize = carQueue.size();
            while (carQueueSize-- > 0) {
                car = carQueue.remove();
                if (car.pos == target) {
                    return instructions;
                }
                
                int nextPos = car.pos + car.speed;
                int nextSpeed = car.speed * 2;
                Node nextCar = new Node(nextPos, nextSpeed);
                if (nextPos < target * 2 && nextPos > 0 && !visited.contains(nextCar)) {
                    visited.add(nextCar);
                    carQueue.add(nextCar);
                }
                
                nextPos = car.pos;
                nextSpeed = (car.speed > 0) ? -1 : 1;
                nextCar = new Node(nextPos, nextSpeed);
                if (nextPos > 0 && !visited.contains(nextCar)) {
                    visited.add(nextCar);
                    carQueue.add(nextCar);
                }
            }
            instructions++;
        }
        return instructions;
    }
    
    class Node {
        int pos;
        int speed;
        
        Node(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.pos, this.speed);
        }
        
        @Override
        public boolean equals(Object car) {
            Node carNode = (Node) car;
            return (carNode.pos == this.pos) && (carNode.speed == this.speed);
        }
    }
}