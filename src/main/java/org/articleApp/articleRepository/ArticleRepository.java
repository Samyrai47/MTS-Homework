package org.articleApp.articleRepository;

import org.articleApp.ID.ArticleID;
import org.articleApp.entity.Article;

import java.util.List;

public interface ArticleRepository {
    ArticleID generateID();

    List<Article> getArticles();

    Article findByID(ArticleID ID);

    void create(Article article);

    void update(Article article);

    void delete(ArticleID ID);
}
