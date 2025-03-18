package com.DigiMarket.AgriHive.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farms")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Buyer buyer;

    private String farmName;
    private String location;
}

