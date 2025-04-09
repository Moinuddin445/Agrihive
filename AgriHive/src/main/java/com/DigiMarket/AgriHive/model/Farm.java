package com.DigiMarket.AgriHive.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "farm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;

    @Column(nullable = false)
    private String farmName;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "farmerId")
    private Farmer farmer;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Product> products;

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

