package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.StudentDTO;
import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.repository.EstimationRepository;
import com.joverlost.ejournal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentFacade {

    @Autowired
    private EstimationRepository estimationRepository;

    public Student studentDtoToStudent(StudentDTO studentDTO){
        Student student=new Student();
        student.setId(studentDTO.getId());
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setContact(studentDTO.getContact());

        if(studentDTO.getEstimations()!=null) {
            List<Estimation> estimationList = new ArrayList<>();
            for (int i = 0; i < studentDTO.getEstimations().size(); i++) {
                Long estimationId = studentDTO.getEstimations().get(i);
                Estimation estimation = estimationRepository.findById(estimationId).orElse(null);
                if (estimation != null) {
                    estimationList.add(estimation);
                }
            }
            student.setEstimations(estimationList);
        }
        return student;
    }

    public StudentDTO studentToStudentDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setContact(student.getContact());

        if(student.getEstimations()!=null) {
            List<Long> estimationList = new ArrayList<>();
            for (int i = 0; i < student.getEstimations().size(); i++) {
                estimationList.add(student.getEstimations().get(i).getId());
            }
            studentDTO.setEstimations(estimationList);
        }
        return studentDTO;
    }

    public List<StudentDTO> studentListToStudentDTOList(List<Student> studentList){
        List<StudentDTO> studentDTOList=new ArrayList<>();
        for(int i=0;i<studentList.size();i++){
            Student student=studentList.get(i);
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setFirstname(student.getFirstname());
            studentDTO.setLastname(student.getLastname());
            studentDTO.setContact(student.getContact());

            if(student.getEstimations()!=null) {
                List<Long> estimationList = new ArrayList<>();
                for (int k = 0; k < student.getEstimations().size(); k++) {
                    estimationList.add(student.getEstimations().get(k).getId());
                }
                studentDTO.setEstimations(estimationList);
            }
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

}
