package org.articleApp.service;

import org.articleApp.entity.Article;
import org.articleApp.ID.ArticleID;
import org.articleApp.articleRepository.ArticleRepository;
import org.articleApp.entity.Comment;
import org.articleApp.articleRepository.exception.ArticleIDDuplicatedException;
import org.articleApp.articleRepository.exception.ArticleNotFoundException;
import org.articleApp.service.exception.ArticleCreateException;
import org.articleApp.service.exception.ArticleDeleteException;
import org.articleApp.service.exception.ArticleFindException;
import org.articleApp.service.exception.ArticleUpdateException;

import java.util.List;
import java.util.Set;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public Article findByID(ArticleID ID) {
        try {
            return articleRepository.findByID(ID);
        } catch (ArticleNotFoundException e) {
            throw new ArticleFindException("Cannot find article with ID=" + ID, e);
        }
    }

    public ArticleID create(String name, Set<String> tags, List<Comment> comments) {
        ArticleID articleID = articleRepository.generateID();
        Article article = new Article(articleID, name, tags, comments);
        try {
            articleRepository.create(article);
        } catch (ArticleIDDuplicatedException e) {
            throw new ArticleCreateException("Cannot create article", e);
        }
        return articleID;
    }

    public void update(ArticleID articleID, String name, Set<String> tags, List<Comment> comments) {
        Article article = new Article(articleID, name, tags, comments);
        try {
            articleRepository.update(article);
        } catch (ArticleNotFoundException e) {
            throw new ArticleUpdateException("Cannot update article with ID=" + articleID, e);
        }
    }

    public void delete(ArticleID ID) {
        try {
            articleRepository.delete(ID);
        } catch (ArticleNotFoundException e) {
            throw new ArticleDeleteException("Cannot delete article with ID=" + ID, e);
        }
    }
}
