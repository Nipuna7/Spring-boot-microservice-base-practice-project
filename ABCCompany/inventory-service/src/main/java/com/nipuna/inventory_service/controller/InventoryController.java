package com.nipuna.inventory_service.controller;

import com.nipuna.inventory_service.dto.InventoryDTO;
import com.nipuna.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    //get all item
    @GetMapping("/getitems")
    public List<InventoryDTO> getItems(){
        return inventoryService.getAllItems();
    }

    //get item by id
    @GetMapping("/items/{itemId}")
    public InventoryDTO getItemById(@PathVariable Integer itemId){
        return inventoryService.getItemById(itemId);
    }

    //add item
    @PostMapping("/additem")
    public InventoryDTO saveItem(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.saveItem(inventoryDTO);
    }

    @PutMapping("/updateitem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        return inventoryService.deleteItem(itemId);
    }
}
