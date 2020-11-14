package org.ruban;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try {
            List<Long> flightTimes = Service.getListFlightTime("tickets1.json");

            System.out.println(Service.averageFlightTime(flightTimes));
            System.out.println(Service.getPercentileFlightTime(90, flightTimes));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
