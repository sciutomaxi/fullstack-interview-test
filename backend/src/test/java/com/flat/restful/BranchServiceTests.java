package com.flat.restful;

import com.flat.restful.model.Branch;
import com.flat.restful.repository.BranchRepository;
import com.flat.restful.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BranchService.class})
public class BranchServiceTests {

    @Autowired
    private BranchService branchService;

    @MockBean
    private BranchRepository branchRepository;

    private Branch branch;

    @BeforeEach
    public void setUp() {
        branch = Branch.builder()
                .id(1)
                .name("master")
                .description("rama principal")
                .userId(1)
                .build();
    }

    @Test
    public void testGetBranch() {
        //Given:
        doReturn(Optional.of(branch)).when(branchRepository).findById(branch.getId());
        //When:
        Optional<Branch> branchOptional = branchService.getBranch(branch.getId());
        //Then:
        assertNotNull(branchOptional.get());
    }

    @Test
    public void testPut() {
        //Given:
        String descriptionNew = "una nueva descripcion";
        Branch branchRequest = Branch.builder()
                .id(1)
                .description(descriptionNew)
                .build();

        doReturn(branch).when(branchRepository).save(any(Branch.class));
        //When:
        Branch branchUpdated = branchService.putBranch(branch, branchRequest);
        //Then:
        assertNotNull(branchUpdated);
        assertEquals(descriptionNew, branchUpdated.getDescription());
        assertEquals(branch.getName(), branchUpdated.getName());
    }


}
