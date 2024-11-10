package org.articleApp.controller.articleController.request;

import org.articleApp.ID.ArticleID;

/**
 * Record запроса на удаление статьи.
 *
 * @param articleID id статьи.
 * @author Samyrai47
 */
public record ArticleDeleteRequest(ArticleID articleID) {}
