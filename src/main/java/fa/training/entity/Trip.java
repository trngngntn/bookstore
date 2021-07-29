package fa.training.entity;

import fa.training.meta.TripMeta;

import java.sql.Date;
import java.sql.Time;

public class Trip extends BaseEntity{
    @Override
    public Class getMeta() {
        return TripMeta.class;
    }

    private int id;
    private int bookedTicket;
    private String carType;
    private Time departureTime;
    private Date departureDate;
    private String destination;
    private String driver;
    private int maxOnlTicket;

    public Trip() {
    }

    public int getId() {
        return id;
    }

    public int getBookedTicket() {
        return bookedTicket;
    }

    public String getCarType() {
        return carType;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getDriver() {
        return driver;
    }

    public int getMaxOnlTicket() {
        return maxOnlTicket;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookedTicket(int bookedTicket) {
        this.bookedTicket = bookedTicket;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setMaxOnlTicket(int maxOnlTicket) {
        this.maxOnlTicket = maxOnlTicket;
    }
}
