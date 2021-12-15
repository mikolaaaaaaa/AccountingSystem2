package com.mikola.accsys.builder;

import  com.mikola.accsys.model.Apartment;

public class ApartmentBuilder {
    private int sqare;
    private int roomer;
    private int cntRooms;
    private int number;
    private int floor;

    public ApartmentBuilder(int number, int floor) {
        this.number = number;
        this.floor = floor;
    }

    public void fixSqare() {
        this.sqare = (int) ((Math.random() + 1) * 40);
    }

    public void fixRoomer() {
        this.roomer = (int) ((Math.random() * 4) + 1);
    }

    public void fixCntRooms() {
        this.cntRooms = this.roomer;
    }

    public Apartment build() {
        fixSqare();
        fixRoomer();
        fixCntRooms();
        return new Apartment(sqare, roomer, cntRooms, number, floor);
    }

}
