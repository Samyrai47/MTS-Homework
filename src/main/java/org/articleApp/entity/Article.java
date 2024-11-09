package org.articleApp.entity;

import org.articleApp.ID.ArticleID;

import java.util.List;
import java.util.Set;

public class Article {
    private final ArticleID ID;
    private final String name;
    private final Set<String> tags;
    private final List<Comment> comments;

    public Article(ArticleID ID, String name, Set<String> tags, List<Comment> comments) {
        this.ID = ID;
        this.name = name;
        this.tags = tags;
        this.comments = comments;
    }

    public ArticleID getID() {
        return ID;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags.toString();
    }

    public String getCommentsAPI() {
        return comments.toString();
    }
}
