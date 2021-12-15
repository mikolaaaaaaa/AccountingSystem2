package com.mikola.accsys.modelservice;

import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.model.House;
import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.repository.impl.HouseRepository;
import com.mikola.accsys.repository.impl.NumberSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class HouseService {

    private static HouseService instance;
    private static final Logger logger = LogManager.getLogger(HouseService.class);

    private HouseService() {

    }

    public static HouseService getInstance() {
        if (instance == null) {
            instance = new HouseService();
        }
        return instance;
    }

    public Apartment findApartment(int number, House house) {
        for (Apartment i : house.getApartments()) {
            if (i.getNumber() == number) {
                return i;
            }
        }
        Apartment temp = new Apartment();
        temp.setNumber(-1);
        return temp;
    }

    public Map<String,Integer> getInfo(House house) {
        return Map.of("number", house.getNumber(),
                "apartmentCount", house.getApartmentCount(),
                "floorsCount", house.getFloorCount(),
                "id",house.getId());
    }

    public int allSqare(House house) {
        int result = 0;
        for (Apartment i : house.getApartments()) {
            result += i.getSqare();
        }
        logger.info("All sqare house №{} is {}",house.getNumber(),result);
        return result;
    }

    public Character getSign(int x, int y) {
        switch (Integer.compare(x, y)) {
            case 1 -> {
                return '>';
            }
            case 0 -> {
                return '=';
            }
            case -1 -> {
                return '<';
            }
        }
        return ' ';
    }

    public Map<String,Character> compareHouse(House houseOne, House houseTwo) {
        Map<String,Character> result = new HashMap<>();
        result.put("apartmentCount",getSign(houseOne.getApartmentCount(),houseTwo.getApartmentCount()));
        result.put("floorsCount",getSign(houseOne.getFloorCount(),houseTwo.getFloorCount()));
        result.put("sqare",getSign(allSqare(houseOne), allSqare(houseTwo)));
        return Collections.unmodifiableMap(result);
    }

    public boolean checkNumberOfHouse(int numberOfHouse, HouseRepository houseRepository) {
        Specification numberSpecification = new NumberSpecification(numberOfHouse);
        List<House> houses = houseRepository.query(numberSpecification);
        return houses.isEmpty();
    }

    public boolean checkNumberOfApartment(int numberOfHouse, int numberOfApartment,HouseRepository houseRepository) {
        Specification numberSpecification = new NumberSpecification(numberOfHouse);
        List<House> houses = houseRepository.query(numberSpecification);
        boolean checkHouse = checkNumberOfHouse(numberOfHouse,houseRepository);

        House house = houses.get(0);
        Optional<Apartment> apartment = house.getApartments()
                .parallelStream()
                .filter(e -> e.getNumber() == numberOfApartment)
                .findFirst();
        boolean checkApartment = apartment.isEmpty();
        return checkApartment || checkHouse;

    }

    public boolean checkQualityApartmentCount(int floorCount, int apartmentCount) {
        return apartmentCount % floorCount == 0;
    }

}
