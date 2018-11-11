package com.example.demo.events;

import java.util.List;

import com.example.demo.github.RepositoryEvent;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 16:15
 */
public class DashboardEntry {

  private final GithubProject project;
  private final List<RepositoryEvent> events;

  public DashboardEntry(GithubProject project, List<RepositoryEvent> events) {
    this.project = project;
    this.events = events;
  }

  public GithubProject getProject() {
    return project;
  }

  public List<RepositoryEvent> getEvents() {
    return events;
  }

}
