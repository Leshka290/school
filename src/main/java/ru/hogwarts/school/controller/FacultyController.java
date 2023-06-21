package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @Operation(summary = "Создание факультета")
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createFaculty);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Получение всех факультетов")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(facultyService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение факультета по id")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    @Operation(summary = "Изменение факультета")
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("{facultyId}")
    @Operation(summary = "Удаление факультета по id")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
            return ResponseEntity.ok().build();

    }

    @DeleteMapping()
    @Operation(summary = "Удаление факультета")
    public ResponseEntity<?> deleteFaculty(@RequestBody Faculty faculty) {
        facultyService.deleteFaculty(faculty);
            return ResponseEntity.ok().build();

    }

    @GetMapping()
    @Operation(summary = "Получение всех факультов по имени или цвету")
    public ResponseEntity<?> findFacultiesByNameOrColor(@RequestParam(required = false) String name
            , @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByNameOrColor(name, color));
    }

    @GetMapping("/students/{studentId}")
    @Operation(summary = "Получение студентов факультета")
    public ResponseEntity<List<Student>> getFacultyByStudent(@PathVariable Long studentId) {
        List<Student> students = facultyService.getFacultyById(studentId).getStudentList();
        return ResponseEntity.ok(students);
    }
}
