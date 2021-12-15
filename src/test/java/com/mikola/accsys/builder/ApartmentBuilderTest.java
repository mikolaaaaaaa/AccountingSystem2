package com.mikola.accsys.builder;

import com.mikola.accsys.model.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApartmentBuilderTest {
    @Test
    public void testBuildShouldCreateApartment() {
          ApartmentBuilder apartmentBuilder = new ApartmentBuilder(1,1);
          int expectedNumber = 1;
          int expectedFloor = 1;
          Apartment expected = apartmentBuilder.build();
        Assertions.assertTrue(expected.getFloor() == expectedFloor && expected.getNumber() == expectedNumber);
    }
}
