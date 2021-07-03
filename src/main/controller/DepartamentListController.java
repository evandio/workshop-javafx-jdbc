/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import main.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.model.entities.Departament;

/**
 * FXML Controller class
 *
 * @author evand
 */
public class DepartamentListController implements Initializable {

    @FXML
    private TableView<Departament> tableViewDepartament;

    @FXML
    private TableColumn<Departament, Integer> tableColumnId;

    @FXML
    private TableColumn<Departament, String> tableColumnName;

    @FXML
    private Button btNew;

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
        tableColumnId.setCellValueFactory(new PropertyValueFactory<Departament, Integer>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       initializeNodes();
    }

}
