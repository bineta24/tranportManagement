package com.saraya.TransportManagement.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;


}
