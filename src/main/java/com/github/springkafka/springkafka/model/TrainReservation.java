package com.github.springkafka.springkafka.model;

public class TrainReservation {

    private String passengerName;
    //    private String seatNumber;
    private String coachNumber;
    private String source;
    private String destination;

    public TrainReservation() {
    }

    public TrainReservation(String passengerName, String coachNumber, String source, String destination) {
        this.passengerName = passengerName;
//        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber;
        this.source = source;
        this.destination = destination;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
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
        return "TrainReservation{" +
                "passengerName='" + passengerName + '\'' +
                ", coachNumber='" + coachNumber + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
