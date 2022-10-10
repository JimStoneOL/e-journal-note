package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.StudentDTO;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.facade.StudentFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.service.StudentService;
import com.joverlost.ejournal.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private StudentFacade studentFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Student student=studentService.createStudent(studentDTO);
        StudentDTO response=studentFacade.studentToStudentDTO(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Long id){
        Student student=studentService.getStudentById(id);
        StudentDTO studentDTO=studentFacade.studentToStudentDTO(student);
        return new ResponseEntity<>(studentDTO,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllStudent(){
        List<Student> studentList=studentService.getAllStudents();
        List<StudentDTO> studentDTOList=studentFacade.studentListToStudentDTOList(studentList);
        return new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable("id") Long id){
        MessageResponse response=studentService.deleteStudentById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Student student=studentService.updateStudent(studentDTO);
        StudentDTO response=studentFacade.studentToStudentDTO(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
