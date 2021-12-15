package com.mikola.accsys.service;

import com.mikola.accsys.builder.HouseBuilder;
import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.model.House;
import com.mikola.accsys.modelservice.HouseService;
import com.mikola.accsys.repository.impl.HouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class HouseServiceTest {
    private HouseService houseService = HouseService.getInstance();

    @Test
    public void testGetApartmentShouldReturnApartmentByNumber() {
        House house = new House(1, 2, 4);
        Apartment expected = new Apartment(1, 1, 1, 1, 1);
        List<Apartment> apartmentList = List.of(expected);
        house.setApartments(apartmentList);
        Apartment actual = houseService.findApartment(expected.getNumber(),house);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testAllSqareShouldReturnAllSqareOfHouse() {
        House house = new House(1, 2, 4);
        Apartment apartment = new Apartment(1, 1, 1, 1, 1);
        List<Apartment> apartmentList = List.of(apartment);
        house.setApartments(apartmentList);
        int expected = apartment.getSqare();
        int actual = houseService.allSqare(house);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCompareHouseShouldReturnMapOfComparisonResult() {
        House firstHouse = new House(1, 2, 4);
        List<Apartment> apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        firstHouse.setApartments(apartmentList);
        House secondHouse = new House(2, 4, 8);
        apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        secondHouse.setApartments(apartmentList);
        Map<String,Character> expected = Map.of(
                "apartmentCount",'<',
                "floorsCount",'<',
                "sqare",'='
        );
        Map<String,Character> actual = houseService.compareHouse(firstHouse,secondHouse);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCheckNumberOfHouseShouldReturnFalse() {
        int numberOfHouse = 1;
        HouseRepository houseRepository = new HouseRepository();
        HouseBuilder houseBuilder = new HouseBuilder(numberOfHouse,1,1);
        House house = houseBuilder.build();
        houseRepository.add(house);
        boolean actual = houseService.checkNumberOfHouse(numberOfHouse,houseRepository);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testCheckNumberOfHouseShouldReturnTrue() {
        int numberOfHouse = 1;
        int wrongNumberOfHouse = 2;
        HouseRepository houseRepository = new HouseRepository();
        HouseBuilder houseBuilder = new HouseBuilder(numberOfHouse,1,1);
        House house = houseBuilder.build();
        houseRepository.add(house);
        boolean actual = houseService.checkNumberOfHouse(wrongNumberOfHouse,houseRepository);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testCheckNumberOfApartmentShouldReturnTrue() {
        int numberOfHouse = 1;
        int apartmentCount = 1;
        int floorCount = 1;
        HouseRepository houseRepository = new HouseRepository();
        HouseBuilder houseBuilder = new HouseBuilder(numberOfHouse,apartmentCount,floorCount);
        House house = houseBuilder.build();
        houseRepository.add(house);
        int numberOfApartment = apartmentCount - 1;
        boolean actual = houseService.checkNumberOfApartment(numberOfHouse,numberOfApartment,houseRepository);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testCheckNumberOfApartmentShouldReturnFalse() {
        int numberOfHouse = 1;
        int apartmentCount = 1;
        int floorCount = 1;
        HouseRepository houseRepository = new HouseRepository();
        HouseBuilder houseBuilder = new HouseBuilder(numberOfHouse,apartmentCount,floorCount);
        House house = houseBuilder.build();
        houseRepository.add(house);
        int numberOfApartment = apartmentCount;
        boolean actual = houseService.checkNumberOfApartment(numberOfHouse,numberOfApartment,houseRepository);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testCheckQualityApartmentCountShouldReturnTrue() {
        int floorCount = 2;
        int apartmentCount = 4;
        boolean expected = houseService.checkQualityApartmentCount(floorCount,apartmentCount);
        Assertions.assertTrue(expected);
    }

    @Test
    public void testCheckQualityApartmentCountShouldReturnFalse() {
        int floorCount = 2;
        int apartmentCount = 7;
        boolean expected = houseService.checkQualityApartmentCount(floorCount,apartmentCount);
        Assertions.assertFalse(expected);
    }

}
