package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.ProductsRepository;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLogRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductStockServiceImpl implements ProductStockService {
    private final StockLogRepository stockLogRepository;
    private final ProductsRepository productsRepository;

    private final EntityManager em;

//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User userDetails = (UserDetails)principal;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Override
    public Long save(ProductStockUpdateDto dto) {
        StockLog stockLog = new StockLog();
        stockLog.setInStock(dto.getInStock());
        stockLog.setStockAdd(dto.getStockAdd());
        stockLog.setStockSub(dto.getStockSub());
        stockLog.setName(dto.getName());
        stockLog.setBarcodeId(dto.getBarcodeId());
        return stockLogRepository.save(stockLog).getStockId();
    }

    @Override
    public ProductStockResponseDto latestLog(long barcode) {
        Optional<StockLog> optionalStockLog = stockLogRepository.findFirstByBarcodeIdOrderByCreatedDateDesc(barcode);
        if(!optionalStockLog.isPresent()){
            ProductStockResponseDto dto =ProductStockResponseDto.builder().inStock(0).barcodeId(barcode).build();
            return dto;
        }
        StockLog stockLog = optionalStockLog.get();
        ProductStockResponseDto dto =
                ProductStockResponseDto.builder().inStock(stockLog.getInStock()).barcodeId(stockLog.getBarcodeId()).build();
        return dto;
    }

    @Override
    public List<ProductStockResponseDto> stockList() {
        // int zero = 0;
        Optional<List<StockLog>> optionalStockLog =
                stockLogRepository.find();
        if(!optionalStockLog.isPresent()){
            System.out.println("?????? ?????? ????????? ??????.");
            return Collections.emptyList();
        }
        System.out.println("????????? ????????????.");
        List<StockLog> stockLog = optionalStockLog.get();
        List<ProductStockResponseDto> responseDtos = new ArrayList<>();
        List<Long> _barcodeList = new ArrayList<>();
        List<LocalDateTime> _createdDate = new ArrayList<>();
        for(StockLog stock : stockLog){
            Optional<Products> optionalProducts = productsRepository.findByBarcodeId(stock.getBarcodeId());
            if(!optionalProducts.isPresent()){
                System.out.println("??? ????????? ????????? ?????????."+stock.getBarcodeId());
            } else {
                // ?????? ??????
                long barcodeId = stock.getBarcodeId();
                LocalDateTime createdDate = stock.getCreatedDate();
                // ?????? ???????????? ?????????
                if(_barcodeList.contains(barcodeId)){
                    int j = _barcodeList.indexOf(barcodeId);
                    // ?????? ????????? ???????????? ???????????????
                    if(_createdDate.get(j).isBefore(createdDate)){
                        // ?????? ???????????? ????????? ??????
                        _createdDate.set(j, createdDate);
                        // ??????
                        Products products = optionalProducts.get();
                        System.out.println("?????? ?????? ???????????? ????????????.");
                        String productName = products.getProductName();
                        String brand = products.getBrand();
                        ProductStockResponseDto dto = new ProductStockResponseDto(stock, productName, brand);
                        responseDtos.set(j, dto);
                    } else{
                        // ????????? ?????? ??? ??????
                    }
                } else {
                    // ????????? ???????????? ????????? ?????? ??????
                    _barcodeList.add(barcodeId);
                    _createdDate.add(createdDate);
                    // ??????
                    Products products = optionalProducts.get();
                    System.out.println("?????? ?????? ???????????? ????????????.");
                    String productName = products.getProductName();
                    String brand = products.getBrand();
                    ProductStockResponseDto dto = new ProductStockResponseDto(stock, productName, brand);
                    responseDtos.add(dto);
                }
            }
        }
        System.out.println("?????? ?????? ?????? ???????????? ????????????.");
        System.out.println(responseDtos);
        return responseDtos;
    }
}
