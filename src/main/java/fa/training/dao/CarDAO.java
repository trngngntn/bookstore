package fa.training.dao;

import fa.training.entity.Car;
import fa.training.meta.CarMeta;

import java.util.List;

public class CarDAO extends BaseDAO<Car>{

    public CarDAO() {
        super(CarMeta.class);
    }
}
