package com.example.happyprogramingbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_key")
    private String roleKey;

    @OneToMany(mappedBy = "role")
    @Transient
    private List<User> users = new ArrayList<>();

}
