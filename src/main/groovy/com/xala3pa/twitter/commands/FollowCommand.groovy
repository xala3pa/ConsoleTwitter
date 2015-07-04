package com.xala3pa.twitter.commands

import com.xala3pa.twitter.backend.Repository

class FollowCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)\s[Ff]ollows\s(?<follower>\w+)/

    def execute(String command, Repository repository) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            String user = matcher.group( 'user' )
            String follower = matcher.group( 'follower' )
            repository.saveFollower(user, follower)
        }
    }
}
