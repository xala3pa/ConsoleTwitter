package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.WallCommand
import com.xala3pa.twitter.backend.Repository

class WallCommandSpec extends Specification {

    Repository repository

    def setup() {
        repository = Mock()
    }

    def "Execute with proper command"() {

	    given: "The correct Command"
	    String command = "Alvaro wall"
	    WallCommand wallCommand = new WallCommand()

	    when: "We execute"
	    wallCommand.execute(command, repository)

		then: "showWall() is invoked"
	    1 *  repository.showWall(_ as String)
  	}

  	def "Execute with non proper command"() {

    	given: "NOT correct command"
    	String command = "Alvaro -> My first Tweet!"
    	WallCommand wallCommand = new WallCommand()

    	when: "We execute"
    	wallCommand.execute(command, repository)

    	then: "showWall() is NOT invoked"
    	0 *  repository.showWall(_ as String)
  	}
}