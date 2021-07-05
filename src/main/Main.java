package main;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import main.gui.util.Alerts;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author evand
 */
public class Main extends Application {

    private static Scene mainScene;

    @Override
    public void start(Stage stage) {

        try {
            ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("/main/gui/ViewMain.fxml"));
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            mainScene = new Scene(scrollPane);

            stage.setScene(mainScene);
            stage.show();
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading Scene", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
