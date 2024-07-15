package by.gvu.testtracingrestservice.service.impl;

import by.gvu.testtracingrestservice.model.DummyResponceProduct;
import by.gvu.testtracingrestservice.model.Product;
import by.gvu.testtracingrestservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DummyJsonProductService implements ProductService {
    private final RestClient restClient;

    @Override
    public List<Product> findAll() {
        DummyResponceProduct dummyResponceProduct = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/products").queryParam("limit", 3).build())
                .retrieve()
                .body(DummyResponceProduct.class);

        assert dummyResponceProduct != null;
        return dummyResponceProduct.products();
    }

    @Override
    public Optional<Product> findById(int productId) {
        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/products/{productId}").build(productId))
                .retrieve()
                .body(Product.class));
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        DummyResponceProduct dummyResponceProduct = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/products/category/{categoryName}").queryParam("limit", 3).build(categoryName))
                .retrieve()
                .body(DummyResponceProduct.class);

        assert dummyResponceProduct != null;
        return dummyResponceProduct.products();
    }
}
