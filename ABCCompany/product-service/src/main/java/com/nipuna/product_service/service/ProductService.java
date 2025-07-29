package com.nipuna.product_service.service;

import com.nipuna.product_service.dto.ProductDTO;
import com.nipuna.product_service.model.Product;
import com.nipuna.product_service.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    // get all products
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    // add product
    public ProductDTO saveProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    // update product
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    // delete product
    public String deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
        return "Product deleted";
    }

    // get product by ID
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepo.getProductById(productId);
        return modelMapper.map(product, ProductDTO.class);
    }
}
