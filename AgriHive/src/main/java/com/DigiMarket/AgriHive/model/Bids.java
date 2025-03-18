package com.DigiMarket.AgriHive.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bids {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    private double bidAmount;

    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus = BidStatus.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime bidDate = LocalDateTime.now();
}

enum BidStatus {
    PENDING, ACCEPTED, REJECTED;
}
