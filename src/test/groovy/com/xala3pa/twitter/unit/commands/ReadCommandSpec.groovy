package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.ReadCommand
import com.xala3pa.twitter.backend.Repo

class ReadCommandSpec extends Specification {

    Repo repo

    def setup() {
        repo = Mock()
    }

    def "Execute with proper command"() {

        given: "The correct Command"
        String command = "Alvaro"
        ReadCommand readCommand = new ReadCommand()

        when: "We execute"
        readCommand.execute(command, repo)

        then: "showUserTimeline() is invoked"
        1 *  repo.showUserTimeline(_ as String)
    }

    def "Execute with non proper command"() {

        given: "NOT correct command"
        String command = "Alvaro -> My first Tweet!"
        ReadCommand readCommand = new ReadCommand()

        when: "We execute"
        readCommand.execute(command, repo)

        then: "showUserTimeline() is NOT invoked"
        0 *  repo.showUserTimeline(_ as String)
    }
}