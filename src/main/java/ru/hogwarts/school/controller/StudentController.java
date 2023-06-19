package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Создание студента")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Получение всех студентов")
    public ResponseEntity<?> findAll() {
        Collection<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("{studentId}")
    @Operation(summary = "Получение студента по id")
    public ResponseEntity<?> getStudentById(@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    @Operation(summary = "Изменение студента")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{studentId}")
    @Operation(summary = "Удаление студента по id")
    public ResponseEntity<?> deleteStudent(@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);
        studentService.deleteStudent(studentId);
        if (student == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping()
    @Operation(summary = "Удаление студента")
    public ResponseEntity<?> deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
        if (student == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{age}")
    @Operation(summary = "Получение студентов с фильтром по возрасту")
    public ResponseEntity<?> getFilterStudentByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.filterStudentByAge(age));
    }

    @GetMapping("/age")
    @Operation(summary = "Получение всех студентов с определенным возрастом")
    public ResponseEntity<?> findByAgeBetween(@RequestParam int ageMin, @RequestParam int ageMax) {
        Collection<Student> students = studentService.findByAgeBetween(ageMin, ageMax);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/faculty/{studentId}")
    @Operation(summary = "Получение факультета студента")
    public ResponseEntity<?> getStudentsByFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.getStudentById(studentId).getFaculty();
        return ResponseEntity.ok(faculty);
    }
}
