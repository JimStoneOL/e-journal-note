package com.joverlost.ejournal.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleEstimation {
    private Long id;
    private Integer estimation;
    private LocalDateTime date;
}
