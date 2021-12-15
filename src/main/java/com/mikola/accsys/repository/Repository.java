package com.mikola.accsys.repository;

import com.mikola.accsys.model.House;

import java.util.Comparator;
import java.util.List;

public interface Repository {
    public void add(House house);
    public void remove(House house);
    public void update(House house);
    public List<House> query(Specification specification);
    public List<House> sort(Comparator<House> comparator);
}
