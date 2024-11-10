package org.articleApp.ID;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Обертка, которая хранит long ID, чтобы не передавать примитив напрямую экземпляру статьи.
 *
 * @author Samyrai47
 */
public class ArticleID {
  @JsonProperty("ArticleID")
  private final long ID;

  public ArticleID(long ID) {
    this.ID = ID;
  }

  @Override
  public String toString() {
    return "" + ID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ArticleID articleID)) return false;
    return ID == articleID.ID;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(ID);
  }
}
