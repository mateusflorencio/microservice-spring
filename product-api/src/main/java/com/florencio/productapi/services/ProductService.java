package com.florencio.productapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.florencio.productapi.dto.ProductDTO;
import com.florencio.productapi.model.Product;
import com.florencio.productapi.repository.ProductyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductyRepository productyRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productyRepository.findAll();

        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productyRepository.getProductByCategory(categoryId);

        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productyRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return ProductDTO.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productyRepository.save((Product.convert(productDTO)));
        return ProductDTO.convert(product);
    }

    public void delete(Long productId) {
        Optional<Product> product = productyRepository.findById(productId);
        if (product.isPresent()) {
            productyRepository.delete(product.get());
        }
    }
}
