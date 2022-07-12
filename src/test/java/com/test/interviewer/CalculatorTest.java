package com.test.interviewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CalculatorTest {
    private int a, b;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();

        a = ThreadLocalRandom.current().nextInt();
        b = ThreadLocalRandom.current().nextInt();
    }

    @Test
    void testAdd() {
        int result = calculator.add(a, b);

        assertEquals( a + b, result, "Resultado incorrecto de la suma");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,4,5,6})
    void testAddParameters(int valid) {
        a = 3;
        assumeTrue(b == valid, "int b is not accepted value");
            int result = calculator.add(a, b);
            assertEquals( a + b, result, "Resultado incorrecto de la suma");
    }

    @Test
    void testAddThrowsExceptionWhenIsCalledWithInvalidParams() {
        String c = "hello";

        assertThrows(Exception.class, () -> {
            int result = calculator.add(a, Integer.parseInt(c));

            assertEquals( a + b, result, "Resultado incorrecto de la suma");
        });
    }

    @Test
    void testMultiply() {
        int additionResult = calculator.add(a, b);

        assumeTrue(additionResult == a+b);

        int multiplicationResult = calculator.multiply(a, b);

        Assertions.assertEquals(a * b, multiplicationResult, "Resultado incorrecto de la multiplicación");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,4,5,6})
    void testMultiplyParameters(int valid) {
        a = 3;
        assumeTrue(b == valid, "int b is not accepted value");
            int additionResult = calculator.add(a, b);
        assumeTrue(additionResult == a+b);
            int multiplicationResult = calculator.multiply(a, b);
            Assertions.assertEquals(a * b, multiplicationResult, "Resultado incorrecto de la multiplicación");
    }
}