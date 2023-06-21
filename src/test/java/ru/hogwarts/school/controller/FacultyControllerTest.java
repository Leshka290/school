package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private AvatarController avatarController;

    @InjectMocks
    private StudentController studentController;

    @InjectMocks
    private FacultyController facultyController;

    final private String NAME = "Griffindor";
    final private String COLOR = "Green";
    final private long ID = 1;

    @Test
    public void createStudentTest() throws Exception{
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(object.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void getFacultyByIdTest() throws Exception{
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void updateFacultyTest() throws Exception{
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void findAllTest() throws Exception{
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        Collection<Faculty> faculties = List.of(faculty);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findAll()).thenReturn(List.copyOf(faculties));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        doNothing().when(facultyRepository).delete(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFacultyByIdTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        doNothing().when(facultyRepository).deleteById(faculty.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultiesByNameOrColorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .param("name", NAME)
                        .param("color", COLOR)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyByStudentTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students/{studentId}", ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}