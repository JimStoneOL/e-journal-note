package com.joverlost.ejournal.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String contact;
    private List<Long> estimations;
}
