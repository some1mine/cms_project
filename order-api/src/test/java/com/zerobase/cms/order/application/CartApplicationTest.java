package com.zerobase.cms.order.application;

import com.zerobase.cms.order.domain.model.Product;
import com.zerobase.cms.order.domain.product.AddProductCartForm;
import com.zerobase.cms.order.domain.product.AddProductForm;
import com.zerobase.cms.order.domain.product.AddProductItemForm;
import com.zerobase.cms.order.domain.redis.Cart;
import com.zerobase.cms.order.domain.repository.ProductRepository;
import com.zerobase.cms.order.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartApplicationTest {
    @Autowired
    private CartApplication cartApplication;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void add_test() {
        Long customerId = 100L;

        cartApplication.clearCart(customerId);

        Product product = add_product();
        Product result = productRepository.findWithProductItemsById(product.getId()).get();

        assertNotNull(result);

        assertEquals(result.getName(), "nike airforce");
        assertEquals(result.getDescription(), "footwear");
        assertEquals(result.getProductItems().size(), 3);
        assertEquals(result.getProductItems().get(0).getName(), "nike airforce0");
        assertEquals(result.getProductItems().get(0).getPrice(), 10000);


        Cart cart = cartApplication.addCart(customerId, makeAddForm(result));

        assertEquals(cart.getMessages().size(), 0);

        cart = cartApplication.getCart(customerId);
        assertEquals(cart.getMessages().size(), 0);
    }

    AddProductCartForm makeAddForm(Product product) {
        AddProductCartForm.ProductItem productItem = AddProductCartForm.ProductItem.builder()
                .id(product.getProductItems().get(0).getId())
                .name(product.getProductItems().get(0).getName())
                .count(5)
                .price(20000)
                .build();
        return AddProductCartForm.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .name(product.getName())
                .description(product.getDescription())
                .items(List.of(productItem))
                .build();

    }

    Product add_product() {
        Long sellerId = 1L;

        AddProductForm form = makeProductForm("nike airforce", "footwear", 3);

        return productService.addProduct(sellerId, form);
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
                .count(10)
                .build();
    }
}