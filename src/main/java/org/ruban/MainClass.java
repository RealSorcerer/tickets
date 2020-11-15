package org.ruban;

import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try {
            List<Long> flightTimes = Service.getListFlightTime("tickets.json", "Владивосток", "Тель-Авив");

            System.out.println(Service.averageFlightTime(flightTimes));
            System.out.println(Service.getPercentileFlightTime(90, flightTimes));
        } catch (JsonParseException e) {
            System.out.println("Не верный формат json файла");
        } catch (IOException e) {
            System.out.println("Проверьте наличие tickets.json в текущем каталоге.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
