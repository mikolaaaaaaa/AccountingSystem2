package com.mikola.accsys.repository;

import com.mikola.accsys.builder.HouseBuilder;
import com.mikola.accsys.comparator.HouseComparator;
import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.model.House;
import com.mikola.accsys.repository.impl.HouseRepository;
import com.mikola.accsys.repository.impl.IdSpecification;
import com.mikola.accsys.repository.impl.NumberSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HouseRepositoryTest {
    private HouseRepository houseRepository;

    @Test
    public void testAddShouldAddHouseInRepository() {
        houseRepository = new HouseRepository();
        House house = new House(1, 1 ,1);
        List<Apartment> apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        house.setApartments(apartmentList);
        houseRepository.add(house);
        Map<Integer, House> expected = Map.of(house.getId(), house);
        Map<Integer, House> actual = houseRepository.getHouseMap();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveShouldRemoveHouseFromRepository() {
        houseRepository = new HouseRepository();
        House house = new House(1, 2, 4);
        List<Apartment> apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        house.setApartments(apartmentList);
        houseRepository.add(house);
        houseRepository.remove(house);
        Assertions.assertTrue(houseRepository.getHouseMap().isEmpty());
    }

    @Test
    public void testQueryShouldReturnListOfHouseBySpecification() {
        houseRepository = new HouseRepository();
        House firstHouse = new House(1, 2, 4);
        List<Apartment> apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        firstHouse.setApartments(apartmentList);
        House secondHouse = new House(2, 2, 4);
        apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        secondHouse.setApartments(apartmentList);
        houseRepository.add(firstHouse);
        houseRepository.add(secondHouse);
        List<House> expected = List.of(firstHouse);
        List<House> actual = houseRepository.query(new NumberSpecification(1));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSortShouldReturnSortedListOfHouse() {
        houseRepository = new HouseRepository();
        House firstHouse = new House(1, 2, 4);
        List<Apartment> apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        firstHouse.setApartments(apartmentList);
        House secondHouse = new House(2, 2, 4);
        apartmentList = List.of(new Apartment(1, 1, 1, 1, 1));
        secondHouse.setApartments(apartmentList);
        houseRepository.add(secondHouse);
        houseRepository.add(firstHouse);
        List<House> expected = List.of(firstHouse,secondHouse);
        List<House> actual = houseRepository.sort(HouseComparator.getIdComparator());
        Assertions.assertEquals(expected,actual);
    }
}
