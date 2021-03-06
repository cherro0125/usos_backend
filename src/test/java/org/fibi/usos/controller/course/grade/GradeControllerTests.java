package org.fibi.usos.controller.course.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fibi.usos.App;
import org.fibi.usos.dto.grade.GradeChangeRequestDto;
import org.fibi.usos.model.course.grade.GradeValueType;
import org.fibi.usos.model.exam.ExamDateType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {App.class})
@AutoConfigureMockMvc
public class GradeControllerTests {
    @Autowired
    private MockMvc mockMvc;
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

    @Test
    @WithUserDetails("student")
    public void getGradesForStudentWithValidRole() throws Exception{
        mockMvc.perform(get("/grade/student/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    @WithUserDetails("student")
    public void getGradesForStudentWithValidRoleButNoGrades() throws Exception{
        mockMvc.perform(get("/grade/student/666"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @WithUserDetails("lecturer")
    public void getGradesForStudentWithNotValidRole() throws Exception{
        mockMvc.perform(get("/grade/student/3"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("lecturer")
    public void getGradesForLecturerWithValidRole() throws Exception{
        mockMvc.perform(get("/grade/lecturer/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    @WithUserDetails("lecturer")
    public void getGradesForLecturerWithValidRoleButNoGrades() throws Exception{
        mockMvc.perform(get("/grade/lecturer/666"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @WithUserDetails("student")
    public void getGradesForLecturerWithNotValidRole() throws Exception{
        mockMvc.perform(get("/grade/lecturer/5"))
                .andExpect(status().isUnauthorized());
    }
}
