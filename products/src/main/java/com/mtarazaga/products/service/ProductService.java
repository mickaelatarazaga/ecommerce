package com.mtarazaga.products.service;

import com.mtarazaga.products.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
}
