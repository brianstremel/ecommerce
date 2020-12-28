package com.kaplan.ecommerce.product;

import com.kaplan.ecommerce.product.entity.Category;
import com.kaplan.ecommerce.product.entity.Product;
import com.kaplan.ecommerce.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct() {

        Product product = Product.builder()
                .name("pc")
                .category(Category.builder().id(1L).build())
                .description("Alieware 2020")
                .stock(new BigDecimal(12))
                .price(new BigDecimal(1000))
                .status("Avaible")
                .createAt(new Date())
                .build();

        productRepository.save(product);

        List<Product> founds = productRepository.findByCategory(product.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);

    }
}
