package org.articleApp.controller.request;

import org.articleApp.entity.Comment;

import java.util.List;
import java.util.Set;

public record ArticleCreateRequest(String name, Set<String> tags, List<Comment> commentList) {
}
