package com.personalTraining.change.services;

import java.math.BigDecimal;

import com.personalTraining.change.domain.Change;

public interface IChangeMoneyService {

    Change calculateChange(BigDecimal moneyLeft);

    boolean optimalChange(double articlePrice, double givenMoney);
}
