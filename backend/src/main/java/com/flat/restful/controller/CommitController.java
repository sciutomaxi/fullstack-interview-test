package com.flat.restful.controller;

import com.flat.restful.dto.ApiError;
import com.flat.restful.model.Commit;
import com.flat.restful.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CommitController {

    @Autowired
    CommitService commitService;

    @GetMapping("/commits")
    public List<Commit> list() {
        return commitService.listAllCommit();
    }

    @GetMapping("/commit/{id}")
    public ResponseEntity<Commit> get(@PathVariable String id) {
        try {
            Optional<Commit> commit = commitService.getCommit(id);
            return new ResponseEntity<>(commit.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/commits/")
    public ResponseEntity<?> add(@RequestBody Commit ci) {
        Optional<Commit>  commitOptional = commitService.saveCommit(ci);
        if(commitOptional.isPresent()){
            return new ResponseEntity<>(commitOptional.get(), HttpStatus.OK);
        }else{
            ApiError apiError = new ApiError("Branch inexistente", 500);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/commit/{id}")
    public ResponseEntity<?> update(@RequestBody Commit ci, @PathVariable String id) {
        try {
            commitService.getCommit(id);
            ci.setId(id);
            commitService.saveCommit(ci);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/commit/{id}")
    public void delete(@PathVariable String id) {
        commitService.deleteCommit(id);
    }

    @GetMapping("/commits/{branchName}")
    public List<Commit> getByBranch(@PathVariable String branchName) {
        return commitService.getCommitsByBranchName(branchName);
    }


}
