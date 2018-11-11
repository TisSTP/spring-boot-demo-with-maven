package com.example.demo.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.github.GithubClient;
import com.example.demo.github.RepositoryEvent;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 14:10
 */
@Controller
public class EventsController {

  private final GithubProjectRepository repository;

  private final GithubClient githubClient;

  public EventsController(GithubProjectRepository repository, GithubClient githubClient) {
    this.repository = repository;
    this.githubClient = githubClient;
  }

  @GetMapping("/events/{projectName}")
  @ResponseBody
  public ResponseEntity<RepositoryEvent[]> fetchEvents(@PathVariable String projectName) {
    GithubProject project = this.repository.findByRepoName(projectName);
    if (project == null) {
      return ResponseEntity.notFound().build();
    }
    ResponseEntity<RepositoryEvent[]> response = this.githubClient
        .fetchEvents(project.getOrgName(), project.getRepoName());
    return ResponseEntity.ok()
        .eTag(response.getHeaders().getETag())
        .body(response.getBody());
  }

  @GetMapping("/")
  public String dashboard(Model model) {
    List<DashboardEntry> entries = StreamSupport
        .stream(this.repository.findAll().spliterator(), true)
        .map(p -> new DashboardEntry(p, githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
        .collect(Collectors.toList());
    model.addAttribute("entries", entries);
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    model.addAttribute("projects", repository.findAll());
    return "admin";
  }

}
