package by.gvu.testtracingrestservice.service;

import by.gvu.testtracingrestservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(int productId);

    List<Product> findByCategory(String categoryName);
}
