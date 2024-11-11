package org.articleApp.service.exception;

public class CommentFindException extends RuntimeException {
  public CommentFindException(String message) {
    super(message);
  }

  public CommentFindException(String message, Throwable cause) {
    super(message, cause);
  }
}
