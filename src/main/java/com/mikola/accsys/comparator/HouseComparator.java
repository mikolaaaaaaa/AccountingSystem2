package com.mikola.accsys.comparator;

import com.mikola.accsys.model.House;

import java.util.Comparator;

public class HouseComparator {
    public static Comparator<House> getIdComparator() {
        return new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return Long.compare(o1.getId(),o2.getId());
            }
        };
    }
}
