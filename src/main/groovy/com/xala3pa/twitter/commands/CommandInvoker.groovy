package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repo

class CommandInvoker {

    private final FollowCommand followCommand
	private final PostCommand postCommand
	private final ReadCommand readCommand
	private final WallCommand wallCommand

    private List avilableCommandList

    public CommandInvoker(PostCommand postCommand, FollowCommand followCommand,
        WallCommand wallCommand, ReadCommand readCommand ) {
        this.followCommand = followCommand
        this.readCommand = readCommand
        this.followCommand = followCommand
        this.postCommand = postCommand
        this.avilableCommandList = [wallCommand, followCommand, postCommand, readCommand]
    }

	void executeCommand(String command, Repo repo) {
		avilableCommandList.each() { it ->
			it.execute(command, repo)
		}
    }
}