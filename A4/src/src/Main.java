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
        IRepository<Car> repo = null;

        FileReader fileToRead = null;
        try {
            fileToRead = new FileReader("settings.properties");
            Properties prop = new Properties();
            prop.load(fileToRead);

            String repositoryType = (String) prop.get("type");
            String repositoryPath = prop.getProperty("path");

            if (repositoryType.equals("text"))
                repo = new TextFileRepository(repositoryPath);

            if (repositoryType.equals("binary"))
                repo= new BinaryFileRepository(repositoryPath);

            if (repositoryType.equals("DB"))
                repo = new DatabaseRepository();

            if (repositoryType.equals("memory"))
                repo = new CarRepository();


            CarService carService = new CarService((CarRepository) repo);
            CarUI carUI = new CarUI(carService);

            carService.addCar("1", "Toyota", "Camry", 2017, true);
            carService.addCar("3", "Ford", "Mustang", 2022, true);
            carService.addCar("2", "Audi", "A6", 2020, true);
            carService.addCar("4", "Porsche", "Macan", 2021, true);
            carService.addCar("5", "Renault", "Clio", 2019, true);


            carUI.showMenu();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
