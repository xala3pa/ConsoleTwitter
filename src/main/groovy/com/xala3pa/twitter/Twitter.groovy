package com.xala3pa.twitter

import java.io.Console
import java.util.Scanner

import com.xala3pa.twitter.commands.*
import com.xala3pa.twitter.backend.Repo

class Twitter {
    private final CommandInvoker commandInvoker
    private final Repo repo
    private final Console con

    Twitter(CommandInvoker commandInvoker, Console console, Repo repo) {
        this.commandInvoker = commandInvoker
        this.repo = repo
        this.con = console
    }

    void init() {
        String command
         println("""
            Welcome to Console Twitter:

              To post:
                username -> message

              To read someone's messages:
                  username

              To follow someone:
                username follows other-username

              To read someone's wall:
                  username wall

      """)

         while (true) {
            if (con != null) {
                Scanner scanner = new Scanner(con.reader())
                command = scanner.nextLine()
                commandInvoker.executeCommand(command, repo)
            } else {
                break
            }
        }
    }
}
