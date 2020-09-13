package com.trendyol.shoppingcard.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category = new Category();
    String expectedResult = "result";

    @Test
    public void testGetId() {
        category.setId(expectedResult);
        String id = category.getId();

        assertEquals(expectedResult, id);
    }

    @Test
    public void testGetTitle() {
        category.setTitle(expectedResult);
        String title = category.getTitle();
        assertEquals(expectedResult, title);
    }
}
