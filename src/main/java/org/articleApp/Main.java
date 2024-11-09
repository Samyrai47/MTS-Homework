package org.articleApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.articleApp.articleRepository.InMemoryArticleRepository;
import org.articleApp.commentRepository.InMemoryCommentRepository;
import org.articleApp.controller.ArticleController;
import org.articleApp.service.ArticleService;
import org.articleApp.service.CommentService;
import spark.Service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = Service.ignite();
        ObjectMapper objectMapper = new ObjectMapper();
        Application application = new Application(
                List.of(
                        new ArticleController(
                                service,
                                new ArticleService(
                                        new InMemoryArticleRepository()
                                ),
                                new CommentService(new InMemoryCommentRepository(new InMemoryArticleRepository())
                                ),
                                objectMapper
                        )
                )
        );
        application.start();
    }
}