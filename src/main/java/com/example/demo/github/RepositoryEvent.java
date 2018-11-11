package com.example.demo.github;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 11:36
 */
public class RepositoryEvent {

  private final Type type;

  private final OffsetDateTime creationTiime;

  private final Actor actor;

  private final Issue issue;

  @JsonCreator
  public RepositoryEvent(@JsonProperty("event") String type,
      @JsonProperty("created_at") OffsetDateTime creationTiime,
      @JsonProperty("actor") Actor actor,
      @JsonProperty("issue") Issue issue) {
//    this.type = Type.valueFrom(type);
    this.type = null;
    this.creationTiime = creationTiime;
    this.actor = actor;
    this.issue = issue;
  }

  public Type getType() {
    return type;
  }

  public OffsetDateTime getCreationTiime() {
    return creationTiime;
  }

  public Actor getActor() {
    return actor;
  }

  public Issue getIssue() {
    return issue;
  }

  public enum Type {

  }

}
