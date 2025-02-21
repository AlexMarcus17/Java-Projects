package service;

import domain.AbstractFilter;
import domain.Car;
import domain.FilterCarByAvailability;
import domain.Reservation;
import repository.CarRepository;
import repository.FilteredRepository;

import java.time.LocalDate;
import java.util.List;

public class CarService {
    private CarRepository carRepository;
    private FilteredRepository filteredRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void setFilter(AbstractFilter<Car> filter) {
        filteredRepository = new FilteredRepository(filter);
        for (Car car : carRepository.getAllCars()) {
            filteredRepository.add( car,car.getId());
        }
    }

    public List<Car> getFilteredCars() {
        if (filteredRepository == null) {
            throw new IllegalStateException("No filter has been set for the filtered repository.");
        }
        return List.of(filteredRepository.getAll()) ;
    }

    public void addCar(String id, String brand, String model, int year, boolean available) {
        Car newCar = new Car(id, brand, model, year, available);
        carRepository.addCar(newCar);
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCarById(String id) {
        return carRepository.getCarById(id);
    }

    public void updateCar(String id, boolean available) {
        Car pickedCar = carRepository.getCarById(id);
        if (pickedCar != null) {
            pickedCar.setAvailable(available);
            carRepository.updateCar(pickedCar);
        } else {
            System.out.println("Car not found.");
        }
    }

    public void deleteCar(String id) {
        carRepository.deleteCar(id);
    }

    public void createReservation(String reservationId, String carId, String customerName,
                                  LocalDate rentalDate, LocalDate returnDate) {
        Car pickedCar = carRepository.getCarById(carId);
        if (pickedCar != null && pickedCar.isAvailable()) {
            Reservation newReservation = new Reservation(reservationId, pickedCar, customerName, rentalDate, returnDate);
            carRepository.createReservation(newReservation);
        } else {
            System.out.println("Car not available for reservation or not found.");
        }
    }

    public void cancelReservation(String reservationId) {
        carRepository.cancelReservation(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return carRepository.getAllReservations();
    }

    public List<Car> getAvailableCars() {
        setFilter(new FilterCarByAvailability(true));
        return getFilteredCars();
    }

    public List<Car> getRentedCars() {
        setFilter(new FilterCarByAvailability(false));
        return getFilteredCars();
    }
}
