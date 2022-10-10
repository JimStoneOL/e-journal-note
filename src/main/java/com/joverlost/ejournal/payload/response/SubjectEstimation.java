package com.joverlost.ejournal.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class SubjectEstimation {
    private String subject;
    private List<SimpleEstimation> estimations;
}
