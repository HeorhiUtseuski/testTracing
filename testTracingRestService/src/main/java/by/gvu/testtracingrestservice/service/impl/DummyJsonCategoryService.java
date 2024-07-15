package by.gvu.testtracingrestservice.service.impl;

import by.gvu.testtracingrestservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyJsonCategoryService implements CategoryService {
    private final RestClient restClient;

    @Override
    public List<String> findAll() {
        ParameterizedTypeReference<List<String>> typeRef = new ParameterizedTypeReference<List<String>>() {};

        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/products/category-list").queryParam("limit", 3).build())
                .retrieve()
                .body(typeRef);
    }
}
