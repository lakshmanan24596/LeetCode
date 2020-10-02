/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

class Solution 
{
	HashMap<String,List<String>> map;
	Set<String> wordDictSet;

	public List<String> wordBreak(String s, List<String> wordDict) 
	{
		wordDictSet = new HashSet<String>(wordDict);
		map = new HashMap<String,List<String>>();
		return recur(s);
	}

	public List<String> recur(String input) 
	{   
		if(map.containsKey(input)) {
			return map.get(input);
		}

		List<String> output = new ArrayList<String>();
		int n = input.length();
		String prefix, suffix;

		for(int i = 1; i <= n; i++)
		{
			prefix = input.substring(0, i); 
			if(wordDictSet.contains(prefix))
			{
				if(i == n)
				{
					output.add(prefix);
					return output;
				}	

				suffix = input.substring(i, n); 			
				List<String> currOutput = recur(suffix);
				for(String curr : currOutput)
				{
					output.add(prefix + " " + curr);
				}
			}
		}

		map.put(input, output);
		return output;
	}
}