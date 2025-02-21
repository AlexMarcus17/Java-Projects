package repository;

import domain.AbstractFilter;
import domain.Car;

import java.util.Arrays;


public class FilteredRepository extends MemoryRepository<Car> {

    private AbstractFilter<Car> filter;

    public FilteredRepository(AbstractFilter<Car> filter) {
        this.filter = filter;
    }

    public void setFilter(AbstractFilter<Car> filter) {
        this.filter = filter;
    }

    @Override
    public Car[] getAll() {
        return Arrays.stream(elements.values().toArray(new Car[0])).filter(filter::accept).toArray(Car[]::new);

    }
}
