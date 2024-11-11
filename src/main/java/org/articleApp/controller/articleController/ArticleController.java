package org.articleApp.controller.articleController;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.articleApp.ID.ArticleID;
import org.articleApp.controller.Controller;
import org.articleApp.controller.ErrorResponse;
import org.articleApp.controller.articleController.request.ArticleCreateRequest;
import org.articleApp.controller.articleController.request.ArticleDeleteRequest;
import org.articleApp.controller.articleController.request.ArticleFindRequest;
import org.articleApp.controller.articleController.request.ArticleUpdateRequest;
import org.articleApp.controller.articleController.response.ArticleCreateResponse;
import org.articleApp.controller.articleController.response.ArticleDeleteResponse;
import org.articleApp.controller.articleController.response.ArticleFindResponse;
import org.articleApp.controller.articleController.response.ArticleUpdateResponse;
import org.articleApp.entity.Article;
import org.articleApp.service.ArticleService;
import org.articleApp.service.exception.ArticleCreateException;
import org.articleApp.service.exception.ArticleDeleteException;
import org.articleApp.service.exception.ArticleFindException;
import org.articleApp.service.exception.ArticleUpdateException;
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
public class ArticleController implements Controller {

  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

  private final ArticleService articleService;
  private final ObjectMapper objectMapper;
  private final Service service;

  public ArticleController(
      Service service, ArticleService articleService, ObjectMapper objectMapper) {
    this.service = service;
    this.articleService = articleService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createArticle();
    updateArticle();
    findArticle();
    getArticles();
    deleteArticle();
  }

  /** Создание статьи (POST). Требует поля name и tags. */
  private void createArticle() {
    service.post(
        "/api/articles",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleCreateRequest articleCreateRequest =
              objectMapper.readValue(body, ArticleCreateRequest.class);
          try {
            ArticleID articleID =
                articleService.create(articleCreateRequest.name(), articleCreateRequest.tags());
            LOG.debug("Article created");
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleCreateResponse(articleID));
          } catch (ArticleCreateException e) {
            LOG.warn("Cannot create article", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }

  /** Обновление статьи (PUT). Требует ID статьи, а так же данные для изменения: name, tags. */
  private void updateArticle() {
    service.put(
        "/api/articles/:articleID",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
          ArticleUpdateRequest articleUpdateRequest =
              objectMapper.readValue(request.body(), ArticleUpdateRequest.class);
          try {
            articleService.update(
                articleID, articleUpdateRequest.name(), articleUpdateRequest.tags());
            LOG.debug("Article updated");
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleUpdateResponse());
          } catch (ArticleUpdateException e) {
            LOG.warn("Cannot update article", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }

  /** Нахождение статьи (GET). Требует ID статьи. */
  private void findArticle() {
    service.get(
        "/api/articles/:articleID",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
          ArticleFindRequest articleFindRequest = new ArticleFindRequest(articleID);
          try {
            Article article = articleService.findByID(articleFindRequest.articleID());
            LOG.debug("Article found");
            response.status(200);
            return objectMapper.writeValueAsString(new ArticleFindResponse(article));
          } catch (ArticleFindException e) {
            LOG.warn("Cannot find article", e);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }

  /** Вывод всех статей (GET). */
  private void getArticles() {
    service.get(
        "/api/articles",
        (Request request, Response response) -> {
          response.type("application/json");
          List<Article> articles = articleService.getArticles();
          List<Map<String, String>> articleMapList =
              articles.stream()
                  .map(
                      article ->
                          Map.of(
                              "name",
                              article.getName(),
                              "tags",
                              article.getTags(),
                              "comments",
                              article.getCommentsAPI()))
                  .toList();

          Map<String, Object> model = new HashMap<>();
          model.put("articles", articleMapList);
          LOG.debug("Articles showed");
          return objectMapper.writeValueAsString(model);
        });
  }

  /** Удаление статьи (DELETE). Требует ID статьи. */
  private void deleteArticle() {
    service.delete(
        "/api/articles/:articleID",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
          ArticleDeleteRequest articleDeleteRequest = new ArticleDeleteRequest(articleID);
          try {
            articleService.delete(articleDeleteRequest.articleID());
            LOG.debug("Article deleted");
            response.status(200);
            return objectMapper.writeValueAsString(new ArticleDeleteResponse());
          } catch (ArticleDeleteException e) {
            LOG.warn("Cannot delete article", e);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        });
  }
}
