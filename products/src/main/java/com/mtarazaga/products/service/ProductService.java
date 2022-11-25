package com.mtarazaga.products.service;

import com.mtarazaga.products.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto editById(ProductDto productDto, Long id);
}
