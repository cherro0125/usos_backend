package org.fibi.usos.controller.course.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fibi.usos.App;
import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.dto.grade.GradeDto;
import org.fibi.usos.model.course.grade.GradeValueType;
import org.fibi.usos.model.exam.ExamDateType;
import org.fibi.usos.service.course.grade.GradeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {App.class})
@AutoConfigureMockMvc
public class GradeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradeController gradeController;

    private ObjectMapper objectMapper;

    @Before
    public void before(){
        objectMapper = new ObjectMapper();
    }


    @Test
    @WithUserDetails("lecturer")
    public void addNewGradeWithValidDtoReturnModelAndUserWithAccess() throws Exception{
        GradeChangeRequestDto dto = new GradeChangeRequestDto();
        dto.setDescription("Test");
        dto.setValue(GradeValueType.EXCELLENT);
        dto.setExamDateType(ExamDateType.FIRST);
        dto.setCourseGroupId(9);
        dto.setAssignedUserId(3);
        dto.setCreatedById(5);
        mockMvc.perform(post("/grade/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }


    @Test
    @WithUserDetails("student")
    public void addNewGradeWithValidDtoReturnModelAndUserWithoutAccess() throws Exception{
        GradeChangeRequestDto dto = new GradeChangeRequestDto();
        dto.setDescription("Test");
        dto.setValue(GradeValueType.EXCELLENT);
        dto.setExamDateType(ExamDateType.FIRST);
        dto.setCourseGroupId(9);
        dto.setAssignedUserId(3);
        dto.setCreatedById(5);
        mockMvc.perform(post("/grade/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());
    }
}
