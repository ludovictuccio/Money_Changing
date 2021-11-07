package com.personalTraining.change.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Change {

    // Cents (0,50, 0,20, 0,10, 0,05, 0,02, 0,01)
    private BigDecimal cents50;
    private BigDecimal cents20;
    private BigDecimal cents10;
    private BigDecimal cents5;
    private BigDecimal cents2;
    private BigDecimal cents1;

    // Coins (2 and 1)
    private BigDecimal coin2;
    private BigDecimal coin1;

    // Banknotes (500, 200, 100, 50, 20, 10, 5)
    private BigDecimal banknotes5;
    private BigDecimal banknotes10;
    private BigDecimal banknotes20;
    private BigDecimal banknotes50;
    private BigDecimal banknotes100;
    private BigDecimal banknotes200;
    private BigDecimal banknotes500;
}
