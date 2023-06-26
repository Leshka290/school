package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int ageMin, int ageMax);

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Integer getAvgAgeStudents();

    @Query(value = "select * from student order by id DESC limit 5", nativeQuery = true)
    Collection<Student> getLastStudents();
}
