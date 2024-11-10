package org.articleApp.repository.commentRepository.exception;

public class CommentIDDuplicatedException extends RuntimeException {
  public CommentIDDuplicatedException(String message) {
    super(message);
  }

  public CommentIDDuplicatedException(String message, Throwable cause) {
    super(message, cause);
  }
}
