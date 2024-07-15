package by.gvu.testtracingrestservice.model;

import java.util.List;

public record DummyResponceProduct(
        List<Product> products,
        Integer total,
        Integer skip,
        Integer limit
) { }