package com.joverlost.ejournal.dto;


import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String contact;
    private List<Long> subjects;
    private List<Long> estimations;
}
