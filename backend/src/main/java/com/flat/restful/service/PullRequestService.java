package com.flat.restful.service;

import com.flat.restful.model.PullRequest;
import com.flat.restful.repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PullRequestService {

    @Autowired
    private PullRequestRepository pullRequestRepository;

    public List<PullRequest> listAllPullRequest() {
        return pullRequestRepository.findAll();
    }

    public PullRequest savePullRequest(PullRequest pr) {
        return pullRequestRepository.save(pr);
    }

    public PullRequest getPullRequest(Integer id) {
        return pullRequestRepository.findById(id).get();
    }

    public PullRequest putPullRequest(PullRequest pullRequest, PullRequest request) {
        setValues(pullRequest, request);

        return pullRequestRepository.save(pullRequest);
    }

    private void setValues(PullRequest pullRequest, PullRequest request) {
        if (Objects.nonNull(request.getTitle())) {
            pullRequest.setTitle(request.getTitle());
        }

        if (Objects.nonNull(request.getDescription())) {
            pullRequest.setDescription(request.getDescription());
        }

        if (Objects.nonNull(request.getStatus())) {
            pullRequest.setStatus(request.getStatus());
        }

        if (Objects.nonNull(request.getConflicts())) {
            pullRequest.setConflicts(request.getConflicts());
        }

        if (Objects.nonNull(request.getUserId())) {
            pullRequest.setUserId(request.getUserId());
        }

        if (Objects.nonNull(request.getBaseBranchId())) {
            pullRequest.setBaseBranchId(request.getBaseBranchId());
        }

        if (Objects.nonNull(request.getCompareBranchId())) {
            pullRequest.setCompareBranchId(request.getCompareBranchId());
        }
    }

    public void deletePullRequest(Integer id) {
        pullRequestRepository.deleteById(id);
    }
}
