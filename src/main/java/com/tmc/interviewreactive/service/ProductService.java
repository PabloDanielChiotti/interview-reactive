package com.tmc.interviewreactive.service;

import com.tmc.interviewreactive.dto.ProductDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Mono<List<ProductDTO>> getProducts(Map<String, String> criteria);
}
