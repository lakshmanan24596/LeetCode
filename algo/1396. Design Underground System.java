/*
Implement the UndergroundSystem class:
void checkIn(int id, string stationName, int t)
A customer with a card id equal to id, gets in the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card id equal to id, gets out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time to travel between the startStation and the endStation.
The average time is computed from all the previous traveling from startStation to endStation that happened directly.
Call to getAverageTime is always valid.
You can assume all calls to checkIn and checkOut methods are consistent. 
If a customer gets in at time t1 at some station, they get out at time t2 with t2 > t1. 
All events happen in chronological order.

Example 1:
Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);
undergroundSystem.checkOut(27, "Waterloo", 20);
undergroundSystem.checkOut(32, "Cambridge", 22);
undergroundSystem.getAverageTime("Paradise", "Cambridge");       // return 14.00000. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.00000
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);
undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 12.00000

Example 2:
Input
["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
[[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
Output
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, "Leyton", 3);
undergroundSystem.checkOut(10, "Paradise", 8);
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000
undergroundSystem.checkIn(5, "Leyton", 10);
undergroundSystem.checkOut(5, "Paradise", 16);
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000
undergroundSystem.checkIn(2, "Leyton", 21);
undergroundSystem.checkOut(2, "Paradise", 30);
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 6.66667

Constraints:
There will be at most 20000 operations.
1 <= id, t <= 106
All strings consist of uppercase and lowercase English letters, and digits.
1 <= stationName.length <= 10
Answers within 10-5 of the actual value will be accepted as correct.
*/


/*
    Logic 1: Time: 1, 1, n
    map1 --> store checkin details with key=checkInStation and value is a map with key=id, value=time
    map2 --. store checkout details with key=checkOutStation and value is a map with key=id, value=time
    getAverageTime --> it needs iteration. time= min(checkin in t)
    
    Logic 2: Time: 1, 1, 1
    map1 --> key=(uid), value=(station, time) 
    map2 --> key=(stationObj), value=(totalTime, count)
    Intutions:
        1) First checkin need to be made. Only then checkout is possible.
        2) A person can be present in 1 station at a time.
        So during checkout, we can find checkin details of that userId.
*/

class UndergroundSystem {
    Map<Integer, Object[]> checkInMap;  // key=(uid), value=(station, time) 
    Map<Station, int[]> checkOutMap;    // key=(stationObj), value=(totalTime, count)
    
    public UndergroundSystem() {
        checkInMap = new HashMap<Integer, Object[]>();
        checkOutMap = new HashMap<Station, int[]>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Object[] {stationName, t});
    }
    
    public void checkOut(int id, String stationName, int t) {
        Object[] checkinDetails = checkInMap.get(id);
        if (checkinDetails == null) {
            return;   
        }
        Station station = new Station((String) checkinDetails[0], stationName);
        int time = t - ((int) checkinDetails[1]);
        int[] checkOutDetails = checkOutMap.get(station);
        if (checkOutDetails == null) {
            checkOutDetails = new int[2];
            checkOutMap.put(station, checkOutDetails);
        }
        checkOutDetails[0] += time;
        checkOutDetails[1] += 1;
        checkInMap.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        Station station = new Station(startStation, endStation);
        int[] checkOutDetails = checkOutMap.get(station);                       // main logic
        if (checkOutDetails == null) {
            return 0.0;
        }
        int totalTime = checkOutDetails[0];
        int count = checkOutDetails[1];
        return totalTime / (double) count;
    }
    
    class Station {
        String from;
        String to;
        
        Station(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.from, this.to);
        }

        @Override
        public boolean equals(Object obj) {
            Station curr = (Station) obj;
            return (curr.from.equals(this.from)) && (curr.to.equals(this.to));
        }
    }
    
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */