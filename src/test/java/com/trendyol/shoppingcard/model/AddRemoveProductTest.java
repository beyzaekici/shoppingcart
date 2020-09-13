package com.trendyol.shoppingcard.model;

import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AddRemoveProductTest {

    AddRemoveProduct addRemoveProduct = new AddRemoveProduct();
    String expectedResult = "result";
    Integer expectedInteger = 1;

    @Test
    public void testGetCartId() {
        addRemoveProduct.setCartId(expectedResult);
        String id = addRemoveProduct.getCartId();

        assertEquals(expectedResult, id);
    }

    @Test
    public void testGetQuantity() {
        addRemoveProduct.setQuantity(expectedInteger);
        Integer quantity = addRemoveProduct.getQuantity();

        assertEquals(expectedInteger, quantity);
    }

    @Test
    public void testGetProductId() {
        addRemoveProduct.setProductId(expectedResult);
        String id = addRemoveProduct.getProductId();

        assertEquals(expectedResult, id);
    }

}
