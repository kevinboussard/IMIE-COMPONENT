package com.imie.component.controller;

import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * <b>RestController Utility class</b>
 *
 * @author kevin boussard
 * @version 1.0
 */
@RestController
@RequestMapping("/api/utility")
public class UtilityController {

    /**
     * <b>Function to enable to convert a date to iso 8601.</b>
     *
     * <p>It's a Web-Service is accessible via 'localhost:8080/api/utility/date/change'</p>
     *
     * <p>The param 'Date' must to give to the Request Body. Example : "2016-06-25"</p>
     *
     * @param date - Date that will be convert to iso8601
     * @return String - Date after convertion
     */
    @RequestMapping(path = "/date/change", method = RequestMethod.POST)
    public @ResponseBody String convertDateToIso8601(@RequestBody Date date) {

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSZ");
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(date);

    }
}