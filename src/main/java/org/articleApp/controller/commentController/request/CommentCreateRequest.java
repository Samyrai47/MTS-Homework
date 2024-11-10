package org.articleApp.controller.commentController.request;

import org.articleApp.ID.ArticleID;

public record CommentCreateRequest(ArticleID articleID, String text) {}
