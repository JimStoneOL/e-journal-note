package com.joverlost.ejournal.dto;

import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.entity.Subject;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class EstimationDTO {
    private Long id;
    @NotNull
    @Min(value = 1L)
    @Max(value = 5l)
    private Integer number;
    private LocalDateTime date;
    private Long studentId;
    private Long subjectId;
}
