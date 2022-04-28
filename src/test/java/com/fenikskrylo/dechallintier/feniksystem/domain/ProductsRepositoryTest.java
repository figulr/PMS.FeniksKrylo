package com.fenikskrylo.dechallintier.feniksystem.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsRepositoryTest {
    @Autowired
    ProductsRepository productsRepository;

    @After
    public void cleanup() {
        productsRepository.deleteAll();
    }

    @Test
    public void product_불러오기() {
        //given
        long barcodeId = 111;
        String productName = "제품이름";
        String price = "111";
        String brand = "브랜드이름";
        String weight = "111";
        String volumeShort = "111";
        String volumeLong = "111";
        String volumeHeight = "111";

        productsRepository.save(Products.builder()
                .barcodeId(barcodeId)
                .productName(productName)
                .price(price)
                .brand(brand)
                .weight(weight)
                .volumeShort(volumeShort)
                .volumeLong(volumeLong)
                .volumeHeight(volumeHeight)
                .build());

        // when
        List<Products> productsList = productsRepository.findAll();

        // then
        Products products = productsList.get(0);
        assertThat(products.getBarcodeId()).isEqualTo(barcodeId);
        assertThat(products.getProductName()).isEqualTo(productName);
        assertThat(products.getPrice()).isEqualTo(price);
        assertThat(products.getBrand()).isEqualTo(brand);
        assertThat(products.getWeight()).isEqualTo(weight);
        assertThat(products.getVolumeShort()).isEqualTo(volumeShort);
        assertThat(products.getVolumeLong()).isEqualTo(volumeLong);
        assertThat(products.getVolumeHeight()).isEqualTo(volumeHeight);
    }
}