package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repository

class WallCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)\s[Ww]all/

    def execute(String command, Repository repository) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            repository.showWall(matcher.group( 'user' ))
        }
    }
}
