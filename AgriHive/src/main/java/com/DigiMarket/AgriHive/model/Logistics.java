package com.DigiMarket.AgriHive.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "logistics")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Logistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logisticsId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(unique = true)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

    private LocalDate estimatedDeliveryDate;
}

enum DeliveryStatus {
    PENDING, DISPATCHED, IN_TRANSIT, DELIVERED;
}

