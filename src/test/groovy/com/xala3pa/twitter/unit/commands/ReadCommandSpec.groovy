package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.ReadCommand
import com.xala3pa.twitter.backend.Repository

class ReadCommandSpec extends Specification {

    Repository repository

    def setup() {
        repository = Mock()
    }

    def "Execute with proper command"() {

        given: "The correct Command"
        String command = "Alvaro"
        ReadCommand readCommand = new ReadCommand()

        when: "We execute"
        readCommand.execute(command, repository)

        then: "showUserTimeline() is invoked"
        1 *  repository.showUserTimeline(_ as String)
    }

    def "Execute with non proper command"() {

        given: "NOT correct command"
        String command = "Alvaro -> My first Tweet!"
        ReadCommand readCommand = new ReadCommand()

        when: "We execute"
        readCommand.execute(command, repository)

        then: "showUserTimeline() is NOT invoked"
        0 *  repository.showUserTimeline(_ as String)
    }
}