package com.mikola.accsys.model;

import lombok.Data;

@Data
public class Apartment {
    private int sqare;
    private int roomer;
    private int cntRooms;
    private int number;
    private int floor;

    public Apartment() {
    }

    public Apartment(int sqare, int roomer, int cntRooms, int number, int floor) {
        this.sqare = sqare;
        this.roomer = roomer;
        this.cntRooms = cntRooms;
        this.number = number;
        this.floor = floor;
    }

}
