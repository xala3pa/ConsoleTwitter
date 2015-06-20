package com.xala3pa.twitter.backend

import com.xala3pa.twitter.tweets.Tweet

class Views {

	void showUserTimeline(List tweets, boolean showUser=false) {
		tweets.sort().each() { tweet ->
			println(format(tweet, showUser))
		}
	}

	private String format(Tweet tweet, boolean showUser) {
		def seconds = (System.currentTimeMillis()-tweet.createTime)/1000
		def timeAgo = seconds<60 ? sprintf(" ( %.0f seconds ago ) ", seconds) : sprintf(" ( %.0f minutes ago ) ", seconds/60 )

		return (showUser ?  tweet.user + " - ":"") + tweet.message + timeAgo
	}
}