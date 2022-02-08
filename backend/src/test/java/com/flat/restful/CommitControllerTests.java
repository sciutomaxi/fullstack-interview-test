package com.flat.restful;


import com.flat.restful.controller.CommitController;
import com.flat.restful.model.Commit;
import com.flat.restful.service.CommitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {CommitController.class})
public class CommitControllerTests {

    @Autowired
    private CommitController commitController;

    @MockBean
    private CommitService commitService;

    private Commit commit;

    private String id = "f41cf987a05e494c8b1013a82a36c187";

    @BeforeEach
    public void setUp() {
        int userId = 1;
        int branchId = 15;
        int numberModifiedFiles = 5;
        String message = "mi primer commit";
        commit = Commit.builder()
                .id(id)
                .userId(userId)
                .branchId(branchId)
                .message(message)
                .numberOfModifiedFiles(numberModifiedFiles)
                .build();
    }

    @Test
    public void testGetOk() {
        doReturn(Optional.of(commit)).when(commitService).getCommit(id);
        //When:
        ResponseEntity responseEntity = commitController.get(id);
        //Then:
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetFail() {
        //given:
        String idOther = "asd123";

        doReturn(Optional.empty()).when(commitService).getCommit(idOther);

        ResponseEntity<Commit> response = commitController.get(idOther);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
