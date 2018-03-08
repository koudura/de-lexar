/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author habuto
 */
public class Lexar extends Application {
    
    public static Stage stagee;
    public static JFXDecorator windowPane;
    
    @Override
    public void start(Stage stage) throws Exception {
        LexTableLoad load = new LexTableLoad();
        
        stagee = stage;
        stagee.initStyle(StageStyle.TRANSPARENT);
        stagee.setTitle("True Lexar");
        
        Parent root = FXMLLoader.load(getClass().getResource("Lexar.fxml"));
        windowPane = new JFXDecorator(stage, root, false, false, true);
	windowPane.setEffect(new DropShadow());
	windowPane.setCustomMaximize(false);
        
        Scene scene = new Scene(windowPane);
	scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("res/Lextyle.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
