package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.EstimationDTO;
import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.exception.EstimationNotFoundException;
import com.joverlost.ejournal.exception.StudentNotFoundException;
import com.joverlost.ejournal.facade.EstimationFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.payload.response.SimpleEstimation;
import com.joverlost.ejournal.payload.response.StudentEstimation;
import com.joverlost.ejournal.payload.response.SubjectEstimation;
import com.joverlost.ejournal.repository.EstimationRepository;
import com.joverlost.ejournal.repository.StudentRepository;
import com.joverlost.ejournal.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EstimationService {

    private final EstimationRepository estimationRepository;
    private final EstimationFacade estimationFacade;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public EstimationService(EstimationRepository estimationRepository, EstimationFacade estimationFacade, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.estimationRepository = estimationRepository;
        this.estimationFacade = estimationFacade;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Estimation createEstimation(EstimationDTO estimationDTO){
        Estimation estimation=estimationFacade.estimationDtoToEstimation(estimationDTO);
        return estimationRepository.save(estimation);
    }

    public Estimation getEstimationById(Long id){
        return estimationRepository.findById(id).orElseThrow(()->new EstimationNotFoundException("Оценка не найдена"));
    }

    public List<Estimation> getAllEstimation(){
        return estimationRepository.findAll();
    }

    public MessageResponse deleteEstimation(Long id){
        estimationRepository.findById(id).orElseThrow(()->new EstimationNotFoundException("Оценка не найдена"));
        estimationRepository.deleteById(id);
        return new MessageResponse("Оценка удалена");
    }

    public Estimation updateEstimation(EstimationDTO estimationDTO){
        estimationRepository.findById(estimationDTO.getId()).orElseThrow(()->new EstimationNotFoundException("Оценка не найдена"));
        return estimationRepository.save(estimationFacade.estimationDtoToEstimation(estimationDTO));
    }

    public StudentEstimation getEstimationsByStudent(Long studentId){

        Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        StudentEstimation studentEstimation=new StudentEstimation();
        studentEstimation.setName(student.getFirstname()+" "+student.getLastname());
        List<Subject> subjectList=subjectRepository.findAll();
        log.info(subjectList.size()+" - subjectList");
        if(subjectList.size()>0){
            List<SubjectEstimation> subjectEstimationList=new ArrayList<>();
            for(int l=0;l<subjectList.size();l++){
                SubjectEstimation subjectEstimation=new SubjectEstimation();
                Subject subject=subjectList.get(l);
                subjectEstimation.setSubject(subject.getName());
                List<Estimation> estimationList=estimationRepository.findAllByStudentAndSubjectOrderByDateDesc(student,subject);
                if(estimationList.size()>0) {
                    List<SimpleEstimation> simpleEstimationList = new ArrayList<>();
                    for (int i = 0; i < estimationList.size(); i++) {
                        Estimation estimation = estimationList.get(i);
                        SimpleEstimation simpleEstimation = new SimpleEstimation();
                        simpleEstimation.setId(estimation.getId());
                        simpleEstimation.setEstimation(estimation.getNumber());
                        simpleEstimation.setDate(estimation.getDate());
                        simpleEstimationList.add(simpleEstimation);
                    }
                    subjectEstimation.setEstimations(simpleEstimationList);
                }else{
                    subjectEstimation.setEstimations(new ArrayList<>());
                }
                subjectEstimationList.add(subjectEstimation);
            }
            studentEstimation.setSubjectEstimations(subjectEstimationList);
        }
        return studentEstimation;
    }
//        Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
//        StudentEstimation studentEstimation=new StudentEstimation();
//        studentEstimation.setName(student.getFirstname()+" "+student.getLastname());
//        List<Estimation> estimations=student.getEstimations();
//        SubjectEstimation subjectEstimation=new SubjectEstimation();
//        String subjectName="";
//        for(int i=0;i<estimations.size();i++){
//            Estimation estimation=estimations.get(i);
//            if(estimation.getSubject().getName()==subjectName){
//
//            } else if (subjectName=="") {
//                subjectName=estimation.getSubject().getName();
//                subjectEstimation.setSubject(subjectName);
//                SimpleEstimation simpleEstimation=new SimpleEstimation();
//                simpleEstimation.setEstimation(estimation.getNumber());
//                simpleEstimation.setDate(estimation.getDate());
//                List<SimpleEstimation> simpleEstimationList=subjectEstimation.getEstimations();
//                simpleEstimationList.add(simpleEstimation);
//                subjectEstimation.setEstimations(simpleEstimationList);
//            }else if(estimation.getSubject().getName()!=subjectName){
//
//            }
//        }
//    }
}
