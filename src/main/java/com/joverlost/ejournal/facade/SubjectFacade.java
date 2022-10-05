package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.SubjectDTO;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectFacade {

    @Autowired
    private StudentRepository studentRepository;

    public Subject subjectDtoToSubject(SubjectDTO subjectDTO){
        Subject subject=new Subject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        subject.setTeacher(subjectDTO.getTeacher());
        if(subjectDTO.getStudents()!=null) {
            List<Student> studentList = new ArrayList<>();
            for (int i = 0; i < subjectDTO.getStudents().size(); i++) {
                Long studentId = subjectDTO.getStudents().get(i);
                Student student = studentRepository.findById(studentId).orElse(null);
                if (student != null) {
                    studentList.add(student);
                }
            }
            subject.setStudents(studentList);
        }
        return subject;
    }

    public SubjectDTO subjectToSubjectDTO(Subject subject){
        SubjectDTO subjectDTO=new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setTeacher(subject.getTeacher());
        if(subject.getStudents()!=null) {
            List<Long> studentList = new ArrayList<>();
            for (int i = 0; i < subject.getStudents().size(); i++) {
                studentList.add(subject.getStudents().get(i).getId());
            }
            subjectDTO.setStudents(studentList);
        }
        return subjectDTO;
    }

    public List<SubjectDTO> subjectListToSubjectDTOList(List<Subject> subjectList){
        List<SubjectDTO> subjectDTOList=new ArrayList<>();
        for(int i=0;i<subjectList.size();i++){
            Subject subject=subjectList.get(i);
            SubjectDTO subjectDTO=new SubjectDTO();
            subjectDTO.setId(subject.getId());
            subjectDTO.setName(subject.getName());
            subjectDTO.setTeacher(subject.getTeacher());
            if(subject.getStudents()!=null) {
                List<Long> studentList = new ArrayList<>();
                for (int k = 0; k < subject.getStudents().size(); k++) {
                    studentList.add(subject.getStudents().get(k).getId());
                }
                subjectDTO.setStudents(studentList);
            }
            subjectDTOList.add(subjectDTO);
        }
        return subjectDTOList;
    }
}
