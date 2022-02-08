package com.flat.restful.repository;

import com.flat.restful.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommitRepository  extends JpaRepository<Commit, String> {

    @Query("SELECT DISTINCT ci from Commit ci " +
            " INNER JOIN Branch br ON ci.branchId=br.id " +
            " WHERE br.name = :branchName")
    List<Commit> findByBranchName(String branchName);

}
