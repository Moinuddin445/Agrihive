package com.DigiMarket.AgriHive.controller;

import com.DigiMarket.AgriHive.DTO.OrderRequestDTO;
import com.DigiMarket.AgriHive.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO request) {
        String response = orderService.placeOrder(request);

        // Check the response and send appropriate JSON response
        if (response.startsWith("✅")) {
            return ResponseEntity.ok(new OrderResponse("success", response));
        } else {
            return ResponseEntity.badRequest().body(new OrderResponse("error", response));
        }
    }

    // Create a class for sending structured responses
    public static class OrderResponse {
        private String status;
        private String message;

        public OrderResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
