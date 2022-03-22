/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerystoregui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class groceryStoreGUI extends Application{
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("primaryGUI.fxml"));
        Scene scene = new Scene(root, 700, 400);
    
        primaryStage.setTitle("primaryGUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String [] args){
        launch(args);
    }
}
