package com.personalTraining.change.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.personalTraining.change.domain.Change;
import com.personalTraining.change.util.Constants;

@Service
public class ChangeMoneyService implements IChangeMoneyService {

    private static final Logger LOGGER = LogManager
            .getLogger("ChangeMoneyService");

    /**
     * This method is used to calculate the change. For each coin, cent or
     * banknotes, a tab is initiate. The first element is the result of the
     * division, and the second the modulo.
     *
     * @param moneyLeft
     * @return Change the money to change
     */
    public Change calculateChange(BigDecimal moneyLeft) {
        try {
            Change change = new Change();

            BigDecimal[] tabBkn500 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.FIVE_HUNDRED));
            change.setBanknotes500(
                    tabBkn500[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn500[1];

            BigDecimal[] tabBkn200 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TWO_HUNDRED));
            change.setBanknotes200(
                    tabBkn200[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn200[1];

            BigDecimal[] tabBkn100 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.HUNDRED));
            change.setBanknotes100(
                    tabBkn100[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn100[1];

            BigDecimal[] tabBkn50 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.FIFTY));
            change.setBanknotes50(
                    tabBkn50[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn50[1];

            BigDecimal[] tabBkn20 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TWENTY));
            change.setBanknotes20(
                    tabBkn20[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn20[1];

            BigDecimal[] tabBkn10 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TEN));
            change.setBanknotes10(
                    tabBkn10[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn10[1];

            BigDecimal[] tabBkn5 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.FIVE));
            change.setBanknotes5(
                    tabBkn5[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = tabBkn5[1];

            BigDecimal[] coins2 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TWO));
            change.setCoin2(coins2[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = coins2[1];

            BigDecimal[] coins1 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.ONE));
            change.setCoin1(coins1[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = coins1[1];

            BigDecimal[] cents50 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.FIFTY)
                            .divide(new BigDecimal(Constants.HUNDRED)));
            change.setCents50(cents50[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = cents50[1];

            BigDecimal[] cents20 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TWENTY)
                            .divide(new BigDecimal(Constants.HUNDRED)));
            change.setCents20(cents20[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = cents20[1];

            BigDecimal[] cents10 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TEN)
                            .divide(new BigDecimal(Constants.HUNDRED)));
            change.setCents10(cents10[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = cents10[1];

            BigDecimal[] cents5 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.FIVE)
                            .divide(new BigDecimal(Constants.HUNDRED)));
            change.setCents5(cents5[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = cents5[1];

            BigDecimal[] cents2 = moneyLeft
                    .divideAndRemainder(new BigDecimal(Constants.TWO)
                            .divide(new BigDecimal(Constants.HUNDRED)));
            change.setCents2(cents2[0].setScale(0, RoundingMode.HALF_EVEN));
            moneyLeft = cents2[1];

            if ((moneyLeft.compareTo(
                    new BigDecimal(Constants.ZERO_DECIMAL)) == Constants.ONE)) {
                change.setCents1(new BigDecimal(Constants.ONE));
            } else {
                change.setCents1(new BigDecimal(Constants.ZERO));
            }
            return change;
        } catch (NullPointerException np) {
            return null;
        }
    }

    /**
     * Method used to communicate with the user to determine the money to change
     *
     * @param articlePrice
     * @param givenMoney
     */
    public boolean optimalChange(final double articlePrice,
            final double givenMoney) {
        boolean isPossibleToChange = false;

        double result = (givenMoney - articlePrice);

        if ((givenMoney < articlePrice) || (givenMoney <= Constants.ZERO)
                || (articlePrice <= Constants.ZERO)) {
            result = Constants.ZERO;
            LOGGER.error(
                    "Impossible to change money. Given money can't be less than article's price, and price can't be a negative number ! Please try again.");
            return isPossibleToChange;
        } else if (givenMoney == articlePrice) {
            LOGGER.error("No money to change.");
            return isPossibleToChange;
        } else if ((!Pattern.matches(
                "(^(\\+|\\-)(0|([1-9][0-9]*))(\\.[0-9]{1,2})?$)|(^(0{0,1}|([1-9][0-9]*))(\\.[0-9]{1,2})?$)",
                String.valueOf(givenMoney)))
                || (!Pattern.matches(
                        "(^(\\+|\\-)(0|([1-9][0-9]*))(\\.[0-9]{1,2})?$)|(^(0{0,1}|([1-9][0-9]*))(\\.[0-9]{1,2})?$)",
                        String.valueOf(articlePrice)))) {
            LOGGER.error("Input invalid. You must set max 2 fract digits.");
            return isPossibleToChange;
        } else
            isPossibleToChange = true;

        BigDecimal sumToChange = new BigDecimal(result).setScale(Constants.TWO,
                RoundingMode.HALF_UP);
        Change money = calculateChange(sumToChange);

        System.out.println("TOTAL to change: " + sumToChange);
        System.out.println("--------------\nBANKNOTES quantity :");
        System.out.println("- 500 E ==> " + money.getBanknotes500());
        System.out.println("- 200 E ==> " + money.getBanknotes200());
        System.out.println("- 100 E ==> " + money.getBanknotes100());
        System.out.println("-  50 E ==> " + money.getBanknotes50());
        System.out.println("-  20 E ==> " + money.getBanknotes20());
        System.out.println("-  10 E ==> " + money.getBanknotes10());
        System.out.println("-   5 E ==> " + money.getBanknotes5());
        System.out.println("--------------\nCOINS quantity :");
        System.out.println("-  2 E ==> " + money.getCoin2());
        System.out.println("-  1 E ==> " + money.getCoin1());
        System.out.println("--------------\nCENTS quantity :");
        System.out.println("- 0,50 E ==> " + money.getCents50());
        System.out.println("- 0,20 E ==> " + money.getCents20());
        System.out.println("- 0,10 E ==> " + money.getCents10());
        System.out.println("- 0,05 E ==> " + money.getCents5());
        System.out.println("- 0,02 E ==> " + money.getCents2());
        System.out.println("- 0,01 E ==> " + money.getCents1());

        // Check one by one correct change's sum
        BigDecimal sumCents1 = (money.getCents1()
                .multiply(new BigDecimal(Constants.ONE)
                        .divide(new BigDecimal(Constants.HUNDRED))));
        BigDecimal sumCents2 = (money.getCents2()
                .multiply(new BigDecimal(Constants.TWO)
                        .divide(new BigDecimal(Constants.HUNDRED))));
        BigDecimal sumCents5 = (money.getCents5()
                .multiply(new BigDecimal(Constants.FIVE)
                        .divide(new BigDecimal(Constants.HUNDRED))));
        BigDecimal sumCents10 = (money.getCents10()
                .multiply(new BigDecimal(Constants.TEN)
                        .divide(new BigDecimal(Constants.HUNDRED))));
        BigDecimal sumCents20 = (money.getCents20()
                .multiply(new BigDecimal(Constants.TWENTY)
                        .divide(new BigDecimal(Constants.HUNDRED))));
        BigDecimal sumCents50 = (money.getCents50()
                .multiply(new BigDecimal(Constants.FIFTY)
                        .divide(new BigDecimal(Constants.HUNDRED))));

        BigDecimal sumCoin1 = (money.getCoin1()
                .multiply(new BigDecimal(Constants.ONE)));
        BigDecimal sumCoin2 = (money.getCoin2()
                .multiply(new BigDecimal(Constants.TWO)));
        BigDecimal sumBanknotes5 = (money.getBanknotes5()
                .multiply(new BigDecimal(Constants.FIVE)));
        BigDecimal sumBanknotes10 = (money.getBanknotes10()
                .multiply(new BigDecimal(Constants.TEN)));
        BigDecimal sumBanknotes20 = (money.getBanknotes20()
                .multiply(new BigDecimal(Constants.TWENTY)));
        BigDecimal sumBanknotes50 = (money.getBanknotes50()
                .multiply(new BigDecimal(Constants.FIFTY)));
        BigDecimal sumBanknotes100 = (money.getBanknotes100()
                .multiply(new BigDecimal(Constants.HUNDRED)));
        BigDecimal sumBanknotes200 = (money.getBanknotes200()
                .multiply(new BigDecimal(Constants.TWO_HUNDRED)));
        BigDecimal sumBanknotes500 = (money.getBanknotes500()
                .multiply(new BigDecimal(Constants.FIVE_HUNDRED)));

        // Addition to check correct change's sum
        BigDecimal moneySumChanged = sumCents1.add(sumCents2).add(sumCents5)
                .add(sumCents10).add(sumCents20).add(sumCents50).add(sumCoin1)
                .add(sumCoin2).add(sumBanknotes5).add(sumBanknotes10)
                .add(sumBanknotes20).add(sumBanknotes50).add(sumBanknotes100)
                .add(sumBanknotes200).add(sumBanknotes500);
        System.out.println("-------------- \nTOTAL changed: "
                + moneySumChanged.setScale(2, RoundingMode.HALF_UP)
                + "\n--------------");

        return isPossibleToChange;
    }

}
