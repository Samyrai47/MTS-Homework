package org.articleApp.controller.articleController.response;

import org.articleApp.ID.ArticleID;

/**
 * Record ответа на запрос создания статьи. Возвращает ID созданной статьи.
 *
 * @param articleID ID статьи.
 * @author Samyrai47
 */
public record ArticleCreateResponse(ArticleID articleID) {}
