package org.articleApp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.articleApp.ID.ArticleID;

/**
 * Представление объекта статьи. Имеет ID, название, теги, комментарии.
 *
 * @author Samyrai47
 */
public class Article {
  private final ArticleID ID;
  private final String name;
  private final Set<String> tags;
  private final List<Comment> comments = new ArrayList<>();

  public Article(ArticleID ID, String name, Set<String> tags) {
    this.ID = ID;
    this.name = name;
    this.tags = tags;
  }

  public ArticleID getID() {
    return ID;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public String getName() {
    return name;
  }

  public String getTags() {
    return tags.toString();
  }

  public String getCommentsAPI() {
    return comments.toString();
  }

  public void setComments(List<Comment> comments) {
    this.comments.addAll(comments);
  }

  @Override
  public String toString() {
    return "Article{"
        + "ID="
        + ID
        + ", name='"
        + name
        + '\''
        + ", tags="
        + tags
        + ", comments="
        + comments
        + '}';
  }
}
