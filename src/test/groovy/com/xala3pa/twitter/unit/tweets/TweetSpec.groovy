package com.xala3pa.twitter.unit.tweets

import spock.lang.*

import com.xala3pa.twitter.tweets.Tweet

class TweetSpec extends Specification {

    Tweet tweet

    def setup() {
        tweet = new Tweet(user: 'Alvaro', message: 'My first tweet!', createTime: 12l)
    }


    def "Execute when other is lower than this"() {
        given:
        Tweet other = new Tweet(user: 'Peter', message: 'My first tweet!', createTime: 10l)

        when: "We compare"
        def res = tweet.compareTo(other)

        then: "resturn 1"
        assert res == 1
    }

    def "Execute when other is greater than this"() {
        given:
        Tweet other = new Tweet(user: 'Peter', message: 'My first tweet!', createTime: 14l)

        when: "We compare"
        def res = tweet.compareTo(other)

        then: "return -1"
        assert res == -1
    }

    def "Execute when other is equals than this"() {
        given:
        Tweet other = new Tweet(user: 'Peter', message: 'My first tweet!', createTime: 12l)

        when: "We compare"
        def res = tweet.compareTo(other)

        then: "return 0"
        assert res == 0
    }
}