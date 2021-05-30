/*
You are given several logs, where each log contains a unique ID and timestamp.
Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, 
for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Implement the LogSystem class:
LogSystem() Initializes the LogSystem object.
void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
int[] retrieve(string start, string end, string granularity) Returns the IDs of the logs whose timestamps are within the range from start to end inclusive. start and end all have the same format as timestamp, and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.). For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day" means that we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute, and Second for each log entry can be ignored.

Example 1:
Input
["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
[[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
Output
[null, null, null, null, [3, 2, 1], [2, 1]]

Explanation
LogSystem logSystem = new LogSystem();
logSystem.put(1, "2017:01:01:23:59:59");
logSystem.put(2, "2017:01:01:22:59:59");
logSystem.put(3, "2016:01:01:00:00:00");

// return [3,2,1], because you need to return all logs between 2016 and 2017.
logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");

// return [2,1], because you need to return all logs between Jan. 1, 2016 01:XX:XX and Jan. 1, 2017 23:XX:XX.
// Log 3 is not returned because Jan. 1, 2016 00:00:00 comes before the start of the range.
logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
 

Constraints:
1 <= id <= 500
2000 <= Year <= 2017
1 <= Month <= 12
1 <= Day <= 31
0 <= Hour <= 23
0 <= Minute, Second <= 59
granularity is one of the values ["Year", "Month", "Day", "Hour", "Minute", "Second"].
At most 500 calls will be made to put and retrieve.
*/


/*
    binary search or treeMap
    time: put=logn, retrieve=logn
    space: n

    In below solution, we have converted string to long
    we can also insert string into the treeMap, by this way, put() will be faster but retrieve() will be slow because processing with strings will to slower
    So convert string to long using --> Long.valueOf(timeStamp.replaceAll(":", ""));

    treemap.submap --> fromKey, isFromInclusive, toKey, isToInclusive
    this is used to get all logIds within start and end range
*/

class LogSystem {
    static final String MIN_TIME = "2000:01:01:00:00:00";
    static final String MAX_TIME = "2017:12:31:23:59:59";
    Map<String, Integer> timeUnitMap;
    TreeMap<Long, Integer> timeMap;                     // main logic: (key, value) = (timeStamp, logId)
    
    public LogSystem() {
        initializeIndexMap();
        timeMap = new TreeMap<Long, Integer>();
    }
    
    public void initializeIndexMap() {
        timeUnitMap = new HashMap<String, Integer>();   // store index of colon in timeStamp
        timeUnitMap.put("Year", 4);
        timeUnitMap.put("Month", 7);
        timeUnitMap.put("Day", 10);
        timeUnitMap.put("Hour", 13);
        timeUnitMap.put("Minute", 16);
        timeUnitMap.put("Second", 19);
    }
    
    public void put(int id, String timestamp) {
        Long time = convertTime(timestamp);
        timeMap.put(time, id);
    }
    
    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> ids = new ArrayList<Integer>();
        Long startTime = convertTime(start, granularity, true);
        Long endTime = convertTime(end, granularity, false);
        
        Map<Long, Integer> subMap = timeMap.subMap(startTime, true, endTime, true);     // main logic
        ids.addAll(subMap.values());
        return ids;
    }
    
    public Long convertTime(String timeStamp, String granularity, boolean isStart) {
        int granularityIndex = timeUnitMap.get(granularity);
        timeStamp = timeStamp.substring(0, granularityIndex);
        timeStamp += (isStart) ? 
                        MIN_TIME.substring(granularityIndex) : 
                        MAX_TIME.substring(granularityIndex);
        return convertTime(timeStamp);
    }
    
    public Long convertTime(String timeStamp) {
        return Long.valueOf(timeStamp.replaceAll(":", ""));     // main logic
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(start,end,granularity);
 */