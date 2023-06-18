package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
    private final Student studentTest = new Student();

    public Student getStudentTest(Student student) {
        student.setId(1);
        student.setName("Petr");
        student.setAge(30);
        return student;
    }

    @Test
    public void shouldCreateStudent() {
        Mockito.when(studentRepository.save(studentTest)).thenReturn(getStudentTest(studentTest));

        assertNotNull(studentRepository);
        assertNotNull(studentService.createStudent(studentTest));
        assertEquals(studentTest, studentService.createStudent(studentTest));
    }

    @Test
    public void shouldGetStudentById() {
        Mockito.when(studentRepository.save(studentTest)).thenReturn(getStudentTest(studentTest));
        Mockito.when(studentRepository.findById(studentTest.getId())).thenReturn(Optional.ofNullable(getStudentTest(studentTest)));
        studentService.createStudent(studentTest);
        studentRepository.save(studentTest);

        assertEquals(studentTest, studentService.getStudentById(studentTest.getId()).orElseThrow());
    }

    @Test
    public void shouldUpdateStudent() {
        Student studentUpdate = new Student();

        Mockito.when(studentRepository.save(studentTest)).thenReturn(getStudentTest(studentTest));
        Mockito.when(studentRepository.save(studentUpdate)).thenReturn(studentUpdate);

        studentService.createStudent(studentTest);
        studentService.updateStudent(studentUpdate);

        assertEquals(studentUpdate, studentService.updateStudent(studentUpdate));
    }

    @Test
    public void shouldDeleteStudent() {
        Mockito.when(studentRepository.save(studentTest)).thenReturn(getStudentTest(studentTest));

        studentService.createStudent(studentTest);
        studentService.deleteStudent(studentTest.getId());

        assertTrue(studentService.getStudentById(studentTest.getId()).isEmpty());
    }

    @Test
    public void shouldFindAll() {
        final List<Student> studentListTest = new ArrayList<>(List.of(studentTest));

        Mockito.when(studentRepository.findAll()).thenReturn(studentListTest);

        studentService.createStudent(studentTest);

        assertTrue(studentService.findAll().containsAll(studentListTest));
    }
}
