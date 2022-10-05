package com.joverlost.ejournal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Estimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Range(min = 1, max = 5)
    @Column(nullable = false,length = 1)
    private Integer number;
    @JsonFormat(pattern = "dd-mm-yyyy ss:mm:HH")
    private LocalDateTime date;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Student student;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Subject subject;
}
