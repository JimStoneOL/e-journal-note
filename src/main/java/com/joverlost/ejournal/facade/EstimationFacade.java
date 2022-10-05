package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.EstimationDTO;
import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.repository.EstimationRepository;
import com.joverlost.ejournal.repository.StudentRepository;
import com.joverlost.ejournal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstimationFacade {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public Estimation estimationDtoToEstimation(EstimationDTO estimationDTO){
        Estimation estimation=new Estimation();
        estimation.setId(estimationDTO.getId());
        estimation.setDate(estimationDTO.getDate());
        estimation.setNumber(estimationDTO.getNumber());
        estimation.setSubject(subjectRepository.findById(estimationDTO.getSubjectId()).get());
        estimation.setStudent(studentRepository.findById(estimationDTO.getStudentId()).get());
        return estimation;
    }

    public EstimationDTO estimationToEstimationDTO(Estimation estimation){
        EstimationDTO estimationDTO=new EstimationDTO();
        estimationDTO.setId(estimation.getId());
        estimationDTO.setDate(estimation.getDate());
        estimationDTO.setNumber(estimation.getNumber());
        estimationDTO.setStudentId(estimation.getStudent().getId());
        estimationDTO.setSubjectId(estimation.getSubject().getId());
        return estimationDTO;
    }

    public List<EstimationDTO> estimationListToEstimationDTOList(List<Estimation> estimationList){
        List<EstimationDTO> estimationDTOList=new ArrayList<>();
        for(int i=0;i<estimationList.size();i++){
            Estimation estimation=estimationList.get(i);
            EstimationDTO estimationDTO=new EstimationDTO();
            estimationDTO.setId(estimation.getId());
            estimationDTO.setDate(estimation.getDate());
            estimationDTO.setNumber(estimation.getNumber());
            estimationDTO.setStudentId(estimation.getStudent().getId());
            estimationDTO.setSubjectId(estimation.getSubject().getId());
            estimationDTOList.add(estimationDTO);
        }
        return estimationDTOList;
    }
}
