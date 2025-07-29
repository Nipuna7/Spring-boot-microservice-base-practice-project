package com.nipuna.product_service.controller;

import com.nipuna.product_service.dto.ProductDTO;
import com.nipuna.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    // get all products
    @GetMapping("/getproducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    // get product by id
    @GetMapping("/products/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    // add product
    @PostMapping("/addproduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    // update product
    @PutMapping("/updateproduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    // delete product
    @DeleteMapping("/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }
}
