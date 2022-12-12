package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "Concerts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Concert {

    @Id
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

    @OneToMany(mappedBy = "concert")
    private List<Order> tickets;
}
