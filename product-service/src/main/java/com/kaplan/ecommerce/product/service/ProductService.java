package com.kaplan.ecommerce.product.service;

import com.kaplan.ecommerce.product.entity.Category;
import com.kaplan.ecommerce.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, BigDecimal quantity);
}
