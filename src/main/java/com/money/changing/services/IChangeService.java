package com.money.changing.services;

import java.math.BigDecimal;

import com.money.changing.controllers.exceptions.BadEntryInputException;
import com.money.changing.domain.Money;

public interface IChangeService {

    Money calculateChange(BigDecimal moneyLeft);

    Money optimalChange(double articlePrice, double givenMoney)
            throws BadEntryInputException;
}
