package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private long generatedFacultyId = 0;

    public Collection<Faculty> findAll() {
        return facultyMap.values();
    }

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++generatedFacultyId);
        facultyMap.put(generatedFacultyId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(long facultyId) {
        return facultyMap.get(facultyId);
    }

    public Faculty updateFaculty(long facultyId, Faculty faculty) {
        return facultyMap.put(facultyId, faculty);
    }

    public Faculty deleteFaculty(long facultyId) {
        return facultyMap.remove(facultyId);
    }

//    public Faculty findById(long id) {
//        return facultyMap.get(id);
//    }
//
//    public Faculty findByName(String name) {
//        return facultyMap.values().stream()
//                .filter(faculty -> faculty.getName().equals(name))
//                .findFirst()
//                .orElseThrow();
//    }
//
//    public Faculty save(Faculty faculty) {
//        if (faculty.getId() != 0) {
//            long _id = faculty.getId();
//
//            for (long idx = 0; idx < facultyMap.size(); idx++)
//                if (_id == facultyMap.get(idx).getId()) {
//                    facultyMap.put(idx, faculty);
//                    break;
//                }
//
//            return faculty;
//        }
//
//        faculty.setId(++generatedFacultyId);
//        facultyMap.values().add(faculty);
//        return faculty;
//    }
//
//    public Faculty updateName(long id, String name) {
//        return facultyMap.values().stream()
//                .filter(faculty -> faculty.getId() == id)
//                .peek(e -> e.setName(name))
//                .findFirst()
//                .orElseThrow();
//    }
//
//    public Faculty updateColor(long id, String color) {
//        return facultyMap.values().stream()
//                .filter(faculty -> faculty.getId() == id)
//                .peek(e -> e.setColor(color))
//                .findFirst()
//                .orElseThrow();
//    }
//
//    public boolean delete(long id) {
//        if(facultyMap.values().removeIf(faculty -> id == faculty.getId())) {
//            count--;
//            return true;
//        }
//        throw new NoSuchElementException();
//    }
//
//    public boolean delete(String name) {
//        if (facultyMap.values().removeIf(faculty -> name.equals(faculty.getName()))){
//            count--;
//            return true;
//        }
//        throw new NoSuchElementException();
//    }
//
//    public boolean deleteAll() {
//        if(facultyMap.values().removeAll(findAll())) {
//            generatedFacultyId = 0;
//            count = 0;
//            return true;
//        }
//        return false;
//    }
}
