package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.*
import com.xala3pa.twitter.backend.Repository

class CommandInvokerSpec extends Specification {

    Repository repository
    WallCommand wallCommand
    ReadCommand readCommand
    PostCommand postCommand
    FollowCommand followCommand

    def setup() {
        repository = Mock()
        wallCommand = Mock()
        readCommand = Mock()
        postCommand = Mock()
        followCommand = Mock()
    }

    def "Execute invoker with proper command"() {

        given: "The correct Command"
        String command = "Alvaro Follows Peter"
        CommandInvoker commandInvoker = new CommandInvoker(postCommand, followCommand,
            wallCommand, readCommand)

        when: "We execute de command invoker"
        commandInvoker.executeCommand(command, repository)

        then: "each command handlers are executed"
        1 *  postCommand.execute(_ as String, _ as Repository)
        1 *  followCommand.execute(_ as String, _ as Repository)
        1 *  wallCommand.execute(_ as String, _ as Repository)
        1 *  readCommand.execute(_ as String, _ as Repository)
    }

    def "Execute invoker with NON proper command"() {

        given: "The correct Command"
        String command = "Alvaro >> Peter"
        CommandInvoker commandInvoker = new CommandInvoker(postCommand, followCommand,
            wallCommand, readCommand)

        when: "We execute de command invoker"
        commandInvoker.executeCommand(command, repository)

        then: "each command handlers are executed"
        1 *  postCommand.execute(_ as String, _ as Repository)
        1 *  followCommand.execute(_ as String, _ as Repository)
        1 *  wallCommand.execute(_ as String, _ as Repository)
        1 *  readCommand.execute(_ as String, _ as Repository)
    }
}