package com.tmc.interviewreactive.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Product {

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer saleUnits;

    @NotNull
    private Integer stockSmall;

    @NotNull
    private Integer stockMedium;

    @NotNull
    private Integer stockLarge;

}
