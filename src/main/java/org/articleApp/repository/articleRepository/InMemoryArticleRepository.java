package org.articleApp.repository.articleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.articleApp.ID.ArticleID;
import org.articleApp.entity.Article;
import org.articleApp.entity.Comment;
import org.articleApp.repository.articleRepository.exception.ArticleIDDuplicatedException;
import org.articleApp.repository.articleRepository.exception.ArticleNotFoundException;

public class InMemoryArticleRepository implements ArticleRepository {

  private final AtomicLong nextID = new AtomicLong(0);
  private final Map<ArticleID, Article> articleMap = new ConcurrentHashMap<>();

  @Override
  public synchronized ArticleID generateID() {
    return new ArticleID(nextID.incrementAndGet());
  }

  @Override
  public List<Article> getArticles() {
    return new ArrayList<>(articleMap.values());
  }

  @Override
  public Article findByID(ArticleID ID) {
    Article article = articleMap.get(ID);
    if (article == null) {
      throw new ArticleNotFoundException("Cannot find article with ID=" + ID);
    }
    return article;
  }

  @Override
  public synchronized void create(Article article) {
    if (articleMap.get(article.getID()) != null) {
      throw new ArticleIDDuplicatedException(
          "Article with the given id already exists: " + article.getID());
    }
    articleMap.put(article.getID(), article);
  }

  @Override
  public synchronized void update(Article article) {
    if (articleMap.get(article.getID()) == null) {
      throw new ArticleNotFoundException("Cannot find article with ID=" + article.getID());
    }
    List<Comment> comments = articleMap.get(article.getID()).getComments();
    article.setComments(comments);
    articleMap.put(article.getID(), article);
  }

  @Override
  public void delete(ArticleID ID) {
    if (articleMap.remove(ID) == null) {
      throw new ArticleNotFoundException("Cannot find article with ID=" + ID);
    }
  }
}
