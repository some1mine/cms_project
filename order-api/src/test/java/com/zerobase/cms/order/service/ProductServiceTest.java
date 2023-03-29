package com.zerobase.cms.order.service;

import com.zerobase.cms.order.domain.model.Product;
import com.zerobase.cms.order.domain.product.AddProductForm;
import com.zerobase.cms.order.domain.product.AddProductItemForm;
import com.zerobase.cms.order.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void add_product_test() {
        Long sellerId = 1L;

        AddProductForm form = makeProductForm("nike airforce", "footwear", 3);

        Product product = productService.addProduct(sellerId, form);

        Product result = productRepository.findWithProductItemsById(product.getId()).get();

        assertEquals(result.getName(), "nike airforce");
        assertEquals(result.getDescription(), "footwear");
        assertEquals(result.getProductItems().size(), 3);
        assertEquals(result.getProductItems().get(0).getName(), "nike airforce0");
        assertEquals(result.getProductItems().get(0).getPrice(), 10000);
        assertEquals(result.getProductItems().get(0).getCount(), 1);
    }

    private static AddProductForm makeProductForm(String name, String description, int itemCount) {
        List<AddProductItemForm> itemForms = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            itemForms.add(makeProductItemForm(null, name + i));
        }
        return AddProductForm.builder()
                .name(name)
                .description(description)
                .items(itemForms)
                .build();
    }

    private static AddProductItemForm makeProductItemForm(Long productId, String name) {
        return AddProductItemForm.builder()
                .productId(productId)
                .name(name)
                .price(10000)
                .count(1)
                .build();
    }
}