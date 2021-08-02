package fa.training.entity;

import fa.training.meta.CarMeta;
import fa.training.utils.validator.LicensePlateValidator;

public class Car extends BaseEntity<Car>{
    @Override
    public Class<CarMeta> getMeta() {
        return CarMeta.class;
    }

    private String licensePlate;
    private String type;
    private String color;
    private int officeId;
    private int parkingLotId;

    public Car() {
    }

    public String getId(){
        return licensePlate;
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
        LicensePlateValidator v = new LicensePlateValidator();
        this.licensePlate = v.normalize(licensePlate);
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
