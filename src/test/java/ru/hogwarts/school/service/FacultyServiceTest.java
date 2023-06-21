package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @InjectMocks
    private FacultyService facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    private final Faculty facultyTest = new Faculty();

    public Faculty getFacultyTest(Faculty faculty) {
        faculty.setId(1);
        faculty.setName("Faculty");
        faculty.setColor("Green");
        return faculty;
    }

    @Test
    public void shouldCreateFaculty() {
        Mockito.when(facultyRepository.save(facultyTest)).thenReturn(getFacultyTest(facultyTest));

        assertNotNull(facultyRepository);
        assertNotNull(facultyService.createFaculty(facultyTest));
        assertEquals(facultyTest, facultyService.createFaculty(facultyTest));
    }

    @Test
    public void shouldGetFacultyById() {
        Mockito.when(facultyRepository.save(facultyTest)).thenReturn(getFacultyTest(facultyTest));
        Mockito.when(facultyRepository.findById(facultyTest.getId())).thenReturn(Optional.ofNullable(getFacultyTest(facultyTest)));
        facultyService.createFaculty(facultyTest);
        facultyRepository.save(facultyTest);

        assertEquals(facultyTest, facultyService.getFacultyById(facultyTest.getId()));
    }

    @Test
    public void shouldUpdateFaculty() {
        Faculty facultyUpdate = new Faculty();

        Mockito.when(facultyRepository.save(facultyTest)).thenReturn(getFacultyTest(facultyTest));
        Mockito.when(facultyRepository.save(facultyUpdate)).thenReturn(facultyUpdate);

        facultyService.createFaculty(facultyTest);
        facultyService.updateFaculty(facultyUpdate);

        assertEquals(facultyUpdate, facultyService.updateFaculty(facultyUpdate));
    }

    @Test
    public void shouldDeleteFaculty() {
        Mockito.when(facultyRepository.save(facultyTest)).thenReturn(getFacultyTest(facultyTest));

        facultyService.createFaculty(facultyTest);
        facultyService.deleteFaculty(facultyTest.getId());

        assertNull(facultyService.getFacultyById(facultyTest.getId()));
    }

    @Test
    public void shouldFindAll() {
        final List<Faculty> facultyListTest = new ArrayList<>(List.of(facultyTest));

        Mockito.when(facultyRepository.findAll()).thenReturn(facultyListTest);

        facultyService.createFaculty(facultyTest);

        assertTrue(facultyService.findAll().containsAll(facultyListTest));
    }
}
