package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private long generatedStudentId = 0;

    public Collection<Student> findAll() {
        return studentMap.values();
    }

    public Student createStudent(Student student) {
        student.setId(++generatedStudentId);
        studentMap.put(generatedStudentId, student);
        return student;
    }

    public Student getStudentById(long studentId) {
        return studentMap.get(studentId);
    }

    public Student updateStudent(long studentId, Student student) {
        return studentMap.put(studentId, student);
    }

    public Student deleteStudent(long studentId) {
        return studentMap.remove(studentId);
    }
//    public Student findById(long id) {
//        return studentMap.get(id);
//    }
//
//    public Student findByName(String name) {
//        return studentMap.values().stream()
//                .filter(student -> student.getName().equals(name))
//                .findFirst()
//                .orElseThrow();
//    }
//
//    public Student save(Student student) {
//        if (student.getId() != 0) {
//            long _id = student.getId();
//
//            for (long idx = 0; idx < studentMap.size(); idx++)
//                if (_id == studentMap.get(idx).getId()) {
//                    studentMap.put(idx, student);
//                    count++;
//                    break;
//                }
//
//            return student;
//        }
//
//        student.setId(++generatedStudentId);
//        studentMap.values().add(student);
//        return student;
//    }
//
//    public Student update(long id, String name) {
//        return studentMap.values().stream()
//                .filter(student -> student.getId() == id)
//                .peek(e -> e.setName(name))
//                .findFirst()
//                .orElseThrow();
//    }
//
//    public Student update(long id, int age) {
//        return studentMap.values().stream()
//                .filter(student -> student.getId() == id)
//                .peek(e -> e.setAge(age))
//                .findFirst()
//                .orElseThrow();
//    }
//
//
//    public boolean delete(String name) {
//        if (studentMap.values().removeIf(student -> name.equals(student.getName()))){
//            count--;
//            return true;
//        }
//        throw new NoSuchElementException();
//    }
}
