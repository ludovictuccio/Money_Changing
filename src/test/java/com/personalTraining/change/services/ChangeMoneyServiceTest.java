package com.personalTraining.change.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.personalTraining.change.domain.Change;

@SpringBootTest
public class ChangeMoneyServiceTest {

    @Autowired
    public IChangeMoneyService changeMoneyService;

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Integers - 80 to change (50,20,10)")
    public void givenTwoIntValues_whenChange_thenReturnCorrectInteger() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("80"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes10()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Integers - 888 to change")
    public void givenMaxIntValues_whenChange_thenReturnCorrectInteger() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("888"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes500()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes200()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes100()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes5()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCoin2()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCoin1()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Integers - 1776 to change")
    public void givenMaxMutliplyTwoIntValues_whenChange_thenReturnCorrectInteger() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("1776"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes500()).isEqualTo(new BigDecimal("3"));
        assertThat(result.getBanknotes200()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes5()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCoin1()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Integers - 888 x 10 to change")
    public void givenMaxMutliplyTenIntValues_whenChange_thenReturnCorrectInteger() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("8880"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes500()).isEqualTo(new BigDecimal("17"));
        assertThat(result.getBanknotes200()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes100()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes10()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Integers - 15 to change")
    public void givenFiftyIntValues_whenChange_thenReturnCorrectInteger() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("15"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes5()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Error - Empty / null amount")
    public void givenNullAmountToChange_whenChange_thenReturnNull() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService.calculateChange(null);
        // THEN
        assertThat(result).isNull();
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Decimal - 888.88 to change")
    public void givenMaxDecimalValues_whenChange_thenReturnCorrectResult() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("888.88"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getBanknotes500()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes200()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes100()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getBanknotes5()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCoin2()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCoin1()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents50()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents5()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents2()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents1()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Decimal - 4,34 to change")
    public void givenDecimalValues_whenChange_thenReturnCorrectResult() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("4.34"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getCoin2()).isEqualTo(new BigDecimal("2"));
        assertThat(result.getCents20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents2()).isEqualTo(new BigDecimal("2"));
    }

    @Test
    @Tag("calculateChange")
    @DisplayName("Change - Ok - Decimal - 4,33 to change")
    public void givenDecimalValues2_whenChange_thenReturnCorrectResult() {
        // GIVEN
        // WHEN
        Change result = changeMoneyService
                .calculateChange(new BigDecimal("4.33"));
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getCoin2()).isEqualTo(new BigDecimal("2"));
        assertThat(result.getCents20()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents10()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents2()).isEqualTo(new BigDecimal("1"));
        assertThat(result.getCents1()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - 0 to change - False")
    public void givenZeroToChange_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(0.00,
                0.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry -  -10 to change - False")
    public void givenNegativeNumber_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(-10.00,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - Article price > given money - False")
    public void givenArticlePriceSuperiorThanGivenChange_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(20.00,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - Same value - False")
    public void givenNoMoneyToChange_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(10.00,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - 1 cents to change - True")
    public void givenOneCentToChange_whenChange_thenReturnTrue() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(9.99,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isTrue();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - .25 - True")
    public void givenCentsWithoutZero_whenChange_thenReturnTrue() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(.25,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isTrue();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - 1 cents to change - True")
    public void givenIntegerValueEntry_whenPossibleChange_thenReturnTrue() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(1, 10);
        // THEN
        assertThat(isPossibleToChange).isTrue();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - 9.999 - Not valid decimal - False")
    public void givenArticlePriceWithThreeDecimal_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(9.999,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }

    @Test
    @Tag("optimalChange")
    @DisplayName("UserEntry - -.25 - Not valid decimal - False")
    public void givenNegativeInputWithOnlyFractDigits_whenChange_thenReturnFalse() {
        // GIVEN
        // WHEN
        boolean isPossibleToChange = changeMoneyService.optimalChange(-.25,
                10.00);
        // THEN
        assertThat(isPossibleToChange).isFalse();
    }
}
