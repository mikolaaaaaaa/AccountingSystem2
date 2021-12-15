package com.mikola.accsys.builder;

import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseBuilder {
    private final int number;
    private final int apartmentCount;
    private final int floorCount;
    private List<Apartment> apartments = new ArrayList<Apartment>();

    public HouseBuilder(int number, int apartmentCount, int floorCount) {
        this.number = number;
        this.apartmentCount = apartmentCount;
        this.floorCount = floorCount;
    }

    private void createApartmentList(House house) {
        int cntApartmentForFloor = house.getApartmentCount() / house.getFloorCount();
        int tempFloor = 1;
        for (int i = 1; i <= apartmentCount; i++) {
            ApartmentBuilder apartmentBuilder = new ApartmentBuilder(i, tempFloor);
            house.getApartments().add(apartmentBuilder.build());
            if (i % cntApartmentForFloor == 0) {
                tempFloor++;
            }
        }
    }

    public House build() {
        House house = new House(number,apartmentCount,floorCount);
        createApartmentList(house);
        return house;
    }
}
