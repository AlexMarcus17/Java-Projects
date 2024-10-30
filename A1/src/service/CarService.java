package service;

import domain.Car;
import domain.Reservation;
import repository.CarRepository;

import java.time.LocalDate;
import java.util.List;

public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
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
        return carRepository.getAvailableCars();
    }

    public List<Car> getRentedCars() {
        return carRepository.getRentedCars();
    }
}

