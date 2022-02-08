package com.flat.restful;

import com.flat.restful.config.H2JpaConfig;
import com.flat.restful.model.Branch;
import com.flat.restful.model.Commit;
import com.flat.restful.repository.BranchRepository;
import com.flat.restful.repository.CommitRepository;
import com.flat.restful.service.BranchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RestfulApplication.class, H2JpaConfig.class})
public class CommitRepositoryTests {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private BranchService branchService;

    @Test
    public void testSaveAndFindIt(){
        // given
        Commit commitExpected = Commit.builder()
                .id("asd123")
                .userId(123)
                .branchId(1)
                .message("otro commit")
                .numberOfModifiedFiles(4)
                .build();
        //when:
        Commit commitSaved = commitRepository.save(commitExpected);
        Commit commitFound = commitRepository.findById(commitSaved.getId()).get();

        //then
        assertThat(commitFound).isNotNull();
        assertThat(commitFound.getId()).isEqualTo(commitSaved.getId());
        assertThat(commitFound.getMessage()).isEqualTo(commitSaved.getMessage());
        assertThat(commitFound.getBranchId()).isEqualTo(commitSaved.getBranchId());
        assertThat(commitFound.getDateCreated()).isBefore(new Date());
    }

    @Test
    public void testFindByBranchName(){
        // given
        Branch branch = Branch.builder()
                .name("develop")
                .userId(1)
                .build();

        branchService.saveBranch(branch);

        Commit commitFirst = Commit.builder()
                .id("asd123")
                .userId(123)
                .branchId(1)
                .message("first commit")
                .numberOfModifiedFiles(4)
                .build();

        Commit commitSecond = Commit.builder()
                .id("asd12344243")
                .userId(123)
                .branchId(1)
                .message("Second commit")
                .numberOfModifiedFiles(2)
                .build();

        commitRepository.save(commitFirst);
        commitRepository.save(commitSecond);

        List<Commit> response = commitRepository.findByBranchName(branch.getName());

        //then
        assertThat(response).isNotNull();
        Assertions.assertTrue(response.size() > 1);
    }


}
