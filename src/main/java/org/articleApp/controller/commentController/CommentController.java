package org.articleApp.controller.commentController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.articleApp.ID.ArticleID;
import org.articleApp.ID.CommentID;
import org.articleApp.controller.Controller;
import org.articleApp.controller.ErrorResponse;
import org.articleApp.controller.commentController.request.CommentCreateRequest;
import org.articleApp.controller.commentController.request.CommentDeleteRequest;
import org.articleApp.controller.commentController.response.CommentCreateResponse;
import org.articleApp.service.ArticleService;
import org.articleApp.service.CommentService;
import org.articleApp.service.exception.ArticleFindException;
import org.articleApp.service.exception.CommentCreateException;
import org.articleApp.service.exception.CommentDeleteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

/**
 * Отвечает за endpoint-ы. RESTful API
 *
 * @author Samyrai47
 */
public class CommentController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

  private final CommentService commentService;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;
  private final Service service;

  public CommentController(
      CommentService commentService,
      ArticleService articleService,
      ObjectMapper objectMapper,
      Service service) {
    this.service = service;
    this.commentService = commentService;
    this.articleService = articleService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createComment();
    deleteComment();
  }

  /** Создание комментария (POST). Требует поля articleID и text. */
  private void createComment() {
    service.post(
        "/api/articles/:articleID",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
          String body = request.body();
          CommentCreateRequest commentCreateRequest =
              objectMapper.readValue(body, CommentCreateRequest.class);
          try {
            articleService.findByID(commentCreateRequest.articleID());
          } catch (ArticleFindException e) {
            LOG.warn("Cannot find article", e);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
          try {
            CommentID commentID =
                commentService.create(
                    commentCreateRequest.articleID(), commentCreateRequest.text());
            LOG.debug("Comment created");
            response.status(201);
            return objectMapper.writeValueAsString(new CommentCreateResponse(commentID));
          } catch (CommentCreateException e) {
            LOG.warn("Cannot create comment", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }

  /** Удаление комментария (DELETE). Требует ID статьи и ID комментария. */
  private void deleteComment() {
    service.delete(
        "/api/articles/:articleID/:commentID",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
          CommentID commentID = new CommentID(Long.parseLong(request.params("commentID")));
          CommentDeleteRequest commentDeleteRequest =
              new CommentDeleteRequest(articleID, commentID);
          try {
            articleService.findByID(commentDeleteRequest.articleID());
          } catch (ArticleFindException e) {
            LOG.warn("Cannot find article", e);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
          try {
            commentService.delete(
                commentDeleteRequest.articleID(), commentDeleteRequest.commentID());
            LOG.debug("Comment deleted");
            response.status(200);
            return objectMapper.writeValueAsString("Comment deleted");
          } catch (CommentDeleteException e) {
            LOG.warn("Cannot delete comment", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }
}
