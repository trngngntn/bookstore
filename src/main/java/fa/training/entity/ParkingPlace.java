package fa.training.entity;

public class ParkingPlace {
    private int id;
    private String name;

    public ParkingPlace(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
