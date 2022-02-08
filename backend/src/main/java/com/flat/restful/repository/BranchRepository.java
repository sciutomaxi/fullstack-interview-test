package com.flat.restful.repository;

import com.flat.restful.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<Branch, Integer>{
}
