package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.FollowCommand
import com.xala3pa.twitter.backend.Repo

class FollowCommandSpec extends Specification {

    Repo repo

    def setup() {
        repo = Mock()
    }

    def "Execute with proper command"() {

        given: "The correct Command"
        String command = "Alvaro Follows Peter"
        FollowCommand followCommand = new FollowCommand()

        when: "We execute"
        followCommand.execute(command, repo)

        then: "saveFollower() is invoked"
        1 *  repo.saveFollower(_ as String, _ as String)
    }

    def "Execute with non proper command"() {

        given: "NOT correct command"
        String command = "Alvaro -> My first Tweet!"
        FollowCommand followCommand = new FollowCommand()

        when: "We execute"
        followCommand.execute(command, repo)

        then: "saveFollower() is NOT invoked"
        0 *  repo.saveFollower(_ as String, _ as String)
    }
}