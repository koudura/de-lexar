/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author habuto
 */
public class LexarController implements Initializable {

    @FXML
    private JFXTextArea sourceArea;
    @FXML
    private JFXTextArea tokenArea;

    public static JFXComboBox<?> extendsBox;
    @FXML
    private JFXButton loadSourceBtn;
    @FXML
    private JFXButton analyseBtn;
    @FXML
    private JFXButton saveTokensBtn;
    public static boolean loadedfile = false;
    @FXML
    private JFXButton clearTokenBtn;
    @FXML
    private JFXToggleButton liveAnalBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       liveAnalBtn.setSelected(false);
        loadSourceBtn.setOnAction((ActionEvent e) -> {
            loadedfile = true;
            LexDataControl control = new LexDataControl(sourceArea, tokenArea);
            control.setSourceFile();
            if (control.getSourceFile() != null) {
                control.loadSourceFile(control.getSourceFile());
            }
        });

        analyseBtn.setOnAction((ActionEvent e) -> {
            tokenArea.clear();
            LexLogic logic = new LexLogic(sourceArea, tokenArea);
            logic.runAnalysis();

        });

        clearTokenBtn.setOnAction((ActionEvent e) -> {
            tokenArea.setText("");
        });

        saveTokensBtn.setOnAction((ActionEvent e) -> {
            LexDataControl control = new LexDataControl(sourceArea, tokenArea);
            control.setTokenFile();
            if (control.getTokenFile() != null) {
                control.saveTokenFile(control.getTokenFile());
            }
        });

        sourceArea.setOnKeyReleased((KeyEvent event) -> {
            if (liveAnalBtn.isSelected()) {
                
                tokenArea.clear();
                LexLogic logic = new LexLogic(sourceArea, tokenArea);
                logic.runAnalysis();
            } else {
                
            }
        });
    }

    @FXML
    private void initialize(ActionEvent event) {

    }

}
