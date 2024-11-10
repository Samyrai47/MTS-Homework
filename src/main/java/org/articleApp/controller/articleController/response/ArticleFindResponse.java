package org.articleApp.controller.articleController.response;

import org.articleApp.entity.Article;

/**
 * Record ответа на запрос нахождения статьи по ID. Возвращает объект найденной статьи.
 *
 * @param article Объект статьи.
 * @author Samyrai47
 */
public record ArticleFindResponse(Article article) {}
