package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.backend.Repository
import com.xala3pa.twitter.backend.Views
import com.xala3pa.twitter.tweets.Tweet

class RepoSpec extends Specification {

    private static final String USER = "Alvaro"
    private static final String OTHER_USER = "Peter"
    private static final String FOLLOWER = "Michael"

    Repository repository
    Views view
    Tweet tweet1
    Tweet tweet2
    Map userTimeline

    def setup() {
        repository = new Repository()
        view = Mock()

        repository.view = view
        tweet1 = new Tweet(user:"Alvaro", message:"My first tweet!",
            createTime:10l)
        tweet2 = new Tweet(user:"Peter", message:"My second tweet!",
            createTime:20l)
    }

    def "Save first follower"() {

        given: "a user and a follower"
        repository.userFollowers = ["Alvaro":[]]

        when: "We save the follower"
        repository.saveFollower(USER, OTHER_USER)

        then: "new follower is added "
        assert repository.userFollowers.get(USER).size() == 1
    }

    def "Add another follower"() {

        given: "a user and a first follower"
        List followersList = [FOLLOWER]
        repository.userFollowers = ["Alvaro":followersList]

        when: "We save the follower"
        repository.saveFollower(USER, OTHER_USER)

        then: "another follower is added"
        assert repository.userFollowers.get(USER).size() == 2
    }

    def "Save the first user tweet"() {
        when: "We save the tweet "
        repository.saveUserTweet(USER, tweet1)

        then: "the tweet is added to the list"
        assert repository.userTimeline.get(USER).size() == 1
        assert repository.userTimeline.get(USER)[0].user == USER
    }

    def "Save another user tweet"() {

        given: "the  user Timeline and new a tweet"
        List tweetList = []
        repository.userTimeline = ["Alvaro":[tweet1]]

        when: "We save the tweet "
        repository.saveUserTweet(USER, tweet2)

        then: "the new tweet is added to the list"
        assert repository.userTimeline.get(USER).size() == 2
    }

    def "Show user Timeline"() {

        when: "we execute show user Timeline"
        repository.showUserTimeline(USER)

        then: "show view is executed"
        1 * view.showUserTimeline(_ as List)
    }

    def "Show user wall"() {

        when: "we execute show user wall"
        repository.showWall(USER)

        then: "show view is executed"
        1 * view.showUserTimeline(_ as List, true)
    }

    def "get user tweets"() {

        given: "a existing user"
        List tweetList = []
        repository.userTimeline = ["Alvaro":[tweet1]]

        when: "We get User Tweets"
        tweetList = repository.getUserTweets(USER)

        then: "get the user Tweets List"
        assert tweetList.size() == 1
        assert tweetList[0].user == USER
        assert tweetList[0].message == "My first tweet!"
    }

    def "get user tweets for non existing user"() {

        given: "a non existing user"
        List tweetList = []
        repository.userTimeline = ["Alvaro":[tweet1]]

        when: "We get User Tweets"
        tweetList = repository.getUserTweets(OTHER_USER)

        then: "get a empty list and add list to a map"
        assert repository.userTimeline.size() == 2
        assert tweetList.size() == 0
    }

    def "get user followers"() {

        given: "a existing user"
        List followersList = []
        repository.userFollowers = ["Alvaro":[OTHER_USER]]

        when: "We get Followers"
        followersList = repository.getFollowers(USER)

        then: "get the followers List"
        assert followersList.size() == 1
        assert followersList[0] == OTHER_USER
    }

    def "get user followers for non existing user"() {

        given: "a non existing user"
        List followersList = []
        repository.userFollowers = ["Alvaro":[tweet1]]

        when: "We get Followers"
        followersList = repository.getFollowers(OTHER_USER)

        then: "get a empty list and add list to a map"
        assert repository.userFollowers.size() == 2
        assert followersList.size() == 0
    }
}