package repository;

import domain.Car;
import domain.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {

    private List<Car> cars = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();


    public void addCar(Car newCar) {
        cars.add(newCar);
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarById(String id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateCar(Car updatedCar) {
        Car car = getCarById(updatedCar.getId());
        if (car != null) {
            car.setAvailable(updatedCar.isAvailable());
        }
    }

    public void deleteCar(String id) {
        cars.removeIf(car -> car.getId().equals(id));
    }

    public void createReservation(Reservation newReservation) {
        Car car = newReservation.getCar();
        if (car.isAvailable()) {
            car.setAvailable(false);
            reservations.add(newReservation);
        } else {
            System.out.println("Car is not available for reservation.");
        }
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElse(null);

        if (reservation != null) {
            Car car = reservation.getCar();
            car.setAvailable(true);
            reservations.remove(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public List<Car> getAvailableCars() {
        return cars.stream()
                .filter(Car::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Car> getRentedCars() {
        return cars.stream()
                .filter(car -> !car.isAvailable())
                .collect(Collectors.toList());
    }
}

