package com.mikola.accsys.repository.impl;

import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.model.House;

public class IdSpecification implements Specification {
    private long from;
    private long to;

    public IdSpecification(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean specify(House house) {
        return house.getId() >= from && house.getId() <= to;
    }
}
