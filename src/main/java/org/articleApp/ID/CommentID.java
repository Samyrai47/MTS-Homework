package org.articleApp.ID;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Обертка, которая хранит long ID, чтобы не передавать примитив напрямую экземпляру комментария.
 *
 * @author Samyrai47
 */
public class CommentID {
  @JsonProperty("CommentID")
  private final long ID;

  public CommentID(long ID) {
    this.ID = ID;
  }

  @Override
  public String toString() {
    return "" + ID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CommentID commentID)) return false;
    return ID == commentID.ID;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(ID);
  }
}
