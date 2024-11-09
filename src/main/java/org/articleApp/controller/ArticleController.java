package org.articleApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.articleApp.ID.ArticleID;
import org.articleApp.controller.request.ArticleCreateRequest;
import org.articleApp.controller.request.ArticleDeleteRequest;
import org.articleApp.controller.request.ArticleFindRequest;
import org.articleApp.controller.request.ArticleUpdateRequest;
import org.articleApp.controller.response.*;
import org.articleApp.entity.Article;
import org.articleApp.entity.Comment;
import org.articleApp.service.ArticleService;
import org.articleApp.service.CommentService;
import org.articleApp.service.exception.ArticleCreateException;
import org.articleApp.service.exception.ArticleDeleteException;
import org.articleApp.service.exception.ArticleFindException;
import org.articleApp.service.exception.ArticleUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArticleController implements Controller{

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final CommentService commentService;
    private final ObjectMapper objectMapper;
    private final Service service;


    public ArticleController(Service service, ArticleService articleService, CommentService commentService, ObjectMapper objectMapper) {
        this.service = service;
        this.articleService = articleService;
        this.commentService = commentService;
        this.objectMapper= objectMapper;
    }

    @Override
    public void initializeEndpoints() {
        createArticle();
        updateArticle();
        findArticle();
        getArticles();
        deleteArticle();
    }

    private void createArticle() {
        service.post(
                "/api/articles",
                (Request request, Response response) -> {
                    response.type("application/json");
                    String body = request.body();
                    ArticleCreateRequest articleCreateRequest = objectMapper.readValue(body,
                            ArticleCreateRequest.class);
                    try {
                        ArticleID articleID = articleService.create(articleCreateRequest.name(), articleCreateRequest.tags(), articleCreateRequest.commentList()); //commentService.create(articleCreateRequest.commentList())
                        response.status(201);
                        return objectMapper.writeValueAsString(new ArticleCreateResponse(articleID));
                    } catch (ArticleCreateException e) {
                        LOG.warn("Cannot create article", e);
                        response.status(400);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }
                }
        );
    }

    private void updateArticle() {
        service.put(
                "/api/articles/:articleID",
                (Request request, Response response) -> {
                    response.type("application/json");
                    ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
                    ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(request.body(),
                            ArticleUpdateRequest.class);
                    try {
                        articleService.update(articleID, articleUpdateRequest.name(), articleUpdateRequest.tags(), articleUpdateRequest.comments());
                        response.status(201);
                        return objectMapper.writeValueAsString(new ArticleUpdateResponse());
                    } catch (ArticleUpdateException e) {
                        LOG.warn("Cannot update article", e);
                        response.status(400);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }
                }
        );
    }

    private void findArticle() {
        service.get(
                "/api/articles/:articleID",
                (Request request, Response response) -> {
                    response.type("application/json");
                    ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
                    ArticleFindRequest articleFindRequest = new ArticleFindRequest(articleID);
                    try {
                        Article article = articleService.findByID(articleFindRequest.articleID());
                        response.status(201);
                        return objectMapper.writeValueAsString(new ArticleFindResponse(article));
                    } catch (ArticleFindException e) {
                        LOG.warn("Cannot find article", e);
                        response.status(404);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }
                }
        );
    }

    private void getArticles() {
        service.get(
                "/api/articles",
                (Request request, Response response) -> {
                    response.type("application/json");
                    List<Article> articles = articleService.getArticles();
                    List<Map<String, String>> articleMapList =
                            articles.stream()
                                    .map(article -> Map.of("name", article.getName(), "tags", article.getTags(), "comments", article.getCommentsAPI()))
                                    .toList();

                    Map<String, Object> model = new HashMap<>();
                    model.put("articles", articleMapList);
                    return objectMapper.writeValueAsString(model);
                }
        );
    }

    private void deleteArticle() {
        service.delete(
                "/api/articles/:articleID",
                (Request request, Response response) -> {
                    response.type("application/json");
                    ArticleID articleID = new ArticleID(Long.parseLong(request.params("articleID")));
                    ArticleDeleteRequest articleDeleteRequest = new ArticleDeleteRequest(articleID);
                    try {
                        articleService.delete(articleDeleteRequest.articleID());
                        response.status(201);
                        return objectMapper.writeValueAsString(new ArticleDeleteResponse());
                    } catch (ArticleDeleteException e) {
                        LOG.warn("Cannot delete article", e);
                        response.status(404);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }
                }
        );
    }
}
