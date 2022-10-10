package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.EstimationDTO;
import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.facade.EstimationFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.payload.response.StudentEstimation;
import com.joverlost.ejournal.service.EstimationService;
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
@RequestMapping("api/estimation")
@CrossOrigin
public class EstimationController {

    @Autowired
    private EstimationService estimationService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private EstimationFacade estimationFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createEstimation(@Valid @RequestBody EstimationDTO estimationDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Estimation estimation=estimationService.createEstimation(estimationDTO);
        EstimationDTO response=estimationFacade.estimationToEstimationDTO(estimation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEstimationById(@PathVariable("id") Long id){
        Estimation estimation=estimationService.getEstimationById(id);
        EstimationDTO estimationDTO=estimationFacade.estimationToEstimationDTO(estimation);
        return new ResponseEntity<>(estimationDTO,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllEstimation(){
        List<Estimation> estimationList=estimationService.getAllEstimation();
        List<EstimationDTO> estimationDTOList=estimationFacade.estimationListToEstimationDTOList(estimationList);
        return new ResponseEntity<>(estimationDTOList,HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEstimationById(@PathVariable("id") Long id){
        MessageResponse response=estimationService.deleteEstimation(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateEstimation(@Valid @RequestBody EstimationDTO estimationDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Estimation estimation=estimationService.updateEstimation(estimationDTO);
        EstimationDTO response=estimationFacade.estimationToEstimationDTO(estimation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/by/{id}")
    public ResponseEntity<Object> getEstimationsByStudent(@PathVariable("id") Long id){
        StudentEstimation studentEstimation=estimationService.getEstimationsByStudent(id);
        return new ResponseEntity<>(studentEstimation,HttpStatus.OK);
    }
}
//todo getEstimationByStudent
//todo for(Subject)
//todo getEstimationByStudentAndSubject - in repository
