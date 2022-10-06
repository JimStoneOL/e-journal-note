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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        List<Student> studentList=subject.getStudents();
        return studentList;
    }

    public MessageResponse addStudentToSubject(Long studentId,Long subjectId){
        Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        List<Subject> subjectList=student.getSubjects();
        subjectList.add(subject);
        student.setSubjects(subjectList);
        studentRepository.save(student);
        return new MessageResponse("Студент успешно дабавлен в предмет");
    }

    public MessageResponse deleteStudentFromSubject(Long studentId,Long subjectId){
        Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        List<Subject> subjectList=student.getSubjects();
        subjectList.remove(subject);
        student.setSubjects(subjectList);
        studentRepository.save(student);
        return new MessageResponse("Студент успешно удалён из предмета");
    }

    public List<Student> getAllStudentsFromSubject(Long subjectId){
        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        return subject.getStudents();
    }

    public List<Student> getAllStudentsNotFromSubject(Long subjectId){
        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        List<Student> studentListFromSubject=subject.getStudents();
        List<Student> studentList=studentRepository.findAll();
        log.info(studentListFromSubject.size()+" ----");
        if(studentListFromSubject.size()==0){
            return studentList;
        }
        List<Student> studentNotFromSubjectList=new ArrayList<>();
        for(int i=0;i<studentList.size();i++){
            Student student=studentList.get(i);
            log.info(student.getId()+"");
            for(int k=0;k<studentListFromSubject.size();k++){
                if(!(student.getId()==(studentListFromSubject.get(k).getId()))){
                    log.info("cool");
                    studentNotFromSubjectList.add(student);
                }
            }
        }
        return studentNotFromSubjectList;
    }
}
