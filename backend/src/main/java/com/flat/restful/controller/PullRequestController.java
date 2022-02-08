package com.flat.restful.controller;

import com.flat.restful.dto.ApiError;
import com.flat.restful.model.PullRequest;
import com.flat.restful.service.PullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pulls")
@CrossOrigin("*")
public class PullRequestController {

    @Autowired
    private PullRequestService pullRequestService;

    @GetMapping("")
    public List<PullRequest> list() {
        return pullRequestService.listAllPullRequest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PullRequest> get(@PathVariable Integer id) {
        try {
            PullRequest pullRequest = pullRequestService.getPullRequest(id);
            return new ResponseEntity<>(pullRequest, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody PullRequest pr) {
        try {
            PullRequest prSaved = pullRequestService.savePullRequest(pr);
            return new ResponseEntity<>(prSaved, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ApiError apiError = new ApiError("Ya existe un pull request con estas branches", 500);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PullRequest request, @PathVariable Integer id) {
        try {
            PullRequest pullRequest = pullRequestService.getPullRequest(id);
            PullRequest prSaved = pullRequestService.putPullRequest(pullRequest, request);
            return new ResponseEntity<>(prSaved, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pullRequestService.deletePullRequest(id);
    }

}
