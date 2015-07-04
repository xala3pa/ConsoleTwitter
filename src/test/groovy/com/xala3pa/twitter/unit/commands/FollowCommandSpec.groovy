package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.FollowCommand
import com.xala3pa.twitter.backend.Repository

class FollowCommandSpec extends Specification {

    Repository repository

    def setup() {
        repository = Mock()
    }

    def "Execute with proper command"() {

        given: "The correct Command"
        String command = "Alvaro Follows Peter"
        FollowCommand followCommand = new FollowCommand()

        when: "We execute"
        followCommand.execute(command, repository)

        then: "saveFollower() is invoked"
        1 *  repository.saveFollower(_ as String, _ as String)
    }

    def "Execute with non proper command"() {

        given: "NOT correct command"
        String command = "Alvaro -> My first Tweet!"
        FollowCommand followCommand = new FollowCommand()

        when: "We execute"
        followCommand.execute(command, repository)

        then: "saveFollower() is NOT invoked"
        0 *  repository.saveFollower(_ as String, _ as String)
    }
}