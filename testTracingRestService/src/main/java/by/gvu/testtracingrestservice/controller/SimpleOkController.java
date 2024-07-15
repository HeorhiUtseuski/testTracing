package by.gvu.testtracingrestservice.controller;

import by.gvu.testtracingrestservice.model.Product;
import by.gvu.testtracingrestservice.service.CategoryService;
import by.gvu.testtracingrestservice.service.ProductService;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/success", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Slf4j
public class SimpleOkController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Tracer tracer;

    @PostMapping("/products")
    public List<Product> getAllProdutcs() {
        log.warn("{} Baggages are {}", "getAllProdutcs", tracer.getAllBaggage());
        return productService.findAll();
    }

    @PostMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        log.warn("{} Baggages are {}", "getProductById(productId)", tracer.getAllBaggage());
        return productService.findById(productId).get();
    }

    @PostMapping("/products/category/{categoryId}")
    public List<Product> getProductById(@PathVariable String categoryId) {
        log.warn("{} Baggages are {}", "getProductById(categoryId)", tracer.getAllBaggage());
        return productService.findByCategory(categoryId);
    }

    @PostMapping("/categories")
    public List<String> getProductById() {
        log.warn("{} Baggages are {}", "getProductById", tracer.getAllBaggage());
        return categoryService.findAll();
    }
}
