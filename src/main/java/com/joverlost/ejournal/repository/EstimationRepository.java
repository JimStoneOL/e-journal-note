package com.joverlost.ejournal.repository;

import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimationRepository extends JpaRepository<Estimation, Long>{
    public List<Estimation> findAllByStudentAndSubjectOrderByDateDesc(Student student, Subject subject);

    public List<Estimation> findAllBySubject(Subject subject);
    public List<Estimation> findAllByStudent(Student student);
}
