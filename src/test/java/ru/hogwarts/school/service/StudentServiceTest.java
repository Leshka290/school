package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();
    private final Student studentTest = new Student("Max", 25);

    @Test
    public void shouldCreateFaculty() {
        assertEquals(studentTest, studentService.createStudent(studentTest));
    }

    @Test
    public void shouldGetFacultyById() {
        studentService.createStudent(studentTest);

        assertEquals(studentTest, studentService.getStudentById(studentTest.getId()));
    }

    @Test
    public void shouldUpdateFaculty() {
        Student studentUpdate = new Student("Petr", 30);

        studentService.createStudent(studentTest);
        studentService.updateStudent(studentTest.getId(), studentUpdate);

        assertEquals(studentUpdate, studentService.getStudentById(studentTest.getId()));
    }

    @Test
    public void shouldDeleteFaculty() {
        studentService.createStudent(studentTest);
        studentService.deleteStudent(studentTest.getId());

        assertNull(studentService.getStudentById(studentTest.getId()));
    }

    @Test
    public void shouldFindAll() {
        final Collection<Student> studentCollectionTest = new ArrayList<>(List.of(studentTest));

        studentService.createStudent(studentTest);

        assertTrue(studentService.findAll().containsAll(studentCollectionTest));
    }
}
