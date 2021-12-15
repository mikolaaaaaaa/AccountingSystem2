package com.mikola.accsys.repository;

import com.mikola.accsys.model.House;

public interface Specification {
    public boolean specify(House house);
}
