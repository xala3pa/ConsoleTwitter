package com.xala3pa.twitter

import java.io.Console
import java.util.Scanner

import com.xala3pa.twitter.backend.Repo
import com.xala3pa.twitter.commands.*

class Server {
	private static FollowCommand fc = new FollowCommand()
	private static PostCommand   pc = new PostCommand()
	private static ReadCommand   rc = new ReadCommand()
	private static WallCommand   wc = new WallCommand()

    static void main(String[] args) {
    	Console console = System.console()
    	Repo repo = new Repo()
        CommandInvoker commandInvoker = new CommandInvoker(pc, fc, wc, rc)
        Twitter twitter = new Twitter(commandInvoker, console, repo)

        twitter.init()
    }
}
