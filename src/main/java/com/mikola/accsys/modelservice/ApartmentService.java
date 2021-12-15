package com.mikola.accsys.modelservice;

import com.mikola.accsys.model.Apartment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApartmentService {

    private static ApartmentService instance;

    private ApartmentService() {

    }

    public static ApartmentService getInstance() {
        if (instance == null) {
            instance = new ApartmentService();
        }
        return instance;
    }

    public Character calculateSign(int x, int y) {

        switch (Integer.compare(x, y)) {
            case 1 -> {
                return '>';
            }
            case 0 -> {
                return '=';
            }
            case -1 -> {
                return '<';
            }

        }
        return ' ';
    }

    public Map<String,Character> compareApartment(Apartment firstApartment,Apartment secondApartment) {
        Map<String,Character> result = new HashMap<>();
        result.put("sqare", calculateSign(firstApartment.getSqare(),secondApartment.getSqare()));
        result.put("roomers", calculateSign(firstApartment.getRoomer(),secondApartment.getRoomer()));
        result.put("cntRooms", calculateSign(firstApartment.getCntRooms(),secondApartment.getCntRooms()));
        result.put("floor", calculateSign(firstApartment.getFloor(),secondApartment.getFloor()));
        return Collections.unmodifiableMap(result);
    }

    public Map<String,Integer> getInfo(Apartment apartment) {
        Map<String,Integer> apartmentInfo = new HashMap<>();
        apartmentInfo.put("number",apartment.getNumber());
        apartmentInfo.put("sqare",apartment.getSqare());
        apartmentInfo.put("roomer",apartment.getRoomer());
        apartmentInfo.put("cntRooms",apartment.getCntRooms());
        apartmentInfo.put("floor",apartment.getFloor());
        return Collections.unmodifiableMap(apartmentInfo);
    }
}
