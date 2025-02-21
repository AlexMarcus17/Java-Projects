package gui;

import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.Repository;
import service.Service;

import java.util.List;
import java.util.stream.Collectors;

public class GuiController {
    private final Service service;
    ObservableList<Student> l;
    @FXML
    private ListView<Student> listViewBacterium;

    @FXML
    private CheckBox checkBoxSearch;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TextField updateTextField;

    @FXML
    private TextField findTextField;

    @FXML
    private Button buttonFind;

    @FXML
    private Label emptyLabel;




    public GuiController(Service service) {
        this.service = service;

    }

    @FXML
    public void initialize() {
        l = FXCollections.observableArrayList();
        List<Student> bacts  = service.getAllBacts();
        l = FXCollections.observableList(bacts);
        listViewBacterium.setItems(FXCollections.observableList(l));


        buttonSearch.setOnAction(this::searchHandler);
    }

    @FXML
    void searchHandler(ActionEvent event) {
        boolean notitle = checkBoxSearch.isSelected();
        String searchText = searchTextField.getText();
        List<Student> filteredStudent = service.searchBacts(notitle, searchText);
        l = FXCollections.observableList(filteredStudent);
        listViewBacterium.setItems(FXCollections.observableList(l));
    }

    @FXML
    void Update(ActionEvent event) {
        Student student = listViewBacterium.getSelectionModel().getSelectedItem();
        String title = findTextField.getText();
        String percg = updateTextField.getText();
        service.update(title,percg,student);
        service.getAllBacts();
        List<Student> bcts  = service.getAllBacts();
        l = FXCollections.observableList(bcts);
        listViewBacterium.setItems(FXCollections.observableList(l));
    }



}