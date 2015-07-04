package com.xala3pa.twitter.backend

import com.xala3pa.twitter.tweets.Tweet

class Repository {

	private Map userTimeline = [:]
    private Map userFollowers = [:]
	private Views view = new Views()

    void saveFollower(String user, String follower) {
        List followersList = getFollowers(user)
        followersList << follower

        userFollowers.put(user, followersList)
    }

	void saveUserTweet(String user, Tweet tweet) {
        List tweets = getUserTweets(user)
        tweets << tweet

        userTimeline.put(user, tweets)
    }

    void showUserTimeline(String user) {
        List tweets = getUserTweets(user)
        view.showUserTimeline(tweets)
    }

    void showWall(String user) {
        List userWall = []
        List selfWall = getUserTweets(user)
        userWall.addAll(selfWall)

        List followersList = getFollowers(user)
        followersList.each() { follower ->
            userWall.addAll(getUserTweets(follower))
        }

        view.showUserTimeline(userWall, true)
    }

	private List getUserTweets(String user) {
        List tweets = []
        if (userTimeline.containsKey(user)) {
            tweets = userTimeline.get(user)
        } else {
            userTimeline.put(user, tweets)
        }
        return tweets
    }

    private List getFollowers(String user) {
        List followersList = []
        if (userFollowers.containsKey(user)) {
            followersList = userFollowers.get(user)
        } else {
            userFollowers.put(user, followersList)
        }
        return followersList
    }
}