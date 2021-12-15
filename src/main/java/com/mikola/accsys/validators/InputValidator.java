package com.mikola.accsys.validators;

import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.repository.impl.HouseRepository;
import com.mikola.accsys.exception.AccountingSystemException;
import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.model.House;
import com.mikola.accsys.repository.impl.IdSpecification;
import com.mikola.accsys.repository.impl.NumberSpecification;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InputValidator {
    private String TEMPLATE_REGEX = "^[0-9 ]+$";

    public boolean checkLineFormat(String s) {
        return s.matches(TEMPLATE_REGEX);
    }

    public boolean checkCountOfNumbers(String s,int countOfNumbers) {
         return s.split(" ").length == countOfNumbers;
    }

    public boolean checkInputLine(String s,int countOfNumbers) {
        return checkLineFormat(s) && checkCountOfNumbers(s,countOfNumbers);
    }

}
