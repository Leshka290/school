package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Collection<Student> findAll() {
        Collection<Student> studentCollection = studentRepository.findAll();

        logger.info("Was invoked method for return all students {}", studentCollection);
        return studentCollection;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student {}", student);
        return studentRepository.save(student);
    }

    public Student getStudentById(long studentId) {
        Optional<Student> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) {
            logger.error("There is not student with id = " + studentId);
            throw new NoSuchElementException();
        } else {
            logger.info("Was invoked method for get student {}", studentRepository.findById(studentId));
            return optional.get();
        }
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method for delete student by id {}", studentRepository.findById(studentId));

        studentRepository.deleteById(studentId);
    }

    public void deleteStudent(Student student) {
        logger.info("Was invoked method for delete student {}", student);

        studentRepository.delete(student);
    }

    public Collection<Student> filterStudentByAge(int age) {
        Collection<Student> studentCollection = studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());

        logger.info("Was invoked method for find student by age {}", studentCollection);
        return studentCollection;
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
//        return studentRepository.findAll().stream()
//                .filter(e -> e.getAge() > ageMin && e.getAge() < ageMax)
//                .collect(Collectors.toList());
        Collection<Student> studentCollection = studentRepository.findByAgeBetween(ageMin, ageMax);

        logger.info("Was invoked method for find student by age between {}", studentCollection);
        return studentCollection;
    }

    public Long getStudentsCount() {
        long count = studentRepository.count();

        logger.info("Was invoked method for get students count {}", count);
        return count;
    }

    public int getAvgAgeStudents() {
        int avgAge = studentRepository.getAvgAgeStudents();

        logger.info("Was invoked method for get average age students {}", avgAge);
        return avgAge;
//        return studentRepository.findAll().stream().collect(Collectors.averagingInt(Student::getAge)).intValue();
    }

    public Collection<Student> getLastStudents() {
        Collection<Student> studentCollection = studentRepository.getLastStudents();

        logger.info("Was invoked method for get last five students {}", studentCollection);
        return studentCollection;
//        return studentRepository.findAll().stream()
//                .sorted(Comparator.comparingInt(Student::getAge).reversed())
//                .limit(5)
//                .collect(Collectors.toList());
    }

    public Collection<String> findStudentsFirstCharA() {
        Collection<String> studentCollection = studentRepository.findAll().stream()
                .filter(e -> e.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());

        logger.info("Was invoked method for find name first char equals A {}", studentCollection);
        return studentCollection;
    }

    public int getAvgAgeStudentsStream() {
        int avgAge = studentRepository.findAll().stream().collect(Collectors.averagingInt(Student::getAge)).intValue();
        logger.info("Was invoked method for get average age students {}", avgAge);
        return avgAge;
    }

    public void getStudentsOnThreads() {
        int count = 0;
        int maxCount = 6;

        while (count < maxCount) {
            printStudent(count++);
            printStudent(count++);

            int finalI = count++;
            int finalCount = count++;

            new Thread(() -> {
                printStudent(finalI);
                printStudent(finalCount);
            }).start();

            int finalI1 = count++;
            int finalCount1 = count++;

            new Thread(() -> {
                printStudent(finalI1);
                printStudent(finalCount1);
            }).start();
        }
    }

    public void getStudentsOnThreadsSynchro() {
        int count = 0;
        int maxCount = 6;

        while (count < maxCount) {
            printStudentSynchro(count++);
            printStudentSynchro(count++);

            int finalI = count++;
            int finalCount = count++;

            new Thread(() -> {
                printStudentSynchro(finalI);
                printStudentSynchro(finalCount);
            }).start();

            int finalI1 = count++;
            int finalCount1 = count++;

            new Thread(() -> {
                printStudentSynchro(finalI1);
                printStudentSynchro(finalCount1);
            }).start();
        }
    }

    private void printStudent(int count) {
        System.out.println(studentRepository.findAll().get(count).toString());
    }

    private void printStudentSynchro(int count) {
        synchronized (StudentService.class) {
            System.out.println(studentRepository.findAll().get(count).toString());
        }
    }
}
