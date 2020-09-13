package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.service.CalculatorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

public class CalculatorControllerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CalculatorService calculatorService;

    private CalculatorController calculatorController;

    @Before
    public void setUp() {
        initMocks(this);
        calculatorController = new CalculatorController(calculatorService);
    }

    @Test
    public void getResultOfCalculate() {
        doReturn(new BigDecimal(0)).when(calculatorService).getResultOfCalculate(anyString());

        ApiResponse<BigDecimal> response = calculatorController.getResultOfCalculate("1");

        assertNotNull(response.getData());
    }

    @Test
    public void getResultOfCalculate_shouldBeException() {
        doThrow(BaseException.class).when(calculatorService).getResultOfCalculate(anyString());

        expectedException.expect(BaseException.class);

        calculatorController.getResultOfCalculate("1");
    }
}
