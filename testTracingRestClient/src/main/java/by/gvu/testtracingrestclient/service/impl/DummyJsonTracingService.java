package by.gvu.testtracingrestclient.service.impl;

import by.gvu.testtracingrestclient.model.CategoryDetails;
import by.gvu.testtracingrestclient.model.Product;
import by.gvu.testtracingrestclient.service.TracingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyJsonTracingService implements TracingService {
    private final RestClient restClient;

    @Override
    public List<CategoryDetails> getCategoryDetailsList() {
        ParameterizedTypeReference<List<String>> refCategoryList = new ParameterizedTypeReference<List<String>>() {};

        List<String> categories = restClient.post()
                .uri(uriBuilder -> uriBuilder.path("/success/categories").build())
                .retrieve()
                .body(refCategoryList);

        if (categories != null && !categories.isEmpty()) {
            return categories.parallelStream().map(category -> {
                ParameterizedTypeReference<List<Product>> refProductList = new ParameterizedTypeReference<List<Product>>() {
                };
                List<Product> productList = restClient.post()
                        .uri(uriBuilder -> uriBuilder.path("/success/products/category/{categoryId}").build(category))
                        .retrieve()
                        .body(refProductList);

                return CategoryDetails.builder()
                        .categoryName(category)
//                        .productList(productList != null ? productList : Collections.emptyList())
                        .productCount(productList != null ? productList.size() : 0)
                        .build();
            }).toList();
        }
        return Collections.emptyList();
    }
}
