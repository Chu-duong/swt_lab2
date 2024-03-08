package com.example.happyprogramingbackend.Entity;

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
@Table(name = "rate")
public class Rate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private Float rateValue;

    @ManyToOne
    @JoinColumn()
    private User ratedBy;

    @ManyToOne
    @JoinColumn()
    private User rated;
}
