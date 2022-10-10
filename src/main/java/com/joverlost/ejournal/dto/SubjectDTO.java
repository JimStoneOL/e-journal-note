package com.joverlost.ejournal.dto;

import com.joverlost.ejournal.entity.Student;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SubjectDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String teacher;
}
