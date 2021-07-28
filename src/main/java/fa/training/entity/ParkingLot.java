package fa.training.entity;

public class ParkingLot {
    private int id;
    private String name;
    private int placeId;
    private double area;
    private double price;
    private boolean status;

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
}
