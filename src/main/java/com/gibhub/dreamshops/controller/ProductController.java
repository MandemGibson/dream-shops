package com.gibhub.dreamshops.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gibhub.dreamshops.service.product.ProductService;
import com.gibhub.dreamshops.exceptions.ResourceNotFoundException;
import com.gibhub.dreamshops.models.Product;
import com.gibhub.dreamshops.request.AddProductRequest;
import com.gibhub.dreamshops.request.UpdateProductRequest;
import com.gibhub.dreamshops.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category,
            @RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products == null || products.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("No products found", products));
            }
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count/brand-and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        try {
            Long count = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product count retrieved successfully", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product added successfully", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Adding product failed", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest product, @PathVariable Long id) {
        try {
            Product updatedProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
