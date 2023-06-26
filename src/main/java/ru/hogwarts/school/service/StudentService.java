package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(long studentId) {
        Optional<Student> optional = studentRepository.findById(studentId);
        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return optional.get();
        }
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public Collection<Student> filterStudentByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
//        return studentRepository.findAll().stream()
//                .filter(e -> e.getAge() > ageMin && e.getAge() < ageMax)
//                .collect(Collectors.toList());
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Long getStudentsCount() {
        return studentRepository.count();
    }

    public int getAvgAgeStudents() {
        return studentRepository.getAvgAgeStudents();
//        return studentRepository.findAll().stream().collect(Collectors.averagingInt(Student::getAge)).intValue();
    }

    public Collection<Student> getLastStudents() {
        return studentRepository.getLastStudents();
//        return studentRepository.findAll().stream()
//                .sorted(Comparator.comparingInt(Student::getAge).reversed())
//                .limit(5)
//                .collect(Collectors.toList());
    }
}
