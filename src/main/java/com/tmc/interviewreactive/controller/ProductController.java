package com.tmc.interviewreactive.controller;

import com.tmc.interviewreactive.dto.ProductDTO;
import com.tmc.interviewreactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<Mono<List<ProductDTO>>> getProducts(@RequestParam Map<String, String> criteria) {
        return new ResponseEntity<>(productService.getProducts(criteria), HttpStatus.OK);
    }
}
