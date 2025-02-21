package repository;

import domain.Car;

import java.sql.*;

public class DatabaseRepository extends MemoryRepository<Car> {
    public static final String JDBC_URL = "jdbc:sqlite:data/data.sqlite";

    public DatabaseRepository() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM cars");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                boolean available = resultSet.getBoolean("available");

                Car car = new Car(id, brand, model, year, available);
                super.add(car, car.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing DatabaseRepository from database.", e);
        }
    }


    public void addCar(Car car) {
        super.add(car, car.getId());

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO cars (id, brand, model, year, available) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, car.getId());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setInt(4, car.getYear());
            statement.setBoolean(5, car.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting car into database.", e);
        }
    }

    public void updateCar(Car car) {
        super.update(car, car.getId());

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE cars SET brand = ?, model = ?, year = ?, available = ? WHERE id = ?");
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setBoolean(4, car.isAvailable());
            statement.setString(5, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car in database.", e);
        }
    }

    public void deleteCar(String id) {
        super.delete(id);

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM cars WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car from database.", e);
        }
    }
}
