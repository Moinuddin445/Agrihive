package com.DigiMarket.AgriHive.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    private String name;
    private String category;
    private double quantity; // in KG or tons
    private double pricePerKg;

    private LocalDate harvestDate;

    private boolean isAvailable = true;
}

