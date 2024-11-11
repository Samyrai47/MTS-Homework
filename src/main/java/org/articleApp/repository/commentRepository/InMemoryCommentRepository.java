package org.articleApp.repository.commentRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;
import org.articleApp.entity.Comment;
import org.articleApp.repository.articleRepository.ArticleRepository;
import org.articleApp.repository.commentRepository.exception.CommentIDDuplicatedException;
import org.articleApp.repository.commentRepository.exception.CommentNotFoundException;

public class InMemoryCommentRepository implements CommentRepository {

  private final AtomicLong nextID = new AtomicLong(0);
  private final ArticleRepository articleRepository;

  public InMemoryCommentRepository(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public synchronized CommentID generateID() {
    return new CommentID(nextID.incrementAndGet());
  }

  @Override
  public Comment findByID(ArticleID articleID, CommentID commentID) {
    List<Comment> comments = articleRepository.findByID(articleID).getComments();
    for (int i = 0; i < comments.size(); i++) {
      if (comments.get(i).getID().equals(commentID)) {
        return comments.get(i);
      }
    }
    throw new CommentNotFoundException("Cannot find comment with ID=" + commentID);
  }

  @Override
  public synchronized void create(ArticleID articleID, Comment comment) {
    if (articleRepository.findByID(articleID).getComments().contains(comment)) {
      throw new CommentIDDuplicatedException(
          "Comment with the given id already exists: " + comment.getID());
    }
    articleRepository.findByID(articleID).getComments().add(comment);
  }

  @Override
  public void delete(ArticleID articleID, CommentID commentID) {
    Comment comment = findByID(articleID, commentID);
    articleRepository.findByID(articleID).getComments().remove(comment);
  }
}
