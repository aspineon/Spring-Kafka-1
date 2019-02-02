package com.github.springkafka.springkafka.model;

public class FlightReservation {

    private String passengerName;
    private String seatNumber;
    private String source;
    private String destination;

    public FlightReservation() {
    }

    public FlightReservation(String passengerName, String seatNumber, String source, String destination) {
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.source = source;
        this.destination = destination;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "FlightReservation{" +
                "passengerName='" + passengerName + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
