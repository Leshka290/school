package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class FacultyServiceTest {

    private final FacultyService facultyService = new FacultyService();
    private final Faculty facultyTest = new Faculty("name", "color");

    @Test
    public void shouldCreateFaculty() {
        assertEquals(facultyTest, facultyService.createFaculty(facultyTest));
    }

    @Test
    public void shouldGetFacultyById() {
        facultyService.createFaculty(facultyTest);

        assertEquals(facultyTest, facultyService.getFacultyById(facultyTest.getId()));
    }

    @Test
    public void shouldUpdateFaculty() {
        Faculty facultyUpdate = new Faculty("name2", "color2");

        facultyService.createFaculty(facultyTest);
        facultyService.updateFaculty(facultyTest.getId(), facultyUpdate);

        assertEquals(facultyUpdate, facultyService.getFacultyById(facultyTest.getId()));
    }

    @Test
    public void shouldDeleteFaculty() {
        facultyService.createFaculty(facultyTest);
        facultyService.deleteFaculty(facultyTest.getId());

        assertNull(facultyService.getFacultyById(facultyTest.getId()));
    }

    @Test
    public void shouldFindAll() {
        final Collection<Faculty> facultyCollectionTest = new ArrayList<>(List.of(facultyTest));

        facultyService.createFaculty(facultyTest);

        assertTrue(facultyService.findAll().containsAll(facultyCollectionTest));
    }
}
