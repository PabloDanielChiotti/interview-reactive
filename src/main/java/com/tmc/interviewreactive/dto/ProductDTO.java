package com.tmc.interviewreactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Integer saleUnits;
    private Integer stockSmall;
    private Integer stockMedium;
    private Integer stockLarge;
    private Integer weightedValue;
}
