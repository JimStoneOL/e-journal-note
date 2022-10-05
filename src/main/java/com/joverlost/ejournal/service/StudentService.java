package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.StudentDTO;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.exception.StudentNotFoundException;
import com.joverlost.ejournal.exception.SubjectNotFoundException;
import com.joverlost.ejournal.facade.StudentFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.StudentRepository;
import com.joverlost.ejournal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentFacade studentFacade, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.studentFacade = studentFacade;
        this.subjectRepository = subjectRepository;
    }

    public Student createStudent(StudentDTO studentDTO){
        Student student=studentFacade.studentDtoToStudent(studentDTO);
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        return student;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public MessageResponse deleteStudentById(Long id){
        studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        studentRepository.deleteById(id);
        return new MessageResponse("Студент успешно удалён");
    }

    public Student updateStudent(StudentDTO studentDTO){
        studentRepository.findById(studentDTO.getId()).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        return studentRepository.save(studentFacade.studentDtoToStudent(studentDTO));
    }

    public List<Student> getAllStudentsBySubject(String name){
        Subject subject=subjectRepository.findByName(name).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        List<Student> studentList=studentRepository.findAllBySubjects(subject).orElseThrow(()->new StudentNotFoundException("Студенты не найдены"));
        return studentList;
    }
}
