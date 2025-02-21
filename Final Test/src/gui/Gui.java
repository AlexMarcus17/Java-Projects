package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.Repository;
import service.Service;

import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("hello-view.fxml"));
        Repository repo = new Repository();
        Service service = new Service(repo);
        fxmlLoader.setControllerFactory(param -> new GuiController(service));

        Scene scene = new Scene(fxmlLoader.load(), 500, 240);

        stage.setTitle("Test");





        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}