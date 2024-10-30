package domain;
import java.time.LocalDate;

public class Reservation implements Identifiable {
    private String id;
    private Car car;
    private String customerName;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Reservation(String id, Car car, String customerName, LocalDate rentalDate, LocalDate returnDate) {
        this.id = id;
        this.car = car;
        this.customerName = customerName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    @Override
    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", car=" + car.getBrand() + " " + car.getModel() +
                ", customerName='" + customerName + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }

}
