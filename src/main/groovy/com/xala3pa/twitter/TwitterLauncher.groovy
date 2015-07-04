package com.xala3pa.twitter

import com.xala3pa.twitter.backend.Repository
import com.xala3pa.twitter.commands.*

class TwitterLauncher {
	private static FollowCommand followCommand = new FollowCommand()
	private static PostCommand   postCommand = new PostCommand()
	private static ReadCommand   readCommand = new ReadCommand()
	private static WallCommand   wallCommand = new WallCommand()

    static void main(String[] args) {
    	Console console = System.console()
    	Repository repository = new Repository()
        CommandInvoker commandInvoker = new CommandInvoker(postCommand, followCommand, wallCommand, readCommand)
        Twitter twitter = new Twitter(commandInvoker, console, repository)

        twitter.init()
    }
}
