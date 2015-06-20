package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.backend.Repo
import com.xala3pa.twitter.backend.Views
import com.xala3pa.twitter.tweets.Tweet

class RepoSpec extends Specification {

    private static final String USER = "Alvaro"
    private static final String OTHER_USER = "Peter"
    private static final String FOLLOWER = "Michael"

    Repo repo
    Views view
    Tweet tweet1
    Tweet tweet2
    Map userTimeline

    def setup() {
        repo = new Repo()
        view = Mock()

        repo.view = view
        tweet1 = new Tweet(user:"Alvaro", message:"My first tweet!",
            createTime:10l)
        tweet2 = new Tweet(user:"Peter", message:"My second tweet!",
            createTime:20l)
    }

    def "Save first follower"() {

        given: "a user and a follower"
        repo.userFollowers = ["Alvaro":[]]

        when: "We save the follower"
        repo.saveFollower(USER, OTHER_USER)

        then: "new follower is added "
        assert repo.userFollowers.get(USER).size() == 1
    }

    def "Add another follower"() {

        given: "a user and a first follower"
        List followersList = [FOLLOWER]
        repo.userFollowers = ["Alvaro":followersList]

        when: "We save the follower"
        repo.saveFollower(USER, OTHER_USER)

        then: "another follower is added"
        assert repo.userFollowers.get(USER).size() == 2
    }

    def "Save the first user tweet"() {
        when: "We save the tweet "
        repo.saveUserTweet(USER, tweet1)

        then: "the tweet is added to the list"
        assert repo.userTimeline.get(USER).size() == 1
        assert repo.userTimeline.get(USER)[0].user == USER
    }

    def "Save another user tweet"() {

        given: "the  user Timeline and new a tweet"
        List tweetList = []
        repo.userTimeline = ["Alvaro":[tweet1]]

        when: "We save the tweet "
        repo.saveUserTweet(USER, tweet2)

        then: "the new tweet is added to the list"
        assert repo.userTimeline.get(USER).size() == 2
    }

    def "Show user Timeline"() {

        when: "we execute show user Timeline"
        repo.showUserTimeline(USER)

        then: "show view is executed"
        1 * view.showUserTimeline(_ as List)
    }

    def "Show user wall"() {

        when: "we execute show user wall"
        repo.showWall(USER)

        then: "show view is executed"
        1 * view.showUserTimeline(_ as List, true)
    }

    def "get user tweets"() {

        given: "a existing user"
        List tweetList = []
        repo.userTimeline = ["Alvaro":[tweet1]]

        when: "We get User Tweets"
        tweetList = repo.getUserTweets(USER)

        then: "get the user Tweets List"
        assert tweetList.size() == 1
        assert tweetList[0].user == USER
        assert tweetList[0].message == "My first tweet!"
    }

    def "get user tweets for non existing user"() {

        given: "a non existing user"
        List tweetList = []
        repo.userTimeline = ["Alvaro":[tweet1]]

        when: "We get User Tweets"
        tweetList = repo.getUserTweets(OTHER_USER)

        then: "get a empty list and add list to a map"
        assert repo.userTimeline.size() == 2
        assert tweetList.size() == 0
    }

    def "get user followers"() {

        given: "a existing user"
        List followersList = []
        repo.userFollowers = ["Alvaro":[OTHER_USER]]

        when: "We get Followers"
        followersList = repo.getFollowers(USER)

        then: "get the followers List"
        assert followersList.size() == 1
        assert followersList[0] == OTHER_USER
    }

    def "get user followers for non existing user"() {

        given: "a non existing user"
        List followersList = []
        repo.userFollowers = ["Alvaro":[tweet1]]

        when: "We get Followers"
        followersList = repo.getFollowers(OTHER_USER)

        then: "get a empty list and add list to a map"
        assert repo.userFollowers.size() == 2
        assert followersList.size() == 0
    }
}