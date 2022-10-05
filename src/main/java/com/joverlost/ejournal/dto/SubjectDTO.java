package com.joverlost.ejournal.dto;

import com.joverlost.ejournal.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class SubjectDTO {
    private Long id;
    private String name;
    private String teacher;
    private List<Long> students;
}
