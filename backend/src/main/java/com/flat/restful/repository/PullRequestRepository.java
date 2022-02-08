package com.flat.restful.repository;

import com.flat.restful.model.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PullRequestRepository extends JpaRepository<PullRequest, Integer> {

}
