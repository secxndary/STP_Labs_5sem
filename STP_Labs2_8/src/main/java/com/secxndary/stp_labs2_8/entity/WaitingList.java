package com.secxndary.stp_labs2_8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Waiting_List")
@Data
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
