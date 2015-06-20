package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repo

class WallCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)\s[Ww]all/

    def execute(String command, Repo repo) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            repo.showWall(matcher.group( 'user' ))
        }
    }
}
