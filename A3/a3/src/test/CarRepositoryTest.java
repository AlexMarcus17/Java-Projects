package test;

import domain.Car;
import domain.Reservation;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CarRepository;

import java.time.LocalDate;
import java.util.List;

public class CarRepositoryTest {

    CarRepository carRepository;
    @BeforeEach
    public void setUp() {
        carRepository = new CarRepository();
        carRepository.addCar(new Car("1", "Toyota", "Corolla", 2010, true)); // Add initial car
    }

    @Test
    public void addCar_validCar_addsSuccessfully() {
        Car newCar = new Car("2", "Honda", "Civic", 2015, true);
        carRepository.addCar(newCar);
        Car retrievedCar = carRepository.getCarById("2");
        Assertions.assertNotNull(retrievedCar);
        Assertions.assertEquals("Honda", retrievedCar.getBrand());
    }

    @Test
    public void getAllCars_returnsAllCars() {
        Car newCar = new Car("2", "Honda", "Civic", 2015, true);
        carRepository.addCar(newCar);

        List<Car> cars = carRepository.getAllCars();
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    public void updateCar_validCar_updatesSuccessfully() {
        Car updatedCar = new Car("1", "Toyota Updated", "Corrola", 2010, false);
        carRepository.updateCar(updatedCar);

        Car retrievedCar = carRepository.getCarById("1");
        Assertions.assertEquals("Toyota Updated", retrievedCar.getBrand());
        Assertions.assertFalse(retrievedCar.isAvailable());
    }

    @Test
    public void deleteCar_validId_deletesSuccessfully() {
        carRepository.deleteCar("1");
        Car deletedCar = carRepository.getCarById("1");
        Assertions.assertNull(deletedCar);
    }

    @Test
    public void createReservation_availableCar_createsSuccessfully() {
        Car car = carRepository.getCarById("1");
        Reservation reservation = new Reservation("1", car ,"Alex", LocalDate.now(), LocalDate.now());

        carRepository.createReservation(reservation);

        List<Reservation> reservations = carRepository.getAllReservations();
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertFalse(car.isAvailable());
    }

    @Test
    public void createReservation_unavailableCar_doesNotCreate() {
        Car car = carRepository.getCarById("1");
        car.setAvailable(false);
        carRepository.updateCar(car);

        Reservation reservation = new Reservation("2", car, "Alex", LocalDate.now(), LocalDate.now());
        carRepository.createReservation(reservation);

        List<Reservation> reservations = carRepository.getAllReservations();
        Assertions.assertEquals(0, reservations.size());
    }

    @Test
    public void cancelReservation_existingReservation_cancelsSuccessfully() {
        Car car = carRepository.getCarById("1");
        Reservation reservation = new Reservation("1", car, "Alex", LocalDate.now(), LocalDate.now());

        carRepository.createReservation(reservation);
        carRepository.cancelReservation("r1");

        List<Reservation> reservations = carRepository.getAllReservations();
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertFalse(car.isAvailable());
    }

    @Test
    public void cancelReservation_nonExistingReservation_doesNothing() {
        Car car = carRepository.getCarById("1");
        Reservation reservation = new Reservation("1", car, "Alex", LocalDate.now(), LocalDate.now());

        carRepository.createReservation(reservation);
        carRepository.cancelReservation("r2");

        List<Reservation> reservations = carRepository.getAllReservations();
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertFalse(car.isAvailable());
    }
}
