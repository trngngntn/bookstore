package fa.training.entity;

import com.google.gson.annotations.JsonAdapter;
import fa.training.meta.BookingOfficeMeta;
import fa.training.utils.typeAdapter.DateTypeAdapter;

import java.sql.Date;

public class BookingOffice extends BaseEntity<BookingOffice>{
    @Override
    public Class<BookingOfficeMeta> getMeta() {
        return BookingOfficeMeta.class;
    }

    private int id;
    private String name;
    private String phone;
    private String place;
    private double price;
    @JsonAdapter(value = DateTypeAdapter.class)
    private Date startContract;
    @JsonAdapter(value = DateTypeAdapter.class)
    private Date endContract;
    private int tripId;

    public BookingOffice() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartContract() {
        return startContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public int getTripId() {
        return tripId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartContract(Date startContract) {
        this.startContract = startContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
}
