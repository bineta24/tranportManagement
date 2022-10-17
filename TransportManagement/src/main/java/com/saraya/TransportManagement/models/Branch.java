package com.saraya.TransportManagement.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "branch")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class Branch  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User manager;




}
