package com.personalTraining.change;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personalTraining.change.services.ChangeMoneyService;

@SpringBootApplication
public class ChangeMoneyApplication {

    private static final Logger LOGGER = LogManager
            .getLogger("ChangeMoneyApplication");

    public static void main(final String[] args) {

        ChangeMoneyService changeMoneyService = new ChangeMoneyService();
        String response = "Y";
        Scanner scanner = new Scanner((System.in), "UTF-8");

        try {
            do {
                System.out.println("PLEASE ENTER YOUR ARTICLE PRICE : ");
                double articlePrice = scanner.nextDouble();

                System.out.println(
                        "PLEASE ENTER YOUR YOUR GIVEN MONEY FOR THE CHANGE : ");
                double givenMoney = scanner.nextDouble();

                changeMoneyService.optimalChange(articlePrice, givenMoney);

                do {
                    System.out.println(
                            "Do you want change money for other article ? (Y/N)");
                    response = scanner.next();
                    scanner.nextLine();
                } while ((!response.toUpperCase().equals("Y"))
                        && (!response.toUpperCase().equals("N")));

            } while (response.toUpperCase().equals("Y"));
            scanner.close();
            System.out.println("Good Bye !");
        } catch (InputMismatchException ex) {
            LOGGER.error(
                    "Input data invalid. Only numbers are allowed. For a decimal number, you must set , and not a .");
        }
    }

}
