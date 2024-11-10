package org.articleApp.service;

import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;
import org.articleApp.entity.Comment;
import org.articleApp.repository.commentRepository.CommentRepository;
import org.articleApp.repository.commentRepository.exception.CommentIDDuplicatedException;
import org.articleApp.repository.commentRepository.exception.CommentNotFoundException;
import org.articleApp.service.exception.CommentCreateException;
import org.articleApp.service.exception.CommentDeleteException;
import org.articleApp.service.exception.CommentFindException;

public class CommentService {
  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Comment findByID(ArticleID articleID, CommentID commentID) {
    try {
      return commentRepository.findByID(articleID, commentID);
    } catch (CommentNotFoundException e) {
      throw new CommentFindException("Cannot find comment with ID=" + commentID, e);
    }
  }

  public CommentID create(ArticleID articleID, String text) {
    CommentID commentID = commentRepository.generateID();
    Comment comment = new Comment(commentID, text, articleID);
    try {
      commentRepository.create(articleID, comment);
    } catch (CommentIDDuplicatedException e) {
      throw new CommentCreateException("Cannot create message", e);
    }
    return commentID;
  }

  public void delete(ArticleID articleID, CommentID commentID) {
    try {
      commentRepository.delete(articleID, commentID);
    } catch (CommentNotFoundException e) {
      throw new CommentDeleteException("Cannot delete comment with ID=" + commentID, e);
    }
  }
}
