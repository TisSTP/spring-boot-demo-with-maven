package com.example.demo.events;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 10-11-2018, 22:54
 */
public interface GithubProjectRepository extends PagingAndSortingRepository<GithubProject, Long> {

  GithubProject findByRepoName(String repoName);

}
