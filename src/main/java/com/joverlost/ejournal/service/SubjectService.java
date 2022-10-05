package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.SubjectDTO;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.exception.SubjectNotFoundException;
import com.joverlost.ejournal.facade.SubjectFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectFacade subjectFacade;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SubjectFacade subjectFacade) {
        this.subjectRepository = subjectRepository;
        this.subjectFacade = subjectFacade;
    }

    public Subject createSubject(SubjectDTO subjectDTO){
        Subject subject=subjectFacade.subjectDtoToSubject(subjectDTO);
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
    }

    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    public MessageResponse deleteSubject(Long id){
        subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        subjectRepository.deleteById(id);
        return new MessageResponse("Предмет успешно удалён");
    }

    public Subject updateSubject(SubjectDTO subjectDTO){
        subjectRepository.findById(subjectDTO.getId()).orElseThrow(()->new SubjectNotFoundException("Предмет не найден"));
        return subjectRepository.save(subjectFacade.subjectDtoToSubject(subjectDTO));
    }

    public List<String> getAllNamesOfSubjects(){
        List<String> namesOfSubjects=new ArrayList<>();
        List<Subject> subjectList=subjectRepository.findAll();
        for(int i=0;i<subjectList.size();i++){
            namesOfSubjects.add(subjectList.get(i).getName());
        }
        return  namesOfSubjects;
    }
}
