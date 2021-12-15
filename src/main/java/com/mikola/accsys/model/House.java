package com.mikola.accsys.model;

import com.mikola.accsys.util.IdGenerator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class House {

    private int number;
    private int apartmentCount;
    private int floorCount;
    private int id;
    private List<Apartment> apartments = new ArrayList<Apartment>();

    public House() {
        id = IdGenerator.generateId();
    }

    public House(int number, int apartmentCount, int floorCount) {
        this.number = number;
        this.apartmentCount = apartmentCount;
        this.floorCount = floorCount;
        this.id = IdGenerator.generateId();
    }

}
