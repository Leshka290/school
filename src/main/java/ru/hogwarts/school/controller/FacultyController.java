package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createFaculty);
    }

    @GetMapping("/findAll")
    public Collection<Faculty> findAll() {
        return facultyService.findAll();
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<?> getFacultyById(@PathVariable long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if(faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty.getId(), faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<?> deleteFaculty(@PathVariable long facultyId) {
        Faculty faculty = facultyService.deleteFaculty(facultyId);
        if(faculty == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/filterColor/{color}")
    public ResponseEntity<?> filterFacultyByColor(@PathVariable String color) {
        Collection<Faculty> facultyCollection = facultyService.findAll().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
        return ResponseEntity.ok(facultyCollection);
    }

//    @GetMapping("/save")
//    public Faculty save(@RequestParam String name, @RequestParam String color) {
//        Faculty faculty = new Faculty(name, color);
//        return facultyService.save(faculty);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity findById(@PathVariable long id) {
//
//        return ResponseEntity.ok(facultyService.findById(id));
//    }
//
//    @GetMapping("/{name}")
//    public Faculty findByName(@PathVariable String name) {
//        return facultyService.findByName(name);
//    }
//
//    @GetMapping("/findAll")
//    public Collection<Faculty> findAll() {
//        return facultyService.findAll();
//    }
//
//    @PutMapping("/updateName")
//    public ResponseEntity updateName(@RequestParam long id, @RequestParam String name) {
//
//        return facultyService.updateName(id, name);
//    }
//
//    @PutMapping("/updateColor")
//    public Faculty updateColor(@RequestParam long id, @RequestParam String color) {
//        return facultyService.updateColor(id, color);
//    }
//
//    @GetMapping("/deleteById")
//    public boolean delete(@RequestParam long id) {
//        return facultyService.delete(id);
//    }
//
//    @GetMapping("/deleteByName")
//    public boolean delete(@RequestParam String name) {
//        return facultyService.delete(name);
//    }
//
//    @GetMapping("/deleteAll")
//    public boolean deleteAll() {
//        return facultyService.deleteAll();
//    }
}
