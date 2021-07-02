/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author evand
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("/gui/ViewMain.fxml"));
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        Scene scene = new Scene(scrollPane);
        
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
