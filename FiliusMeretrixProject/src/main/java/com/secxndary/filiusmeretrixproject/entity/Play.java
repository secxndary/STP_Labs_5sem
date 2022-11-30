package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "Play")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Play {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    @Column(name = "play_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private String date;

}
