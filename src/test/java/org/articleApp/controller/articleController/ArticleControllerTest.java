package org.articleApp.controller.articleController;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.articleApp.Application;
import org.articleApp.repository.articleRepository.InMemoryArticleRepository;
import org.articleApp.service.ArticleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

class ArticleControllerTest {

  private Service service;

  @BeforeEach
  void befofeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void should201IfArticleIsSuccessfullyCreated() throws Exception {
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    ObjectMapper objectMapper = new ObjectMapper();
    Application application =
        new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                                    { "name": "How to use Postman", "tags": ["API", "Request", "Response"] }"""))
                    .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));

    assertEquals(201, response.statusCode());
  }

  @Test
  void should200IfArticleSuccessfullyDeleted() throws Exception {
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    ObjectMapper objectMapper = new ObjectMapper();
    Application application =
        new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                                    { "name": "How to use Postman", "tags": ["API", "Request", "Response"] }"""))
                    .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));
    assertEquals(201, response.statusCode());

    HttpResponse<String> response1 =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:%d/api/articles/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));

    assertEquals(200, response1.statusCode());
  }

  @Test
  void should404IfArticleDoesNotDeleted() throws Exception {
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    ObjectMapper objectMapper = new ObjectMapper();
    Application application =
        new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:%d/api/articles/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));

    assertEquals(404, response.statusCode());
  }

  @Test
  void should200IfArticleSuccessfullyUpdated() throws Exception {
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    ObjectMapper objectMapper = new ObjectMapper();
    Application application =
        new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                                    { "name": "How to use Postman", "tags": ["API", "Request", "Response"] }"""))
                    .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));
    assertEquals(201, response.statusCode());

    HttpResponse<String> response1 =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .PUT(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                                    { "name": "How to use Spring", "tags": ["API", "Request"] }"""))
                    .uri(URI.create("http://localhost:%d/api/articles/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));

    assertEquals(201, response1.statusCode());
  }

  @Test
  void should400IfArticleDoesNotUpdated() throws Exception {
    InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
    final ArticleService articleService = new ArticleService(inMemoryArticleRepository);
    ObjectMapper objectMapper = new ObjectMapper();
    Application application =
        new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .PUT(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                                    { "name": "How to use Spring", "tags": ["API", "Request"] }"""))
                    .uri(URI.create("http://localhost:%d/api/articles/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));

    assertEquals(400, response.statusCode());
  }
}
