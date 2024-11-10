package org.articleApp.entity;

import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;

/**
 * Представление объекта комментария. Имеет ID, текст комментария, ID статьи, к которому относится
 * комментарий.
 *
 * @author Samyrai47
 */
public class Comment {
  private final CommentID commentID;
  private final String text;
  private final ArticleID articleID;

  public Comment(CommentID commentID, String text, ArticleID articleID) {
    this.commentID = commentID;
    this.text = text;
    this.articleID = articleID;
  }

  public CommentID getID() {
    return commentID;
  }

  @Override
  public String toString() {
    return "Comment{" + "commentID=" + commentID + ", text='" + text + '\'' + '}';
  }
}
