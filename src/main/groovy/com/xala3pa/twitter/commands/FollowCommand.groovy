package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repo

class FollowCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)\s[Ff]ollows\s(?<follower>\w+)/

    def execute(String command, Repo repo) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            String user = matcher.group( 'user' )
            String follower = matcher.group( 'follower' )
            repo.saveFollower(user, follower)
        }
    }
}
