package com.xala3pa.twitter.tweets

class Tweet implements Comparable {

    long createTime
    String user
    String message

    int compareTo(other) {
        this.createTime <=> other.createTime
    }
}