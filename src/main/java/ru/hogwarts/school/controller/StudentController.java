package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    @Operation(summary = "Получение студента по id")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    @Operation(summary = "Изменение студента")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{studentId}")
    @Operation(summary = "Удаление студента по id")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping()
    @Operation(summary = "Удаление студента")
    public ResponseEntity<?> deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filterStudentAge/{age}")
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

    @GetMapping("/students")
    @Operation(summary = "Получение количества студентов")
    public ResponseEntity<?> getStudentsCount() {
        Long count = studentService.getStudentsCount();
        return ResponseEntity.ok(count);
    }

//    @GetMapping("/students/avgAge2")
//    @Operation(summary = "Получение количества студентов")
//    public ResponseEntity<?> getAvgAgeStudents () {
//        Long count = studentService.getStudentsCount();
//        return ResponseEntity.ok(count);
//    }

    @GetMapping("/students/avgAge")
    @Operation(summary = "Получение среднего возраста студентов")
    public ResponseEntity<?> getAvgAgeStudents() {
        int avgAge = studentService.getAvgAgeStudents();
        return ResponseEntity.ok(avgAge);
    }

    @GetMapping("/students/avgAgeStream")
    @Operation(summary = "Получение среднего возраста студентов с помощью стримов")
    public ResponseEntity<?> getAvgAgeStudentsStream() {
        int avgAge = studentService.getAvgAgeStudentsStream();
        return ResponseEntity.ok(avgAge);
    }


    @GetMapping("/lastStudents")
    @Operation(summary = "Получение последних 5 студентов")
    public ResponseEntity<?> getLastStudents() {
        Collection<Student> students = studentService.getLastStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/firstCharA")
    @Operation(summary = "Получение имен студентов начинающихся с буквы А")
    public ResponseEntity<?> findStudentsFirstCharA() {
        Collection<String> students = studentService.findStudentsFirstCharA();
        return ResponseEntity.ok(students);
    }
}
