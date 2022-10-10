package com.joverlost.ejournal.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class StudentEstimation {
    private String name;
    private List<SubjectEstimation> subjectEstimations;
}
