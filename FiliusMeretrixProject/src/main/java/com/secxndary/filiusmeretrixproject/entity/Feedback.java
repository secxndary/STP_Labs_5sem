package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Comments")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment_text")
    private String text;

    // TODO: binary image
    @Column(name = "image")
    private String image;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Concert concert;
}
