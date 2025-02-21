package repository;

import domain.Car;
import domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class CarRepository extends MemoryRepository<Car> {

    private List<Reservation> reservations = new ArrayList<>();

    public void addCar(Car newCar) {
        add( newCar,newCar.getId());
    }

    public List<Car> getAllCars() {
        return List.of(getAll());
    }

    @Override
    public Car[] getAll() {
        return elements.values().toArray(new Car[0]);
    }

    public Car getCarById(String id) {
        return findById(id);
    }

    public void updateCar(Car updatedCar) {
        update( updatedCar,updatedCar.getId());
    }

    public void deleteCar(String id) {
        delete(id);
    }

    public void createReservation(Reservation newReservation) {
        Car car = newReservation.getCar();
        if (car.isAvailable()) {
            car.setAvailable(false);
            reservations.add(newReservation);
            updateCar(car);
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
            updateCar(car);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}
