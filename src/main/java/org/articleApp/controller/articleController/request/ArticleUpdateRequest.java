package org.articleApp.controller.articleController.request;

import java.util.Set;
import org.articleApp.ID.ArticleID;

/**
 * Record запроса на обновление статьи по ID. Можно изменить название и теги.
 *
 * @param articleID ID статьи.
 * @param name Название статьи.
 * @param tags Теги статьи.
 * @author Samyrai47
 */
public record ArticleUpdateRequest(ArticleID articleID, String name, Set<String> tags) {}
