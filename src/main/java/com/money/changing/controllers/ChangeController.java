package com.money.changing.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.money.changing.controllers.exceptions.BadEntryInputException;
import com.money.changing.domain.Money;
import com.money.changing.services.ChangeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/change")
public class ChangeController {

    private static final Logger LOGGER = LogManager
            .getLogger("ChangeController");

    @Autowired
    public ChangeService changeService;

//    @GetMapping
//    public int changeMoney() {
//        return 500;
//    }

    @GetMapping
    public Money changeMoney(@RequestParam final double articlePrice,
            @RequestParam final double givenMoney)
            throws BadEntryInputException {

        Money money = changeService.optimalChange(articlePrice, givenMoney);

        return money;
    }

//    @GetMapping
//    public ResponseEntity<Boolean> changeMoney(
//            @RequestParam final double articlePrice,
//            @RequestParam final double givenMoney) {
//
//        boolean isChanged;
//
//        isChanged = changeService.optimalChange(articlePrice, givenMoney);
//
//        if (isChanged == false) {
//            LOGGER.error("Error while money changing");
//            return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<Boolean>(HttpStatus.OK);
//    }

}
