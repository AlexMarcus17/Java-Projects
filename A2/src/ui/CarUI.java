package ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import domain.Car;
import domain.Reservation;
import service.CarService;


public class CarUI {
    private CarService carService;
    private Scanner scanner = new Scanner(System.in);
    private static final int ADD_CAR = 1;
    private static final int VIEW_ALL_CARS = 2;
    private static final int DELETE_CAR = 3;
    private static final int ADD_RESERVATION = 4;
    private static final int CANCEL_RESERVATION = 5;
    private static final int VIEW_ALL_RESERVATIONS = 6;
    private static final int VIEW_AVAILABLE_CARS = 7;
    private static final int VIEW_RENTED_CARS = 8;
    private static final int EXIT = 0;


    public CarUI(CarService carService) {
        this.carService = carService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Car Rental Management ---");
            System.out.println(ADD_CAR+". Add Car");
            System.out.println(VIEW_ALL_CARS+". View All Cars");
            System.out.println(DELETE_CAR+". Delete Car");
            System.out.println(ADD_RESERVATION+". Create Reservation");
            System.out.println(CANCEL_RESERVATION+". Cancel Reservation");
            System.out.println(VIEW_ALL_RESERVATIONS+". View All Reservations");
            System.out.println(VIEW_AVAILABLE_CARS+". View Available Cars");
            System.out.println(VIEW_RENTED_CARS+". View Rented Cars");
            System.out.println(EXIT+". Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case ADD_CAR:
                    addCar();
                    break;
                case VIEW_ALL_CARS:
                    viewAllCars();
                    break;
                case DELETE_CAR:
                    deleteCar();
                    break;
                case ADD_RESERVATION:
                    createReservation();
                    break;
                case CANCEL_RESERVATION:
                    cancelReservation();
                    break;
                case VIEW_ALL_RESERVATIONS:
                    viewAllReservations();
                    break;
                case VIEW_AVAILABLE_CARS:
                    viewAvailableCars();
                    break;
                case VIEW_RENTED_CARS:
                    viewRentedCars();
                    break;
                case EXIT:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addCar() {
        System.out.print("Enter Car ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Is the car available? (true/false): ");
        boolean available = scanner.nextBoolean();

        carService.addCar(id, brand, model, year, available);
        System.out.println("Car added successfully.");
    }

    private void viewAllCars() {
        List<Car> cars = carService.getAllCars();
        System.out.println("\n--- All Cars ---");
        for (Car car : cars) {
            System.out.println(car);
        }
    }


    private void deleteCar() {
        System.out.print("Enter Car ID: ");
        String id = scanner.nextLine();

        carService.deleteCar(id);
        System.out.println("Car deleted.");
    }

    private void createReservation() {
        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();
        System.out.print("Enter Car ID: ");
        String carId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        LocalDate rentalDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        carService.createReservation(reservationId, carId, customerName, rentalDate, returnDate);
        System.out.println("Reservation created.");
    }

    private void cancelReservation() {
        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();

        carService.cancelReservation(reservationId);
        System.out.println("Reservation canceled.");
    }

    private void viewAllReservations() {
        List<Reservation> reservations = carService.getAllReservations();
        System.out.println("\n--- All Reservations ---");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private void viewAvailableCars() {
        List<Car> availableCars = carService.getAvailableCars();
        System.out.println("\n--- Available Cars ---");
        for (Car car : availableCars) {
            System.out.println(car);
        }
    }

    private void viewRentedCars() {
        List<Car> rentedCars = carService.getRentedCars();
        System.out.println("\n--- Rented Cars ---");
        for (Car car : rentedCars) {
            System.out.println(car);
        }
    }
}
