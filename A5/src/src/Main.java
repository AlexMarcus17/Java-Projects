import domain.Car;
import repository.*;
import service.CarService;
import ui.CarUI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {




            CarRepository repo = new CarRepository();
            CarService carService = new CarService((CarRepository) repo);
            CarUI carUI = new CarUI(carService);

            carService.addCar("1", "Toyota", "Camry", 2017, true);
            carService.addCar("3", "Ford", "Mustang", 2022, true);
            carService.addCar("2", "Audi", "A6", 2020, true);
            carService.addCar("4", "Porsche", "Macan", 2021, true);
            carService.addCar("5", "Renault", "Clio", 2019, true);


            carUI.showMenu();




    }
}
