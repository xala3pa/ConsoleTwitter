package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.backend.Repo
import com.xala3pa.twitter.backend.Views
import com.xala3pa.twitter.tweets.Tweet

class ViewsSpec extends Specification {

    Tweet tweet1
    Tweet tweet2
    Tweet tweet3
    Views view

    def setup() {
        view = Spy()
        tweet1 = new Tweet(user:"Alvaro", message:"My first tweet!",
            createTime:System.currentTimeMillis() - 4000)
        tweet2 = new Tweet(user:"Peter", message:"My second tweet!",
            createTime:System.currentTimeMillis() - 3000)
        tweet3 = new Tweet(user:"Michael", message:"My third tweet!",
            createTime:System.currentTimeMillis() - 2000)
    }

    def "Show user timeLine sorted"() {
        given: "a tweets list"
        List tweetList = [tweet1, tweet2, tweet3]

        when: "we execute showUserTimeLine"
        view.showUserTimeline(tweetList, false)

        then: "format is executed one time per tweet "
        1 * view.showUserTimeline(tweetList, false)
        3 * view.invokeMethod(*_)
    }

    def "Format a tweet to show in timeLine"() {

        when: "we execute format"
        String tweet = view.format(tweet1, false)

        then: "tweet is formatted"
        assert tweet == "My first tweet! ( 4 seconds ago ) "
    }

    def "Format a tweet to show in user wall"() {

        when: "we execute format to show the user too"
        String tweet = view.format(tweet1, true)

        then: "tweet is formatted"
        assert tweet == "Alvaro - My first tweet! ( 4 seconds ago ) "
    }
}