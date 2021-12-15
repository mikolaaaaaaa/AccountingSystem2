package com.mikola.accsys.validator;

import com.mikola.accsys.builder.HouseBuilder;
import com.mikola.accsys.model.House;
import com.mikola.accsys.repository.impl.HouseRepository;
import com.mikola.accsys.validators.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    private final InputValidator inputValidator = new InputValidator();

    @Test
    public void testCheckLineFormatShouldReturnTrue() {
        String goodString = "1 2 3 4 5";
        boolean actual = inputValidator.checkLineFormat(goodString);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testCheckLineFormatShouldReturnFalse() {
        String wrongString = "1a 2, 3 4 5";
        boolean actual = inputValidator.checkLineFormat(wrongString);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testCheckCountOfNumbersShouldReturnTrue() {
        String goodString = "1 2 3 4 5";
        boolean actual = inputValidator.checkCountOfNumbers(goodString,5);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testCheckCountOfNumbersShouldReturnFalse() {
        String string = "1 2 3 4 5";
        int countOfNumbers = 2;
        boolean actual = inputValidator.checkCountOfNumbers(string,countOfNumbers);
        Assertions.assertFalse(actual);
    }

}
