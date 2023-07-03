package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Collection<Faculty> findAll() {
        Collection<Faculty> faculties = facultyRepository.findAll();

        logger.info("Was invoked method for get all faculties {}", faculties);
        return faculties;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow();

        logger.info("Was invoked method for get faculty by id {}", faculty);
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for get faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        logger.info("Was invoked method for delete faculty by id {}", facultyRepository.findById(facultyId));
        facultyRepository.deleteById(facultyId);
    }

    public void deleteFaculty(Faculty faculty) {
        logger.info("Was invoked method for delete faculty {}", faculty);
        facultyRepository.delete(faculty);
    }


    public Faculty findFacultiesByNameOrColor(String name, String color) {
        Faculty faculty = facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);

        logger.info("Was invoked method for find faculties by name or color {}", faculty);
        return faculty;
    }

    public String findFacultyMaxLength() {
        String faculty = facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .min(Comparator.comparing(String::length)).orElseThrow();
        logger.info("Was invoked method for find faculty max length {}", faculty);
        return faculty;
    }

    public int getSum() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);

        return sum;
    }
}
