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
import java.util.function.Consumer;
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
        loadView("/main/gui/DepartamentList.fxml", (DepartamentListController controller) -> {
            controller.setDepartamentService(new DepartamentService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/main/gui/About.fxml", x -> {
        });
    }

    //<T> Função generica parametrizada (existe aula para isso)
    //Proucurar a aula de funções Lambda
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {

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

            T controller = loader.getController();
            initializingAction.accept(controller);

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
