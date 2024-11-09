package org.articleApp.controller.request;

import org.articleApp.ID.ArticleID;
import org.articleApp.entity.Comment;

import java.util.List;
import java.util.Set;

public record ArticleUpdateRequest(ArticleID articleID, String name, Set<String> tags, List<Comment> comments) {
}
