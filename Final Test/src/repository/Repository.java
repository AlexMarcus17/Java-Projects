package repository;



import domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Connection connection;

    public Repository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, gradyear TEXT, title TEXT, percentage TEXT, coordinator TEXT)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update( String newTitle, String newPercentage,String name) {
        String updateSQL = "UPDATE student SET title = ?, percentage = ? WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newPercentage);
            pstmt.setString(3, name);

            int rowsUpdated = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<Student> get() {


        ArrayList<Student> bact = new ArrayList<>();
        try
        {
            PreparedStatement statement =
                     connection.prepareStatement("SELECT * from student");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String gradyear = resultSet.getString(3);
                String title = resultSet.getString(4);
                String percentage = resultSet.getString(5);
                String coordinator = resultSet.getString(6);

                Student std = new Student(name, gradyear, title, percentage, coordinator);
                bact.add(std);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bact;
    }

}



//git push -u origin main --force