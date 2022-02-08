package com.flat.restful.service;

import com.flat.restful.model.Branch;
import com.flat.restful.model.Commit;
import com.flat.restful.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private BranchService branchService;

    public List<Commit> listAllCommit() {
        return commitRepository.findAll();
    }

    public Optional<Commit> saveCommit(Commit ci) {
        Optional<Branch> optionalBranch = branchService.getBranch(ci.getBranchId());
        if (optionalBranch.isPresent()) {
            return Optional.of(commitRepository.save(ci));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Commit> getCommit(String id) {
        return commitRepository.findById(id);
    }

    public List<Commit> getCommitsByBranchName(String branchName) {
        return commitRepository.findByBranchName(branchName);
    }

    public void deleteCommit(String id) {
        commitRepository.deleteById(id);
    }
}

