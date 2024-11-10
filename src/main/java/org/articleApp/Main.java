package org.articleApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.articleApp.controller.articleController.ArticleController;
import org.articleApp.controller.articleController.ArticleFreemarkerController;
import org.articleApp.controller.commentController.CommentController;
import org.articleApp.repository.articleRepository.InMemoryArticleRepository;
import org.articleApp.repository.commentRepository.InMemoryCommentRepository;
import org.articleApp.service.ArticleService;
import org.articleApp.service.CommentService;
import org.articleApp.template.TemplateFactory;
import spark.Service;

public class Main {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    Application application =
        new Application(
            List.of(
                new ArticleController(service, articleService, objectMapper),
                new CommentController(
                    new CommentService(new InMemoryCommentRepository(inMemoryArticleRepository)),
                    articleService,
                    objectMapper,
                    service),
                new ArticleFreemarkerController(
                    service, articleService, TemplateFactory.freeMarkerEngine())));
    application.start();
  }
}
