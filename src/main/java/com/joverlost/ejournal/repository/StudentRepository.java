package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
