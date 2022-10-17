package com.saraya.TransportManagement.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    private String fromm;
    private String too;

}
