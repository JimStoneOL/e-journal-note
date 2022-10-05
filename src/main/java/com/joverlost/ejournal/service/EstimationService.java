package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.EstimationDTO;
import com.joverlost.ejournal.entity.Estimation;
import com.joverlost.ejournal.exception.EstimationNotFoundException;
import com.joverlost.ejournal.facade.EstimationFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EstimationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimationService {

    private final EstimationRepository estimationRepository;
    private final EstimationFacade estimationFacade;

    @Autowired
    public EstimationService(EstimationRepository estimationRepository, EstimationFacade estimationFacade) {
        this.estimationRepository = estimationRepository;
        this.estimationFacade = estimationFacade;
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

}
