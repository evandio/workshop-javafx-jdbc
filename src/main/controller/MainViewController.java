package main.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import main.Main;
import main.gui.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import main.model.services.DepartamentService;

/**
 *
 * @author evand
 */
public class MainViewController implements Initializable {

    //Para os IDs nas GUI
    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartament;

    @FXML
    private MenuItem menuItemAbout;

    //Para os eventos heandler
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("Seller");
    }

    @FXML
    public void onMenuItemDepartamentAction() {
        loadView2("/main/gui/DepartamentList.fxml");
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/main/gui/About.fxml");
    }

    private synchronized void loadView(String absoluteName) {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            Scene mainScene = Main.getMainScene();

            //Aula275
            //Faz Casting para pegar o VBox
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            //preserva o Menu principal
            Node mainMenu = mainVbox.getChildren().get(0);
            //Limpa os Filhos do VBox
            mainVbox.getChildren().clear();

            //Adiciona o Menu novamente
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVBox.getChildren());

        } catch (IOException e) {
            Alerts.showAlert("IO Exceptio", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private synchronized void loadView2(String absoluteName) {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            Scene mainScene = Main.getMainScene();

            //Aula275
            //Faz Casting para pegar o VBox
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            //preserva o Menu principal
            Node mainMenu = mainVbox.getChildren().get(0);
            //Limpa os Filhos do VBox
            mainVbox.getChildren().clear();

            //Adiciona o Menu novamente
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVBox.getChildren());

            //Aula 278 - Processo manual de injetar a dependencia no controler
            // e chamar os dados para atualizar a table view
            DepartamentListController controller = loader.getController();
            controller.setDepartamentService(new DepartamentService());
            controller.updateTableView();

        } catch (IOException e) {
            Alerts.showAlert("IO Exceptio", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
