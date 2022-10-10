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
        return subject;
    }

    public SubjectDTO subjectToSubjectDTO(Subject subject){
        SubjectDTO subjectDTO=new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setTeacher(subject.getTeacher());
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
            subjectDTOList.add(subjectDTO);
        }
        return subjectDTOList;
    }
}
