package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.PostCommand
import com.xala3pa.twitter.backend.Repo
import com.xala3pa.twitter.tweets.Tweet

class PostCommandSpec extends Specification {

    Repo repo

    def setup() {
        repo = Mock()
    }

    def "Execute with proper command"() {

        given: "The correct Command"
        String command = "Alvaro -> My first tweet!"
        PostCommand postCommand = new PostCommand()

        when: "We execute"
        postCommand.execute(command, repo)

        then: "saveUserTweet() is invoked"
        1 *  repo.saveUserTweet(_ as String, _ as Tweet)
    }

    def "Execute with non proper command"() {

        given: "NOT correct command"
        String command = "Alvaro post My first Tweet!"
        PostCommand postCommand = new PostCommand()

        when: "We execute"
        postCommand.execute(command, repo)

        then: "saveUserTweet() is NOT invoked"
        0 *  repo.saveUserTweet(_ as String, _ as Tweet)
    }
}