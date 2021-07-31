package fa.training.entity;

import com.google.gson.annotations.JsonAdapter;
import fa.training.meta.TicketMeta;
import fa.training.utils.typeAdapter.TimeTypeAdapter;

import java.sql.Time;

public class Ticket extends BaseEntity<Ticket>{
    @Override
    public Class<TicketMeta> getMeta() {
        return TicketMeta.class;
    }

    private int id;
    private String customerName;
    @JsonAdapter(value = TimeTypeAdapter.class)
    private Time bookedTime;
    private int tripId;
    private String licensePlate;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Time getBookedTime() {
        return bookedTime;
    }

    public int getTripId() {
        return tripId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBookedTime(Time bookedTime) {
        this.bookedTime = bookedTime;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
