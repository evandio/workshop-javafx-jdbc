/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import main.Main;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.model.entities.Department;
import main.model.services.DepartamentService;

/**
 * FXML Controller class
 *
 * @author evand
 */
public class DepartamentListController implements Initializable {

    private DepartamentService service;

    @FXML
    private TableView<Department> tableViewDepartament;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    @FXML
    private Button btNew;

    private ObservableList<Department> obsList;

    @FXML
    private void onBtNewAction() {
        System.out.println("Botão New");
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    //Aula 277
    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());
    }

    //Aula 278 - Principio Sólide (Inversão de controle)
    //Isso e uma forma de injetar a dependência na clase. Não é utilizado o NEW para o objeto
    public void setDepartamentService(DepartamentService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null!");
        }

        List<Department> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartament.setItems(obsList);
    }

}