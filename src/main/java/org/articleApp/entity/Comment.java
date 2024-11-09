package org.articleApp.entity;

import org.articleApp.ID.CommentID;

public class Comment {
    private final CommentID commentID;
    private final String text;

    public Comment(CommentID commentID, String text) {
        this.commentID = commentID;
        this.text = text;
    }

    public CommentID getID() {
        return commentID;
    }
}
