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
public class StudentControllerTest {

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

    final private String NAME = "Alex";
    final private int AGE = 20;
    final private long ID = 1;

    @Test
    public void createStudentTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(object.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }

    @Test
    public void updateStudentTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.age").value(AGE));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        doNothing().when(studentRepository).delete(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentByIdTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        doNothing().when(studentRepository).deleteById(student.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        Collection<Student> students = List.of(student);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findAll()).thenReturn(List.copyOf(students));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFilterStudentByAgeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filterStudentAge/{age}", AGE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByAgeBetweenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age")
                        .param("ageMin", "19")
                        .param("ageMax", "21")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentsByFacultyTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", NAME);
        object.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty/{studentId}", ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
