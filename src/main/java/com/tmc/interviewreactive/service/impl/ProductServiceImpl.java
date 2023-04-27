package com.tmc.interviewreactive.service.impl;

import com.tmc.interviewreactive.dto.ProductDTO;
import com.tmc.interviewreactive.enums.OrderCriteria;
import com.tmc.interviewreactive.model.Product;
import com.tmc.interviewreactive.repository.ProductRepository;
import com.tmc.interviewreactive.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Mono<List<ProductDTO>> getProducts(Map<String, String> criteria) {
        return productRepository.findAll().flatMap(it -> covertProductToProductDTO(it, criteria)).collectSortedList(Comparator.comparingInt(ProductDTO::getWeightedValue));
    }

    private Mono<ProductDTO> covertProductToProductDTO(Product product, Map<String, String> criteria) {
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
        productDTO.setWeightedValue(getWeightedValues(product, criteria));
        return Mono.just(productDTO);
    }

    private Integer getWeightedValues(Product product, Map<String, String> criteria) {
        Integer weightedValue = 0;
        for (Map.Entry<String, String> itemCriteria : criteria.entrySet()) {
            weightedValue = getWeightedValue(product, itemCriteria.getKey(), Integer.valueOf(itemCriteria.getValue()), weightedValue);
        }
        return weightedValue;
    }

    private Integer getWeightedValue(Product product, String criteria, Integer criteriaValue, Integer weightedValue) {
        if (OrderCriteria.UNIT_SALE_CRITERIA.name().equals(criteria)) {
            Integer weight = product.getSaleUnits() * criteriaValue;
            return weightedValue + weight;
        } else if (OrderCriteria.STOCK_RATIO_CRITERIA.name().equals(criteria)) {
            Integer weight = getAmountOfSizesWithMoreThanZeroProduct(product) * criteriaValue;
            return weightedValue + weight;
        }
        return weightedValue;
    }

    private Integer getAmountOfSizesWithMoreThanZeroProduct(Product product) {
        Integer weight = 0;
        weight = product.getStockSmall() > 0 ? weight + 1 : weight;
        weight = product.getStockMedium() > 0 ? weight + 1 : weight;
        weight = product.getStockLarge() > 0 ? weight + 1 : weight;
        return weight;
    }
}
