package repository;

import domain.Car;

import java.io.*;
import java.util.HashMap;

public class BinaryFileRepository extends FileRepository<Car> {
    public BinaryFileRepository(String filename) throws IOException {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(filename))){
            this.elements=(HashMap<String, Car>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(filename))){
            objectOutputStream.writeObject(this.elements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
