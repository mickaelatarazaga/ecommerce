package com.mtarazaga.products.service.imp;

import com.mtarazaga.products.exception.NotFoundException;
import com.mtarazaga.products.model.dto.ProductDto;
import com.mtarazaga.products.model.entity.Product;
import com.mtarazaga.products.repository.ProductRepository;
import com.mtarazaga.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RefreshScope
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> findAll() {
        var productsFound = productRepository.findAll();
        List<ProductDto> productsDto = new ArrayList<>();
        productsFound.forEach(product -> {
            productsDto.add(Optional.of(product).isPresent() ? modelMapper.map(product, ProductDto.class) : null);
        });
        return productsDto;
    }

    @Override
    public ProductDto findById(Long id) {
        var productFound = findProductById(id);
        if (productFound.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }
        return modelMapper.map(productFound.get(), ProductDto.class);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        var savedProduct = productRepository.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(savedProduct, ProductDto.class);
    }
}
