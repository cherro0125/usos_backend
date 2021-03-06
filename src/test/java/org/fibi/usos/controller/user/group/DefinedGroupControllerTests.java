package org.fibi.usos.controller.user.group;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fibi.usos.App;
import org.fibi.usos.dto.user.group.DefinedGroupRequestDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {App.class})
@AutoConfigureMockMvc
public class DefinedGroupControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void before(){
        objectMapper = new ObjectMapper();
    }



    @Test
    @WithUserDetails("dean")
    public void getAllDefinedGroupsWithValidRole() throws Exception{
        mockMvc.perform(get("/definedGroups/all"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    public void getAllDefinedGroupsWithNotValidRole() throws Exception {
        mockMvc.perform(get("/definedGroups/all"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("dean")
    public void getSpecificDefinedGroupBelongingToDegreeCourseWithValidRole() throws Exception {
        mockMvc.perform(get("/definedGroups/8"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("dean")
    public void addNewGradeWithValidDtoReturnModelAndUserWithAccess() throws Exception{
        DefinedGroupRequestDto dto = new DefinedGroupRequestDto();
        dto.setName("Test group");
        dto.setDescription("Test description");
        dto.setDegreeCourseId(1L);
        mockMvc.perform(post("/definedGroups/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
