package org.ruban;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ticket {

     private String origin;
     private String originName;
     private String destination;
     private String destinationName;
     private String departureDate;
     private String departureTime;
     private String arrivalDate;
     private String arrivalTime;
     private String carrier;
     private byte stops;
     private int price;

     public String getOrigin() {
          return origin;
     }

     public void setOrigin(String origin) {
          this.origin = origin;
     }

     public String getOriginName() {
          return originName;
     }

     public void setOriginName(String originName) {
          this.originName = originName;
     }

     public String getDestination() {
          return destination;
     }

     public void setDestination(String destination) {
          this.destination = destination;
     }

     public String getDestinationName() {
          return destinationName;
     }

     public void setDestinationName(String destinationName) {
          this.destinationName = destinationName;
     }

     public String getDepartureDate() {
          return departureDate;
     }

     public void setDepartureDate(String departureDate) {
         this.departureDate = departureDate;
     }

     public String getDepartureTime() {
          return departureTime;
     }

     public void setDepartureTime(String departureTime) {
          this.departureTime = departureTime;
     }

     public String getArrivalDate() {
          return arrivalDate;
     }

     public void setArrivalDate(String arrivalDate) {
          this.arrivalDate = arrivalDate;
     }

     public String getArrivalTime() {
          return arrivalTime;
     }

     public void setArrivalTime(String arrivalTime) {
          this.arrivalTime = arrivalTime;
     }

     public String getCarrier() {
          return carrier;
     }

     public void setCarrier(String carrier) {
          this.carrier = carrier;
     }

     public byte getStops() {
          return stops;
     }

     public void setStops(byte stops) {
          this.stops = stops;
     }

     public int getPrice() {
          return price;
     }

     public void setPrice(int price) {
          this.price = price;
     }
}
