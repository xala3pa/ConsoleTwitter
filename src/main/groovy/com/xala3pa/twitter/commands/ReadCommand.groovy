package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repo

class ReadCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)/

    def execute(String command, Repo repo) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            repo.showUserTimeline(matcher.group( 'user' ))
        }
    }
}
