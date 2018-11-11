package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.github.GithubClient;
import com.example.demo.github.RepositoryEvent;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 15:40
 */
@Component
public class GithubHealthIndicator implements HealthIndicator {

  private final GithubClient githubClient;

  public GithubHealthIndicator(GithubClient githubClient) {
    this.githubClient = githubClient;
  }

  @Override
  public Health health() {
    try {
      ResponseEntity<RepositoryEvent[]> response = this.githubClient.fetchEvents("spring-projects", "spring-boot");

      if (response.getStatusCode().is2xxSuccessful()) {
        return Health.up().build();

      } else {
        return Health.down().build();
      }
    } catch (Exception e) {
      return Health.down(e).build();
    }
  }

}
