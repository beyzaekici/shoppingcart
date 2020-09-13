package com.trendyol.shoppingcard.base;

import com.trendyol.shoppingcard.ShoppingcardApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = {ShoppingcardApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BaseIntegration {
    @Test
    public void runnableTestMethod() {
    }
}
