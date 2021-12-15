package com.mikola.accsys.repository.impl;

import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.model.House;

public class NumberSpecification implements Specification {
    private int number;

    public NumberSpecification(int number) {
        this.number = number;
    }

    @Override
    public boolean specify(House house) {
        return house.getNumber() == number;
    }

}
