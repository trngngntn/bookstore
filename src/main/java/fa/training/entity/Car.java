package fa.training.entity;

import fa.training.meta.CarMeta;

public class Car extends BaseEntity{
    @Override
    public Class getMeta() {
        return CarMeta.class;
    }

    private String licensePlate;
    private String type;
    private String color;
    private int officeId;
    private int parkingLotId;

    public Car() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getOfficeId() {
        return officeId;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
