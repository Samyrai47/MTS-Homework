package org.articleApp.controller.articleController.request;

import java.util.Set;

/**
 * Record запроса на создание статьи.
 *
 * @param name Название статьи.
 * @param tags Теги статьи.
 * @author Samyrai47
 */
public record ArticleCreateRequest(String name, Set<String> tags) {}
