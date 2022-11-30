package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Waiting_List")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Concert concert;

    @Column(name = "queue_number")
    private int queue_number;
}
