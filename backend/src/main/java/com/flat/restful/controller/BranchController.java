package com.flat.restful.controller;

import com.flat.restful.dto.ApiError;
import com.flat.restful.model.Branch;
import com.flat.restful.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/branches")
@CrossOrigin("*")
public class BranchController {

    @Autowired
    BranchService branchService;

    @GetMapping("")
    public List<Branch> list() {
        return branchService.listAllBranch();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> get(@PathVariable Integer id) {
        try {
            Branch branch = branchService.getBranch(id).get();
            return new ResponseEntity<>(branch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Branch branch) {
        try {
            Branch branchSaved = branchService.saveBranch(branch);
            return new ResponseEntity<>(branchSaved, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ApiError apiError = new ApiError("Nombre del branch existente", 500);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Branch branchRequest, @PathVariable Integer id) {
        try {
            Branch branch = branchService.getBranch(id).get();
            Branch branchSaved = branchService.putBranch(branch, branchRequest);
            return new ResponseEntity<>(branchSaved, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            ApiError apiError = new ApiError("Nombre del branch existente", 500);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        branchService.deleteBranch(id);
    }

}
