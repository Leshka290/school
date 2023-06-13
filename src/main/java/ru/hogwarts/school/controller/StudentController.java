package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("student")
@RestController
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("/findAll")
    public Collection<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student.getId(), student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable long studentId) {
        Student student = studentService.deleteStudent(studentId);
        if (student == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/filterAge/{age}")
    public ResponseEntity<?> getFilterStudentByAge(@PathVariable int age) {
        Collection<Student> studentCollection = studentService.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentCollection);
    }

//    @GetMapping("/save")
//    public Student save(@RequestParam String name, @RequestParam int age) {
//        Student student = new Student(name, age);
//        return studentService.save(student);
//    }
//
//    @GetMapping("/findById")
//    public Student findById(@RequestParam long id) {
//        return studentService.findById(id);
//    }
//
//    @GetMapping("/findByName")
//    public Student findByName(@RequestParam String name) {
//        return studentService.findByName(name);
//    }
//
//    @GetMapping("/updateAge")
//    public Student updateAge(@RequestParam long id, @RequestParam int age) {
//        return studentService.update(id, age);
//    }
//
//    @GetMapping("/updateName")
//    public Student updateAge(@RequestParam long id, @RequestParam String name) {
//        return studentService.update(id, name);
//    }
//
//    @GetMapping("/deleteById")
//    public boolean deleteById(@RequestParam long id) {
//        return studentService.delete(id);
//    }
//
//    @GetMapping("/deleteByName")
//    public boolean deleteById(@RequestParam String name) {
//        return studentService.delete(name);
//    }
//
//    @GetMapping("/deleteAll")
//    public boolean deleteAll() {
//        return studentService.deleteAll();
//    }
}
