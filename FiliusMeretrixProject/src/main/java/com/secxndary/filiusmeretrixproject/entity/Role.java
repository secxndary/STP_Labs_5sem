package com.secxndary.filiusmeretrixproject.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "Roles")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
