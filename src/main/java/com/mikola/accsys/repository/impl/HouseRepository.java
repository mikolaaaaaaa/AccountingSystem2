package com.mikola.accsys.repository.impl;

import com.mikola.accsys.repository.Repository;
import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class HouseRepository implements Repository {
    private Map<Integer, House> houseMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(HouseRepository.class);
    public Map<Integer, House> getHouseMap() {
        return Collections.unmodifiableMap(houseMap);
    }

    @Override
    public void add(House house) {
        houseMap.put(house.getId(), house);
        logger.info("House №{}(id = {}) added",house.getNumber(),house.getId());
    }

    @Override
    public void remove(House house) {
        houseMap.remove(house.getId());
        logger.info("House №{}(id = {}) removed",house.getNumber(),house.getId());
    }

    @Override
    public void update(House house)
    {
        houseMap.put(house.getId(),house);
        logger.info("House №{}(id = {}) updated ",house.getNumber(),house.getId());
    }

    @Override
    public List<House> query(Specification specification) {
        List<House> houses = new ArrayList<>();
        for(House i : houseMap.values()) {
            if (specification.specify(i)) {
                houses.add(i);
            }
        }
        return houses;
    }

    @Override
    public List<House> sort(Comparator<House> comparator) {
        List<House> houses = new ArrayList<>(houseMap.values());
        houses.sort(comparator);
        return houses;
    }
}
