package com.devon.demo.main.slackbuilderutil;

/**
 * Created by diwenlao on 2/23/17.
 */

public class MessageLink {

    private String url;
    private String text;

    public MessageLink(String url) {
        this.url = url;
        this.text = url;
    }

    public MessageLink(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public String toString() {
        return "<" + url + "|" + text + ">";
    }

}