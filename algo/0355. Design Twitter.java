/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 

Your design should support the following methods:
postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.

Example:
Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
*/


/*
    Logic: HashMap, MaxHeap, LinkedList
    getNewsFeed can be implemented using "merge K sorted Lists" using heap, where K = 10
    refer: https://www.geeksforgeeks.org/find-m-th-smallest-value-in-k-sorted-arrays/    

    Time:
        post, follow, unfollow --> O(1)
        getNewsFeed            --> O(FlogK + KlogK + KlogK), where F=friends, K=10
    Space:
        store all tweets            --> O(tweets)
        store friends for each user --> O(users * users)
        store output news feed      --> O(K) 
*/

class Twitter {
    Map<Integer, User> userMap;     // easy to find if user exist
    int currTime;
    int K;
    
    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<Integer, User>();
        currTime = 0;
        K = 10;
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeedResult = new ArrayList<Integer>();
        if(!userMap.containsKey(userId)) {
            return newsFeedResult;
        }
        PriorityQueue<Tweet> minHeap = new PriorityQueue<Tweet>((t1, t2) -> (t1.time - t2.time)); // minHeap of size K
        User user = getUser(userId);
        for (User friend : user.friends) {
            if (friend.tweetHead != null) {
                if (minHeap.size() < K) {
                    minHeap.add(friend.tweetHead);
                } else if (friend.tweetHead.time > minHeap.peek().time) {
                    minHeap.remove();
                    minHeap.add(friend.tweetHead);
                }
            }
        }
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<Tweet>((t1, t2) -> (t2.time - t1.time)); // maxHeap of size K
        while (!minHeap.isEmpty()) {
            maxHeap.add(minHeap.remove());                         // convert minHeap to maxHeap
        }
        while (!maxHeap.isEmpty() && newsFeedResult.size() < K) {  // implementation of merge K sorted Lists
            Tweet tweet = maxHeap.remove();
            newsFeedResult.add(tweet.id);
            if (tweet.next != null) {
                maxHeap.add(tweet.next);
            }
        }
        return newsFeedResult;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        getUser(userId).postTweet(tweetId);
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        getUser(followerId).follow(getUser(followeeId));
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        getUser(followerId).unfollow(getUser(followeeId));
    }
    
    public User getUser(int userId) {
        userMap.putIfAbsent(userId, new User(userId));
        return userMap.get(userId);
    }
    
    class User {
        Integer id;
        Tweet tweetHead;            // main logic: store recent tweet reference
        Set<User> friends;
        
        User(int userId) {
            id = userId;
            tweetHead = null;
            friends = new HashSet<User>();
            friends.add(this);      // follows itself
        }
        
        public void follow(User followee) {
            this.friends.add(followee);
        }
        
        public void unfollow(User followee) {
            this.friends.remove(followee);
        }
        
        public void postTweet(int tweetId) {
            Tweet tweet = getTweet(tweetId);
            tweet.next = this.tweetHead;
            this.tweetHead = tweet;
        }
    }
    
    class Tweet {
        Integer id;
        int time;
        Tweet next;
        
        Tweet(int tweetId) {
            id = tweetId;
            time = currTime++;
            next = null;
        }
        
        public Tweet getTweet(int tweetId) {
            return new Tweet(tweetId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */