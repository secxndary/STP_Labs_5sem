package com.secxndary.stp_labs2_8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Concerts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "capacity")
    private int capacity;
}
