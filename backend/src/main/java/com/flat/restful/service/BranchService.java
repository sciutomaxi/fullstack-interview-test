package com.flat.restful.service;

import com.flat.restful.model.Branch;
import com.flat.restful.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> listAllBranch() {
        return branchRepository.findAll();
    }

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch putBranch(Branch branch, Branch branchRequest) {
        if (Objects.nonNull(branchRequest.getDescription())) {
            branch.setDescription(branchRequest.getDescription());
        }
        if (Objects.nonNull(branchRequest.getName())) {
            branch.setName(branchRequest.getName());
        }
        if (Objects.nonNull(branchRequest.getUserId())) {
            branch.setUserId(branchRequest.getUserId());
        }

        return branchRepository.save(branch);
    }

    public Optional<Branch> getBranch(Integer id) {
        return branchRepository.findById(id);
    }

    public void deleteBranch(Integer id) {
        branchRepository.deleteById(id);
    }

}
