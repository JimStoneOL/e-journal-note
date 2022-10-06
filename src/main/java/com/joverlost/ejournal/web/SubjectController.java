package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.StudentDTO;
import com.joverlost.ejournal.dto.SubjectDTO;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import com.joverlost.ejournal.facade.SubjectFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.service.SubjectService;
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
@RequestMapping("api/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private SubjectFacade subjectFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createSubject(@Valid @RequestBody SubjectDTO subjectDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Subject subject =subjectService.createSubject(subjectDTO);
        SubjectDTO response=subjectFacade.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getSubjectById(@PathVariable("id") Long id){
        Subject subject=subjectService.getSubjectById(id);
        SubjectDTO subjectDTO=subjectFacade.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllSubject(){
        List<Subject> subjectList=subjectService.getAllSubject();
        List<SubjectDTO> subjectDTOList=subjectFacade.subjectListToSubjectDTOList(subjectList);
        return new ResponseEntity<>(subjectDTOList,HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSubjectById(@PathVariable("id") Long id){
        MessageResponse response=subjectService.deleteSubject(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateSubject(@Valid @RequestBody SubjectDTO subjectDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Subject subject =subjectService.updateSubject(subjectDTO);
        SubjectDTO response=subjectFacade.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/get/all/name")
    public ResponseEntity<Object> getAllNamesOfSubjects(){
        List<String> nameSubjectList=subjectService.getAllNamesOfSubjects();
        return new ResponseEntity<>(nameSubjectList,HttpStatus.OK);
    }

    @GetMapping("/get/by/{name}")
    public ResponseEntity<Object> getSubjectByName(@PathVariable("name") String name){
        Subject subject=subjectService.getSubjectByName(name);
        SubjectDTO subjectDTO=subjectFacade.subjectToSubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO,HttpStatus.OK);
    }
}
