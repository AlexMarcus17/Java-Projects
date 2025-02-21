package test;
import domain.Car;
import domain.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CarRepository;
import service.CarService;

import java.time.LocalDate;
import java.util.List;

public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        CarRepository carRepository = new CarRepository();
        carService = new CarService(carRepository);

        carService.addCar("1", "Toyota", "Corolla", 2020, true);
        carService.addCar("2", "Honda", "Civic", 2019, false);
        carService.addCar("3", "Ford", "Focus", 2021, true);
    }

    @Test
    public void addCar_validCar_addsSuccessfully() {
        carService.addCar("4", "Mazda", "3", 2022, true);
        Car car = carService.getCarById("4");
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Mazda", car.getBrand());
        Assertions.assertEquals("3", car.getModel());
        Assertions.assertTrue(car.isAvailable());
    }

    @Test
    public void getAllCars_returnsAllCars() {
        List<Car> cars = carService.getAllCars();
        Assertions.assertEquals(3, cars.size());
    }

    @Test
    public void getCarById_existingCar_returnsCar() {
        Car car = carService.getCarById("1");
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Toyota", car.getBrand());
    }

    @Test
    public void updateCar_validCar_updatesSuccessfully() {
        carService.updateCar("1", false);
        Car updatedCar = carService.getCarById("1");
        Assertions.assertNotNull(updatedCar);
        Assertions.assertFalse(updatedCar.isAvailable());
    }

    @Test
    public void deleteCar_validId_deletesSuccessfully() {
        carService.deleteCar("3");
        Car car = carService.getCarById("3");
        Assertions.assertNull(car);
    }

    @Test
    public void createReservation_availableCar_createsSuccessfully() {
        carService.createReservation("r1", "1", "Alex", LocalDate.now(), LocalDate.now().plusDays(3));
        List<Reservation> reservations = carService.getAllReservations();
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertFalse(carService.getCarById("1").isAvailable());
    }

    @Test
    public void createReservation_unavailableCar_doesNotCreate() {
        carService.createReservation("r2", "2", "Alex", LocalDate.now(), LocalDate.now().plusDays(3));
        List<Reservation> reservations = carService.getAllReservations();
        Assertions.assertEquals(0, reservations.size());
    }

    @Test
    public void cancelReservation_existingReservation_cancelsSuccessfully() {
        carService.createReservation("r1", "1", "Alex", LocalDate.now(), LocalDate.now().plusDays(3));
        carService.cancelReservation("r1");

        List<Reservation> reservations = carService.getAllReservations();
        Assertions.assertEquals(0, reservations.size());
        Assertions.assertTrue(carService.getCarById("1").isAvailable());
    }

    @Test
    public void getAvailableCars_returnsOnlyAvailableCars() {
        List<Car> availableCars = carService.getAvailableCars();
        Assertions.assertEquals(2, availableCars.size());
        Assertions.assertTrue(availableCars.stream().allMatch(Car::isAvailable));
    }

    @Test
    public void getRentedCars_returnsOnlyRentedCars() {
        carService.updateCar("1", false);
        List<Car> rentedCars = carService.getRentedCars();
        Assertions.assertEquals(2, rentedCars.size());
        Assertions.assertTrue(rentedCars.stream().noneMatch(Car::isAvailable));
    }


}
