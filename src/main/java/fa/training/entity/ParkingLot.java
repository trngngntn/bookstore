package fa.training.entity;

import fa.training.meta.ParkingLotMeta;

public class ParkingLot extends BaseEntity<ParkingLot>{
    @Override
    public Class<ParkingLotMeta> getMeta() {
        return ParkingLotMeta.class;
    }

    private int id;
    private String name;
    private int placeId;
    private double area;
    private double price;
    private boolean status;

    public ParkingLot() {
    }

    public ParkingLot(int id, String name, int placeId, double area, double price, boolean status) {
        this.id = id;
        this.name = name;
        this.placeId = placeId;
        this.area = area;
        this.price = price;
        this.status = status;
    }

    public ParkingLot(String name, int placeId, double area, double price) {
        this.name = name;
        this.placeId = placeId;
        this.area = area;
        this.price = price;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlaceId() {
        return placeId;
    }

    public double getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
