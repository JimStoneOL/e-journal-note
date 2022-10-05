package com.joverlost.ejournal.dto;

import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstimationDTO {
    private Long id;
    private Integer number;
    private LocalDateTime date;
    private Long studentId;
    private Long subjectId;
}
