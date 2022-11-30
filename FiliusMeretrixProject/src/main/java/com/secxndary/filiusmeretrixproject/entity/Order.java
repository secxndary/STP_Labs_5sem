package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "active_ticket")
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "concert_id", nullable = false)
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
