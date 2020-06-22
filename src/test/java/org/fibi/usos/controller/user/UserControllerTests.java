package org.fibi.usos.controller.user;

import org.fibi.usos.App;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {App.class})
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithUserDetails("rector")
    public void getAllUserUsingUserWithValidRole() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithUserDetails("student")
    public void getAllUserUsingUserWithNotValidRole() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithUserDetails("rector")
    public void getAllUserInSpecificRoleUsingUserWithValidRole() throws Exception {
        mockMvc.perform(get("/user/DEAN/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[*].role").value("DEAN"));
    }

    @Test
    @WithUserDetails("rector")
    public void getAllUserInSpecificNotValidRolreUsingUserWithValidRole() throws Exception {
        mockMvc.perform(get("/user/AAA/all"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("student")
    public void getAllUserInSpecificRoleUsingUserWithNotValidRole() throws Exception {
        mockMvc.perform(get("/user/DEAN/all"))
                .andExpect(status().isUnauthorized());
    }
}
