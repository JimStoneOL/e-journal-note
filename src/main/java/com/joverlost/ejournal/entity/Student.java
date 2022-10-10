package com.joverlost.ejournal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String firstname;
    @Column(nullable = false,length = 50)
    private String lastname;
    @Column(nullable = false,unique = true,length = 80)
    private String contact;
    @OneToMany(mappedBy = "student")
    private List<Estimation> estimations;
}
