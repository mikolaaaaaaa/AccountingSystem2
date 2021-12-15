package com.mikola.accsys.service;

import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.modelservice.ApartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ApartmentServiceTest {
    private ApartmentService apartmentService = ApartmentService.getInstance();

    @Test
    public void testCalculateSignShouldReturnSign() {
        char expected = '>';
        char actual = apartmentService.calculateSign(2,1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCompareApartmentShouldReturnMapOfComparisonResult() {
        Apartment firstApartment = new Apartment(1,1,1,1,1);
        Apartment secondApartment = new Apartment(2,2,2,2,2);
        Map<String,Character> expected = Map.of(
                "sqare",'<',
                "roomers",'<',
                "cntRooms",'<',
                "floor",'<');
        Map<String,Character> actual = apartmentService.compareApartment(firstApartment,secondApartment);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testGetInfoShouldReturnMapOfApartmentInformation() {
        Apartment apartment = new Apartment(1,1,1,1,1);
        Map<String,Integer> expected = Map.of(
                "roomer",1,
                "number",1,
                "cntRooms",1,
                "floor",1,
                "sqare",1);
        Map<String,Integer> actual = apartmentService.getInfo(apartment);
        Assertions.assertEquals(expected,actual);
    }
}
