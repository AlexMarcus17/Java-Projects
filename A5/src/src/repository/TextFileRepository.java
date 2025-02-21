package repository;

import domain.Car;

import java.io.*;

public class TextFileRepository extends FileRepository<Car> {
    public TextFileRepository(String filename) throws IOException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 5)
                    continue;
                String id = data[0];
                String brand = data[1];
                String model = data[2];
                int year = Integer.parseInt(data[3]);
                boolean available = Boolean.parseBoolean(data[4]);
                Car car = new Car(id, brand, model, year, available);
                super.add(car, id);
            }

    }

    @Override
    protected void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename)))
        {
            for (Car car: super.getAll())
            {
                bw.write(car.getId() + "," + car.getBrand() + "," + car.getModel() + "," + car.getYear() + "," + car.isAvailable() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
