package com.secxndary.filiusmeretrixproject.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Tickets")
@Getter
@Setter
@RequiredArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "row_place")
    private Integer row;

    @Column(name = "place")
    private Integer place;

    @Column(name = "availability_ticket")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
