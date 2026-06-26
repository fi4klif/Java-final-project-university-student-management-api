package ua.university.sms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.university.sms.model.dto.StudentDTO;
import ua.university.sms.service.StudentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_ShouldReturn200AndList() throws Exception {
        StudentDTO s1 = new StudentDTO();
        s1.setId(1L);
        s1.setFirstName("John");
        when(studentService.getStudents(null, null)).thenReturn(List.of(s1));

        mockMvc.perform(get("/api/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void getById_ShouldReturn200AndStudent() throws Exception {
        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("Alice");
        when(studentService.getStudentById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"));
    }

    @Test
    void create_ShouldReturn200AndSavedStudent() throws Exception {
        StudentDTO input = new StudentDTO();
        input.setFirstName("Bob");
        input.setLastName("Marley");
        input.setEmail("bob@email.com");

        StudentDTO saved = new StudentDTO();
        saved.setId(2L);
        saved.setFirstName("Bob");
        saved.setLastName("Marley");
        saved.setEmail("bob@email.com");

        when(studentService.createStudent(any(StudentDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.firstName").value("Bob"));
    }

    @Test
    void delete_ShouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());
    }
}