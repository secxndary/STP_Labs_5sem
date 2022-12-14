package com.secxndary.filiusmeretrixproject.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "is_active")
    private Boolean is_active;

    @ManyToOne
    @JoinColumn(name = "concert", nullable = false)
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
