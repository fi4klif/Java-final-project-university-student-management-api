package ua.university.sms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.university.sms.model.dto.EnrollmentDTO;
import ua.university.sms.service.EnrollmentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void enroll_ShouldReturn200AndSavedEnrollment() throws Exception {
        EnrollmentDTO input = new EnrollmentDTO();
        input.setStudentId(1L);
        input.setCourseId(1L);

        EnrollmentDTO saved = new EnrollmentDTO();
        saved.setId(10L);
        saved.setStudentId(1L);
        saved.setCourseId(1L);
        saved.setPaid(false);

        when(enrollmentService.enrollStudent(any(EnrollmentDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/enrollments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.paid").value(false));
    }

    @Test
    void updateGrade_ShouldReturn200AndUpdatedGrade() throws Exception {
        EnrollmentDTO updated = new EnrollmentDTO();
        updated.setId(1L);
        updated.setGrade(5.0);

        when(enrollmentService.updateGrade(eq(1L), eq(5.0))).thenReturn(updated);

        mockMvc.perform(patch("/api/enrollments/1/grade")
                .param("grade", "5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade").value(5.0));
    }
}