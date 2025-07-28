package com.nipuna.inventory_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Inventory {
    @Id
    private int id;
    private int itemId;
    private int productId;
    private int quantity;
}
