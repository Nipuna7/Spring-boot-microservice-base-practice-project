package com.nipuna.order_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Orders {
    @Id
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
