/*
Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

Implement the WordDistance class:
WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.

Example 1:
Input
["WordDistance", "shortest", "shortest"]
[[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
Output
[null, 3, 1]

Explanation
WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
wordDistance.shortest("coding", "practice"); // return 3
wordDistance.shortest("makes", "coding");    // return 1

Constraints:
1 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
At most 5000 calls will be made to shortest.
*/



/*
    Logic: HashTable + 2-pointer
    Time: constructor --> n, shortest --> O(occurence of w1 + occurence of w2)
    space: n
*/

class WordDistance {
    Map<String, List<Integer>> wordOccurences;
    
    public WordDistance(String[] wordsDict) {
        wordOccurences = new HashMap<String, List<Integer>>();
        List<Integer> indexes;
        String word;
        
        for (int i = 0; i < wordsDict.length; i++) {
            word = wordsDict[i];
            indexes = wordOccurences.get(word);
            if (indexes == null) {
                indexes = new ArrayList<Integer>();
                wordOccurences.put(word, indexes);
            }
            indexes.add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        if (word1 == null || word2 == null || word1.equals(word2)) {
            return 0;
        }
        List<Integer> indexes1 = wordOccurences.get(word1);
        List<Integer> indexes2 = wordOccurences.get(word2);
        int i1 = 0, i2 = 0;
        int index1, index2;
        int shortestDist = Integer.MAX_VALUE, currDist;
        
        while (i1 < indexes1.size() && i2 < indexes2.size()) {
            index1 = indexes1.get(i1);
            index2 = indexes2.get(i2);
            currDist = Math.abs(index1 - index2);
            shortestDist = Math.min(shortestDist, currDist);
            
            if (index1 < index2) {
                i1++;
            } else {
                i2++;
            }
        }
        return shortestDist;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */