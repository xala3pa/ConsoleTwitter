package com.xala3pa.twitter.commands

import com.xala3pa.twitter.tweets.Tweet
import com.xala3pa.twitter.backend.Repository

class PostCommand {
    private static final String COMMAND_PATTERN = /(?<user>\w+)\s->\s(?<message>.*)/

    def execute(String command, Repository repository) {
        def matcher = command =~ COMMAND_PATTERN
        if( matcher.matches() ) {
            String user = matcher.group( 'user' )
            Tweet tweet = new Tweet(user:user, message:matcher.group( 'message' ),
                createTime: System.currentTimeMillis())

            repository.saveUserTweet(user, tweet)
        }
    }
}
