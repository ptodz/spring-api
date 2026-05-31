package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;
    private ProductMapper  productMapper;
    private CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        Product product = productRepository.findById(Long.parseLong(id)).orElse(null);
        if  (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@RequestParam(defaultValue = "",
            required = false,
            name = "categoryId") String categoryId) {
        List<Product> products = productRepository.getProductsByCategoryId(Byte.parseByte(categoryId));
        if  (products.isEmpty()) {
            return ResponseEntity.ok().body(productMapper.toList(productRepository.findAll()));
        }
        return ResponseEntity.ok().body(productMapper.toList(products));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto request,
                                                    UriComponentsBuilder uriBuilder) {
        var product = productMapper.toEntity(request);
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElse(null));
        productRepository.save(product);
        request.setId(product.getId());
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") Long id,
                                              @RequestBody ProductDto request) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productMapper.update(request, product);
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElse(null));
        productRepository.save(product);
        request.setId(product.getId());
        return ResponseEntity.ok(request);
    }


}
