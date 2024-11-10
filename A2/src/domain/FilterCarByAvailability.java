package domain;

import domain.Car;

public class FilterCarByAvailability implements AbstractFilter<Car> {
    private boolean isAvailable;

    public FilterCarByAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean accept(Car car) {
        return car.isAvailable() == isAvailable;
    }
}
