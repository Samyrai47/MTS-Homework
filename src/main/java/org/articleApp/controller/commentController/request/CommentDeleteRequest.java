package org.articleApp.controller.commentController.request;

import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;

public record CommentDeleteRequest(ArticleID articleID, CommentID commentID) {}
