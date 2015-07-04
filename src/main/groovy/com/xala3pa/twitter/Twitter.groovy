package com.xala3pa.twitter

import com.xala3pa.twitter.commands.*
import com.xala3pa.twitter.backend.Repository

class Twitter {
    private final CommandInvoker commandInvoker
    private final Repository repository
    private final Console console

    Twitter(CommandInvoker commandInvoker, Console console, Repository repository) {
        this.commandInvoker = commandInvoker
        this.repository = repository
        this.console = console
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
            if (console != null) {
                Scanner scanner = new Scanner(console.reader())
                command = scanner.nextLine()
                commandInvoker.executeCommand(command, repository)
            } else {
                break
            }
        }
    }
}
