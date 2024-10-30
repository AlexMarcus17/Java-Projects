import repository.CarRepository;
import service.CarService;
import ui.CarUI;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        CarService carService = new CarService(carRepository);
        CarUI carUI = new CarUI(carService);

        carService.addCar("1", "Toyota", "Camry", 2017, true);
        carService.addCar("3", "Ford", "Mustang", 2022, true);
        carService.addCar("2", "Audi", "A6", 2020, true);
        carService.addCar("4", "Porsche", "Macan", 2021, true);
        carService.addCar("5", "Renault", "Clio", 2019, true);


        carUI.showMenu();
    }
}
