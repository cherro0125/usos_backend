package org.fibi.usos.controller;


import org.fibi.usos.App;
import org.fibi.usos.model.exam.ExamDateType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {App.class})
@AutoConfigureMockMvc
public class UtilControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void enumUtilWithValidNameReturnCorrectValues() throws Exception {
        mockMvc.perform(get("/util/enum/ExamDateType/values")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Enum found."))
                .andExpect(jsonPath("$.enumValues").isArray())
                .andExpect(jsonPath("$.enumValues", Matchers.containsInAnyOrder(Arrays
                        .stream(ExamDateType.class.getFields())
                        .map(Field::getName)
                        .distinct()
                        .toArray())));
    }

    @Test
    public void enumUtilWithNotValidNameReturn403() throws Exception {
        mockMvc.perform(get("/util/enum/Test/values"))
                .andExpect(status().isNoContent());
    }
}
