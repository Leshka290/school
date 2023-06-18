package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
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

    public Optional<Faculty> getFacultyById(long facultyId) {
        return facultyRepository.findById(facultyId);
//        return facultyRepository.findAll().stream()
//                .filter(e -> e.getId() == facultyId)
//                .findFirst().orElseThrow();
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

    public Faculty findFacultiesByNameOrColor(String nameOrColor) {
        return facultyRepository.findAll().stream()
                .filter(e -> e.getName().equalsIgnoreCase(nameOrColor) || e.getColor().equalsIgnoreCase(nameOrColor))
                .findFirst().orElseThrow();
    }

    public Faculty findFacultiesByStudent(Student student) {
        return facultyRepository.findAll().stream()
                .peek(e -> e.getStudentList().get((int)student.getId()))
                .findFirst().orElseThrow();
    }
}
