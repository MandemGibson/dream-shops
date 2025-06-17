package com.gibhub.dreamshops.service.product;

import java.util.List;

import com.gibhub.dreamshops.models.Product;
import com.gibhub.dreamshops.request.AddProductRequest;
import com.gibhub.dreamshops.request.UpdateProductRequest;

public interface IProductService {
    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    void deleteProduct(Long id);

    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

}
