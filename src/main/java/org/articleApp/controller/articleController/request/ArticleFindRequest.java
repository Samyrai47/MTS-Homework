package org.articleApp.controller.articleController.request;

import org.articleApp.ID.ArticleID;

/**
 * Record запроса на нахождение статьи по ID.
 *
 * @param articleID ID статьи
 * @author Samyrai47
 */
public record ArticleFindRequest(ArticleID articleID) {}
