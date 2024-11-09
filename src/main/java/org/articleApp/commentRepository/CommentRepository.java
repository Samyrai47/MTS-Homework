package org.articleApp.commentRepository;

import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;
import org.articleApp.entity.Comment;

public interface CommentRepository {
    CommentID generateID();

    Comment findByID(ArticleID articleID, CommentID commentID);

    void create(ArticleID articleID, Comment comment);

    void delete(ArticleID articleID, CommentID commentID);
}
