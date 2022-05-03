package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.Products;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductsListResponseDto {
    private Long id;
    private Long barcodeId;
    private String productName;
    private String brand;
    private String price;
    private String weight;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ProductsListResponseDto(Products entity){
        this.id = entity.getId();
        this.barcodeId = entity.getBarcodeId();
        this.productName = entity.getProductName();
        this.brand = entity.getBrand();
        this.price = entity.getPrice();
        this.weight = entity.getWeight();
        this.volumeShort = entity.getVolumeShort();
        this.volumeLong = entity.getVolumeLong();
        this.volumeHeight = entity.getVolumeHeight();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
