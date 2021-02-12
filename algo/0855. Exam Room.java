/*
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  
If there are multiple such seats, they sit in the seat with the lowest number.  
(Also, if no one is in the room, then the student sits at seat number 0.)
Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  
It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

Example 1:
Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]

Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
​​​​​​
Note:
1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
*/


/*
    1) maintain sorted position list
        Logic: create a arraylist and store seats in ascending order
        Time: p and p
        Space: p
        where p is the number of students sitting in exam room
        
    2) treeset
        Logic: store the x, y, dist in treeset and use comparator for dist in descending order
        ex: 0, 9
        seat    --> (add: 0, 4),    (add: 4, 9),    (remove: 0, 9)
        leave 4 --> (remove: 0, 4), (remove: 4, 9), (add: 0, 9)
        
        using pollFirst() in treeset we can do seat() in O(log(p))
        using lower() and higher() in treeset we can do leave() in O(log(p))
        
        Time: 3log(p) and 3log(p)
        Space: p
        where p is the number of students sitting in exam room
*/

class ExamRoom {
    TreeSet<Integer> seatsSet;
    TreeSet<Interval> intervalSet;
    int n;
    
    public ExamRoom(int N) {
        this.n = N;
        this.seatsSet = new TreeSet<Integer>();
        this.intervalSet = new TreeSet<Interval>((a, b) -> (a.dist != b.dist ? b.dist - a.dist : a.x - b.x));   // main logic
        intervalSet.add(new Interval(-1, n));
    }
    
    public int seat() {
        int newSeatNum;
        Interval interval = intervalSet.pollFirst();                        // main logic: take max distance
        
        if (interval.x == -1) {
            newSeatNum = 0;
            intervalSet.add(new Interval(newSeatNum, interval.y));
        } else if (interval.y == this.n) {
            newSeatNum = n - 1;
            intervalSet.add(new Interval(interval.x, newSeatNum));
        } else {
            newSeatNum = interval.x + ((interval.y - interval.x) / 2);      // main logic
            intervalSet.add(new Interval(interval.x, newSeatNum));
            intervalSet.add(new Interval(newSeatNum, interval.y));
        }
        seatsSet.add(newSeatNum);
        return newSeatNum;
    }
    
    public void leave(int p) {
        Integer left = seatsSet.lower(p);                                   // main logic
        Integer right = seatsSet.higher(p);                                 // main logic
        left = (left == null) ? -1 : left;
        right = (right == null) ? n : right;
        
        intervalSet.remove(new Interval(left, p));
        intervalSet.remove(new Interval(p, right));
        intervalSet.add(new Interval(left, right));
        seatsSet.remove(p);
    }
    
    class Interval {
        int x, y, dist;
        Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                dist = y;
            } else if (y == n) {
                dist = n - 1 - x;
            } else {
                dist = (y - x) / 2;                                         // main logic
            }
        }
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */