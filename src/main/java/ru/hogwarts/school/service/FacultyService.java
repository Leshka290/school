package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    public Collection<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.getReferenceById(facultyId);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public void deleteFaculty(Faculty faculty) {
        facultyRepository.delete(faculty);
    }

    public Collection<Faculty> filterFacultyByColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
