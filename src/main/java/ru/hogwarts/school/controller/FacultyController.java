package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Optional;

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

    @GetMapping("{facultyId}")
    @Operation(summary = "Получение факультета по id")
    public ResponseEntity<?> getFacultyById(@PathVariable long facultyId) {
        Optional<Faculty> faculty = facultyService.getFacultyById(facultyId);
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
    @Operation(summary = "Удаление факультета по di")
    public ResponseEntity<?> deleteFaculty(@PathVariable long facultyId) {
        Optional<Faculty> faculty = facultyService.getFacultyById(facultyId);
        facultyService.deleteFaculty(facultyId);
        if (faculty.isEmpty()) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping()
    @Operation(summary = "Удаление факультета")
    public ResponseEntity<?> deleteFaculty(@RequestBody Faculty faculty) {
        facultyService.deleteFaculty(faculty);
        if (faculty == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }

//    @GetMapping("/filterColor/{color}")
//    @Operation(summary = "Получение факультетов с фильтром по цвету")
//    public ResponseEntity<?> filterFacultyByColor(@PathVariable String color) {
//        return ResponseEntity.ok(facultyService.filterFacultyByColor(color));
//    }

    @GetMapping()
    @Operation(summary = "Получение всех факультов по имени или цвету")
    public ResponseEntity<?> findFacultiesByNameOrColor(@RequestParam(required = false) String name
            , @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByNameOrColor(name, color));
    }

    @GetMapping("/getFacultyByStudent")
    @Operation(summary = "Получение факультета студента")
    public ResponseEntity<?> getFacultyByStudent(@RequestBody Student student) {
        return ResponseEntity.ok(facultyService.getFacultyByStudent(student));
    }
}
