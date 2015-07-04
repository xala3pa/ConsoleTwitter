package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repository

class ReadCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)/

    def execute(String command, Repository repository) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            repository.showUserTimeline(matcher.group( 'user' ))
        }
    }
}
