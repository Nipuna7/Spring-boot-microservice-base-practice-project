package com.nipuna.inventory_service.repo;

import com.nipuna.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Integer> {
    @Query(value = "SELECT * FROM inventory WHERE item_id = ?1", nativeQuery = true)
    Inventory getItemById(Integer itemId);
}
