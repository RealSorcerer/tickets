package org.ruban;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class Service {

    public static final HashMap<String, String> TIME_ZONES = new HashMap<>();

    static {
        TIME_ZONES.put("Владивосток", " +0700");
        TIME_ZONES.put("Тель-Авив", " +0200");
    }

    public static List<Ticket> getTicketsFromJsonFile(String path) throws IOException {
        File file = new File(path);
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String json = new String(encoded, Charset.forName("UTF-8")).replaceAll("\uFEFF", "");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        rootNode = rootNode.get(rootNode.fieldNames().next());
        return mapper.readValue(rootNode.toString(), new TypeReference<List<Ticket>>(){});
    }


    public static List<Long> getListFlightTime(String path, String originName, String destinationName) throws ParseException, IOException {
        List<Ticket> tickets = getTicketsFromJsonFile(path);
        ArrayList<Long> list = new ArrayList<>();

        for (Ticket ticket: tickets) {
            if(originName.equals(ticket.getOriginName()) && destinationName.equals(ticket.getDestinationName())) {
                String departureDateStr = new StringBuilder(ticket.getDepartureDate())
                        .append(" ").append(ticket.getDepartureTime())
                        .append(TIME_ZONES.get(ticket.getOriginName())).toString();
                String arrivalDateStr = new StringBuilder(ticket.getArrivalDate())
                        .append(" ").append(ticket.getArrivalTime())
                        .append(TIME_ZONES.get(ticket.getDestinationName())).toString();

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm Z", Locale.ENGLISH);
                Date departureDate = formatter.parse(departureDateStr);
                Date arrivalDate = formatter.parse(arrivalDateStr);
                long unixTimestamp = Math.abs(arrivalDate.getTime() - departureDate.getTime());
                list.add(unixTimestamp);
            }
        }

        return list;
    }

    public static String averageFlightTime(List<Long> list) {
        long averageFlightTimeMillis = (long) list.stream().mapToLong(s->s).average().orElse(0.0);
        Duration duration = Duration.ofMillis(averageFlightTimeMillis) ;
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return new StringBuilder()
                .append("Среднее время полета между городами Владивосток и Тель-Авив : ")
                .append(hours).append(":").append(minutes).toString();
    }

    public static String getPercentileFlightTime(int percentile, List<Long> list) {
        Collections.sort(list);
        long percentileFlightTime = list.get((int) Math.round((list.size() - 1) * percentile / 100));
        Duration duration = Duration.ofMillis(percentileFlightTime) ;
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return new StringBuilder()
                .append(percentile + "-й процентиль времени полета между городами Владивосток и Тель-Авив : ")
                .append(hours).append(":").append(minutes).toString();

    }


}
