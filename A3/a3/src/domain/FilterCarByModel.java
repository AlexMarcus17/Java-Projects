package domain;


public class FilterCarByModel implements AbstractFilter<Car> {
    private String model;

    public FilterCarByModel(String model) {
        this.model = model;
    }

    @Override
    public boolean accept(Car car) {
        return car.getModel().equals(model);
    }
}
