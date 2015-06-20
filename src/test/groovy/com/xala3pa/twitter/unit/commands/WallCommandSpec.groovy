package com.xala3pa.twitter.unit.commands

import spock.lang.*

import com.xala3pa.twitter.commands.WallCommand
import com.xala3pa.twitter.backend.Repo

class WallCommandSpec extends Specification {

    Repo repo

    def setup() {
        repo = Mock()
    }

    def "Execute with proper command"() {

	    given: "The correct Command"
	    String command = "Alvaro wall"
	    WallCommand wallCommand = new WallCommand()

	    when: "We execute"
	    wallCommand.execute(command, repo)

		then: "showWall() is invoked"
	    1 *  repo.showWall(_ as String)
  	}

  	def "Execute with non proper command"() {

    	given: "NOT correct command"
    	String command = "Alvaro -> My first Tweet!"
    	WallCommand wallCommand = new WallCommand()

    	when: "We execute"
    	wallCommand.execute(command, repo)

    	then: "showWall() is NOT invoked"
    	0 *  repo.showWall(_ as String)
  	}
}