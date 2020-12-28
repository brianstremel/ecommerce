package com.kaplan.ecommerce.product;

import com.kaplan.ecommerce.product.entity.Category;
import com.kaplan.ecommerce.product.entity.Product;
import com.kaplan.ecommerce.product.repository.ProductRepository;
import com.kaplan.ecommerce.product.service.ProductService;
import com.kaplan.ecommerce.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product pc = Product.builder()
                .id(1L)
                .name("pc")
                .category(Category.builder().id(1L).build())
                .price(new BigDecimal(1400.50))
                .stock(BigDecimal.ONE)
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(pc));

        Mockito.when(productRepository.save(pc)).thenReturn(pc);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct() {
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("pc");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {
        Product newStock = productService.updateStock(1L, BigDecimal.TEN);
        Assertions.assertThat(newStock.getStock()).isEqualTo(BigDecimal.TEN);
    }
}
